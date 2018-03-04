package pizzaShop.temp;

import pizzaShop.entity.CategorizedItem;
import pizzaShop.entity.Category;
;

public class TempClass {
    public static void main(String[] args) {

        CategorizedItem categorizedItem = new CategorizedItem();
        Category category = new Category();
        category.setName("ars");
        categorizedItem.setCategory(category);
        System.out.println(categorizedItem);;



////        LocalDate date = LocalDate.now();
////        System.out.println(date);
////        System.out.println(date.getMonthValue());
////        System.out.println(date.getMonth());
////        System.out.println(date.getMonth().getValue());
////        System.out.println(date.getDayOfWeek());
////        System.out.println(date.getDayOfWeek().getValue());
////        System.out.println(date.getDayOfWeek().plus(15));
////        System.out.println(date.getDayOfYear());
////        System.out.println(date.getDayOfMonth());
////      //  ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);
////        LocalTime time = LocalTime.now(ZoneId.of("Etc/GMT-3"));
////        System.out.println(time);
////        System.out.println(time.getSecond());
////        System.out.println(time.getNano());
////        System.out.println(time.getHour());
////        System.out.println(time.getMinute());
////        LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("Etc/GMT-3"));
////        System.out.println(dateTime);
    }
}
