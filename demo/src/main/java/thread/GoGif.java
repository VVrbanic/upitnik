package thread;

import com.example.demo.HelloApplication;
import javafx.scene.image.Image;

import java.util.ArrayList;
public class GoGif implements Runnable{
    ArrayList<String> ikone = new ArrayList<String>();
    public static Integer i = 0;
    @Override
    public void run() {
        ikone.add("/ikona1.png");
        ikone.add("/ikona2.png");
        ikone.add("/ikona3.png");
        ikone.add("/ikona4.png");
        i = (i + 1) % 4;
        HelloApplication.ikona = ikone.get(i);
        HelloApplication.getMainStage().getIcons().remove(0);
        HelloApplication.getMainStage().getIcons().add(new Image(HelloApplication.class.getResourceAsStream(HelloApplication.ikona)));
    }

}
