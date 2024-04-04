package thread;

import com.example.demo.HelloApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockThread implements Runnable{
    public DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void run() {
        HelloApplication.timeNow = HelloApplication.timeNow.plusSeconds(1);
        HelloApplication.getMainStage().setTitle(simpleDateFormat.format(HelloApplication.timeNow));
    }

}
