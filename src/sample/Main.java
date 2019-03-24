package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;

public class Main extends Application {

   public static File fileOne = new File("Kullanıcılar.txt");
   public static File fileTwo = new File ("Kategoriler.txt");
    public static File fileThree = new File ("AltKategoriler.txt");
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ProjectInterfaces.fxml"));
        primaryStage.setTitle("Proje-4");
        primaryStage.setScene(new Scene(root, 718, 530));
        primaryStage.show();
    }


    @Override
    public void init() throws Exception {
        super.init();
        Reservation reservation = new Reservation();
        reservation.ReadFile("rezervasyon.txt");
        reservation.WriteCategories();
        reservation.WriteUsers();
        reservation.WriteSubCategories();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        FileOutputStream fileOutputStream = new FileOutputStream(fileOne);
        FileOutputStream fileOutputStream1 = new FileOutputStream(fileTwo);
        FileOutputStream fileOutputStream2 = new FileOutputStream(fileThree);
        fileOutputStream1.write(("").getBytes());  fileOutputStream.write(("").getBytes()); fileOutputStream2.write(("").getBytes());
        fileOutputStream.close(); fileOutputStream1.close();fileOutputStream2.close();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
