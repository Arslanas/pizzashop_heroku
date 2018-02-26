package pizzaShop.temp;

import pizzaShop.pojo.Address;
import pizzaShop.pojo.Contact;;import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

public class TempClass {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT-3"));
        System.out.println(calendar.getTime());
//        LocalDate date = LocalDate.now();
//        System.out.println(date);
//        System.out.println(date.getMonthValue());
//        System.out.println(date.getMonth());
//        System.out.println(date.getMonth().getValue());
//        System.out.println(date.getDayOfWeek());
//        System.out.println(date.getDayOfWeek().getValue());
//        System.out.println(date.getDayOfWeek().plus(15));
//        System.out.println(date.getDayOfYear());
//        System.out.println(date.getDayOfMonth());
//      //  ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);
//        LocalTime time = LocalTime.now(ZoneId.of("Etc/GMT-3"));
//        System.out.println(time);
//        System.out.println(time.getSecond());
//        System.out.println(time.getNano());
//        System.out.println(time.getHour());
//        System.out.println(time.getMinute());
//        LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("Etc/GMT-3"));
//        System.out.println(dateTime);
    }
}
