package logic;

import models.Transport;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LogisticManager {
    private static final String FILE = "logistic_state.dat";
    private Map<String, List<Transport>> hubs = new LinkedHashMap<>();
    private List<DispatchRule<? extends Transport>> rules = new ArrayList<>();

    public void addTransport(String hub, Transport t) {
        List<Transport> list = hubs.get(hub);
        if (list == null) {
            list = new ArrayList<>();
            hubs.put(hub, list);
        }
        list.add(t);
    }

    public void addRule(DispatchRule<?> r) {
        rules.add(r);
    }

    public Stream<Transport> getAnalyticsStream() {
        return hubs.values().stream().flatMap(List::stream);
    }

    public Transport getTransportById(String id) {
        return getAnalyticsStream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void appluAllRules() {
        for (List<Transport> list : hubs.values()) {
            for (Transport t : list) {
                for (DispatchRule rule : rules) {
                    Class c = rule.getTargetClass();
                    if (c == null || c.isInstance(t)) {
                        rule.apply(t);
                    }
                }
            }
        }
    }

    public void saveState() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(hubs);
            System.out.println("Состояние сохранено в " + FILE);
        } catch (Exception e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    public void loadState() {
        File f = new File(FILE);
        if (!f.exists()) {
            System.out.println("Файл сохранения не найдён");
            return;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))){
            hubs = (Map<String, List<Transport>>) in.readObject();
            System.out.println("Состояние загружено из " + FILE);
        } catch (Exception e) {
            System.out.println("Ошибка загрузки: " + e.getMessage());
        }
    }
    public boolean isEmpty(){
        return hubs.isEmpty();
    }
    public Map<String,List<Transport>> getHubs(){return hubs;}
}