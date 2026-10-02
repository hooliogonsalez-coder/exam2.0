package main;

import logic.DispatchRule;
import logic.LogisticManager;
import models.CargoHelicopter;
import models.DeliveryDrone;
import models.GroundRobot;
import models.Transport;

import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static final Scanner SC = new Scanner(System.in);
    static final LogisticManager M = new LogisticManager();

    public static void main(String[] args) {
        M.loadState();
        if (M.isEmpty()) System.out.println("Система пуста");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = SC.nextLine().trim();
            switch (choice) {
                case "1": addTransport(); break;
                case "2": toggleTransit(); break;
                case "3":
                    M.addRule(calibration());
                    System.out.println(">>> Правило «Калибровка батареи» добавлено.");
                    break;
                case "4":
                    M.addRule(variant());
                    System.out.println(">>> Правило варианта добавлено.");
                    break;
                case "5":
                    M.appluAllRules();
                    System.out.println(">>> Все правила применены.");
                    break;
                case "6": analytics(); break;
                case "7":
                    M.saveState();
                    running = false;
                    break;
                default:
                    System.out.println("!!! Неверный пункт меню");
            }
        }
        SC.close();
    }


    static void printMenu() {
        System.out.println();
        System.out.println("СИСТЕМА УПРАВЛЕНИЯ ДОСТАВКОЙ");
        System.out.println("1. Добавить транспорт в хаб");
        System.out.println("2. Отправить / вернуть транспорт по ID");
        System.out.println("3. Добавить правило «Калибровка батареи» (общее)");
        System.out.println("4. Добавить правило варианта (экстренный возврат дрона)");
        System.out.println("5. Запустить проверку всех правил");
        System.out.println("6. Аналитика: ТОП-3 по заряду батареи");
        System.out.println("7. Выход (с сохранением состояния)");
        System.out.print("> ");
    }

    static String ask(String p) {
        System.out.print(p);
        return SC.nextLine().trim();
    }

    static int askI(String p) {return Integer.parseInt(ask(p));}

    static double askD(String p) {return Double.parseDouble(ask(p).replace(',', '.'));}

    static void addTransport() {
        String id = ask("ID: ");
        if (M.getTransportById(id) != null) {
            System.out.println("ID уже занят");
            return;
        }
        String model = ask("Модель: ");
        double bat   = askD("Заряд: ");
        String hub   = ask("Хаб: ");
        int type     = askI("Тип (1-дрон, 2-вертолёт, 3-робот): ");
        Transport t;
        switch (type) {
            case 1:
                t = new DeliveryDrone(id, model, bat,
                        askD("Грузоподъёмность (кг): "));
                break;
            case 2:
                t = new CargoHelicopter(id, model, bat,
                        askD("Дальность (км): "));
                break;
            case 3:
                t = new GroundRobot(id, model, bat,
                        askI("Износ колёс: "));
                break;
            default:
                System.out.println("Неизвестный тип");
                return;
        }
        M.addTransport(hub, t);
        System.out.println(">>> " + t.getDetails());
    }


    static void toggleTransit() {
        Transport t = M.getTransportById(ask("ID: "));
        if (t == null) {
            System.out.println("!!! Транспорт не найден");
            return;
        }
        int action = askI("1 - dispatch, 2 - recall: ");
        if (action == 1) {
            t.dispatch();
        } else {
            t.recall();
        }
        System.out.println(">>> " + t.getDetails());
    }


    static DispatchRule<Transport> calibration() {
        return new DispatchRule<Transport>(
                "Калибровка батареи",
                t -> t.getBatteryLevel() < 0,
                t -> {
                    t.setBatteryLevel(0);
                    System.out.println("    [Калибровка] " + t.getId() + " -> 0.0%");
                    if (t.isInTransit()) {
                        t.recall();
                        System.out.println("    [Калибровка] " + t.getId()
                                + " экстренно возвращён на базу");
                    }
                });
    }



    static DispatchRule<DeliveryDrone> variant() {
        return new DispatchRule<DeliveryDrone>(
                "Экстренный возврат дрона",
                DeliveryDrone.class,
                d -> d.isInTransit() && d.getBatteryLevel() < 15.0,
                d -> {
                    d.recall();
                    System.out.printf("    [Возврат] Дрон %s (%.1f%%) возвращён на базу%n",
                            d.getId(), d.getBatteryLevel());
                });
    }

    static void analytics() {
        System.out.println("--- ТОП-3 транспорта с наименьшим зарядом ---");
        M.getAnalyticsStream()
                .sorted(Comparator.comparingDouble(Transport::getBatteryLevel))
                .limit(3)
                .forEach(t -> System.out.printf("    %.1f%% — %s%n",
                        t.getBatteryLevel(), t.getDetails()));
    }
}