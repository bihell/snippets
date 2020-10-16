import java.sql.Timestamp;
import java.time.LocalDate;

public class TimeExample2 {

    public static void main(String[] args) {

        //  LocalDate to Timestamp
        LocalDate now = LocalDate.now();
        Timestamp timestamp = Timestamp.valueOf(now.atStartOfDay());

        System.out.println(now);        // 2019-06-14
        System.out.println(timestamp);  // 2019-06-14 00:00:00.0

        //  Timestamp to LocalDate
        LocalDate localDate = timestamp.toLocalDateTime().toLocalDate();
        System.out.println(localDate);  // 2019-06-14

    }
}