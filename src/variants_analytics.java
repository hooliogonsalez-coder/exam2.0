public class variants_analytics {
    //2,15,23,28
    //static void analytics() {
    //    double sum = M.getAnalyticsStream()
    //            .filter(t -> !t.isInTransit())
    //            .mapToDouble(Transport::getBatteryLevel)
    //            .sum();
    //
    //    System.out.printf("--- Суммарный заряд транспорта на базе: %.1f%% ---%n", sum);
    //}

    //3,16,24,29
    //static void analytics() {
    //    List<String> activeHubs = M.getHubs().entrySet().stream()
    //            .filter(e -> e.getValue().stream()
    //                    .anyMatch(Transport::isInTransit))
    //            .map(Map.Entry::getKey)
    //            .collect(Collectors.toList());
    //
    //    System.out.println("--- Хабы с активным транспортом ---");
    //    activeHubs.forEach(name -> System.out.println("    " + name));

    //4,9,17,30,33
    //static void analytics() {
    //    Map<Boolean, List<Transport>> byStatus = M.getAnalyticsStream()
    //            .collect(Collectors.groupingBy(Transport::isInTransit));
    //    System.out.println("--- Группировка по статусу ---");
    //    List<Transport> inTransit = byStatus.getOrDefault(true, List.of());
    //    List<Transport> onBase    = byStatus.getOrDefault(false, List.of());
    //    System.out.println("В пути (" + inTransit.size() + "):");
    //    inTransit.forEach(t -> System.out.println("    " + t.getDetails()));
    //    System.out.println("На базе (" + onBase.size() + "):");
    //    onBase.forEach(t -> System.out.println("    " + t.getDetails()));
    //}

    //5,10,18,31,34
    //static void analytics() {
    //    Optional<Transport> best = M.getAnalyticsStream()
    //            .filter(Transport::isInTransit)
    //            .max(Comparator.comparingDouble(Transport::getBatteryLevel));
    //
    //    System.out.println("--- Транспорт в пути с максимальным зарядом ---");
    //    if (best.isPresent()) {
    //        Transport t = best.get();
    //        System.out.printf("    %.1f%% — %s%n", t.getBatteryLevel(), t.getDetails());
    //    } else {
    //        System.out.println("    Нет транспорта в пути.");
    //    }
    //}

    //6,11,19,32,35
    //static void analytics() {
    //    Map<String, Long> countByClass = M.getAnalyticsStream()
    //            .collect(Collectors.groupingBy(
    //                    t -> t.getClass().getSimpleName(),
    //                    Collectors.counting()));
    //
    //    System.out.println("--- Количество транспорта по классам ---");
    //    countByClass.forEach((cls, cnt) ->
    //            System.out.printf("    %-20s : %d%n", cls, cnt));
    //}

    //7,12,20,25,36
    //static void analytics() {
    //    boolean exists = M.getAnalyticsStream()
    //            .anyMatch(t -> t.getBatteryLevel() < 5.0);
    //
    //    System.out.println("--- Проверка: есть ли транспорт с зарядом < 5% ---");
    //    System.out.println("    Результат: " + exists);
    //}

    //8,13,21,26
    //static void analytics() {
    //    String result = M.getAnalyticsStream()
    //            .filter(t -> !t.isInTransit())
    //            .map(Transport::getModel)
    //            .collect(Collectors.joining(", "));
    //
    //    System.out.println("--- Модели транспорта на базе ---");
    //    System.out.println("    " + result);
    //}
}
