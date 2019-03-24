

package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import java.io.*;
import java.net.URL;
import java.util.*;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXRadioButton DeleteUserButtonOne;

    @FXML
    private JFXRadioButton DeleteUserButtonTwo;

    @FXML
    private JFXRadioButton DeleteUserButtonThree;

    @FXML
    private JFXTextField ListUserToLocText;

    @FXML
    private JFXTextField listUserToCatText;

    @FXML
    private JFXTextField listAllText;

    @FXML
    private JFXTextField ListCatToUserText;

    @FXML
    private Pane KullaniciIslemleriPane;

    @FXML
    private Label introText;

    @FXML
    private Pane kullanıcıIslemleriPane;

    @FXML
    private Pane kategoriIslemleriPane;

    @FXML
    private Pane listelemeIslemleriPane;

    @FXML
    private JFXRadioButton listCatToUserRB;

    @FXML
    private JFXRadioButton ListUserToCatRB;

    @FXML
    private JFXRadioButton ListUserToLocRB;

    @FXML
    private JFXRadioButton ListAllRB;

    @FXML
    private JFXButton CheckButtonOne;

    @FXML
    private JFXButton CheckButtonTwo;

    @FXML
    private JFXButton CheckButtonThree;

    @FXML
    private JFXButton CheckButtonFour;

    @FXML
    private JFXTextField AddCategoryText;

    @FXML
    private JFXTextField DeleteCategoryText;

    @FXML
    private JFXTextField SearchCategoryText;

    @FXML
    private JFXTextField AddUserText;

    @FXML
    private JFXTextField AddUserCatText;

    @FXML
    private JFXTextField AddUserSubCatText;

    @FXML
    private JFXTextField DeleteUserIdText;

    @FXML
    private JFXTextField DeleteUserCatText;

    @FXML
    private JFXTextField AddCategoryText1;

    @FXML
    private JFXButton SearchCategoryDeleteButton;

    @FXML
    private JFXButton SearchCategoryAddButton;

    Reservation reservation = new Reservation();
    ArrayList<Integer> TemporaryArray = new ArrayList<>();

    /**
     * Listeleme işlemlerini yapan fonksiyonlar
     * Dosyadan okunan verileri kullanıcının girdiği değerle
     * kontrol ettikten sonra eşleşme sağladığı verileri dizide
     * tutup tekrarsız bir şekilde yazdırır.
     * @param event
     * @throws IOException
     */

    @FXML
    void ListAll(MouseEvent event) throws IOException {


        String searched = listAllText.getText();
        boolean isEqual = false;
        for (int i = 0; i <reservation.User_id.size() ; i++) {
            if(reservation.User_id.get(i).equals(searched)) {
               /* ListTextField.setText("Aradığınız kullanıcının yaptığı \nrezervasyon bilgileri"
                        + " " + reservation.Location_id.get(i) + " " + reservation.User_Time.get(i) + " "
                        + reservation.Latitute.get(i) + " " + reservation.Longtitute.get(i) + " " + reservation.City.get(i)
                        + reservation.Category.get(i) + reservation.Sub_Category.get(i));
*/
                System.out.println("Aradığınız kullanıcının yaptığı rezervasyon bilgileri : "
                        + "\n" + reservation.Location_id.get(i) + " " + reservation.User_Time.get(i) + " "
                        + reservation.Latitute.get(i) + " " + reservation.Longtitute.get(i) + " " + reservation.City.get(i)
                        + " " + reservation.Category.get(i) + " :" + reservation.Sub_Category.get(i));
                isEqual = true;
            }
        }
        if(!isEqual){
            System.out.println("Aradığınız kullanıcı bulunamadı");
        }


    }

    @FXML
    void ListCatToUser(MouseEvent event) throws IOException {

        String searched = ListCatToUserText.getText();
        Vector<String> TemporaryList = new Vector<>();
        boolean isEqual = false;

        for (int i = 0; i < reservation.User_id.size() ; i++) {
            if(reservation.User_id.get(i).equals(searched)){
                TemporaryList.add(reservation.Category.get(i));
                isEqual = true;
            }
        }
        for (int i = 0; i < TemporaryList.size()-1; i++) {
            for (int j = i+1 ; j < TemporaryList.size() ; j++) {
                if(TemporaryList.get(i).equals(TemporaryList.get(j))){
                    TemporaryArray.add(j);
                }
            }
        }
        Collections.sort(TemporaryArray);
        Set<Integer> junkies = new LinkedHashSet<Integer>(TemporaryArray);
        TemporaryArray.clear();
        TemporaryArray.addAll(junkies);
        for (int i = TemporaryArray.size()-1; i >= 0 ; i--) {
            TemporaryList.removeElementAt(TemporaryArray.get(i));
        }

        System.out.println(searched + " kullanıcısının yaptığı rezervasyon : " + TemporaryList);
        if(!isEqual) System.out.println("Kullanıcı bulunamadı");
        TemporaryList.clear();
        TemporaryArray.clear();
    }

    @FXML
    void ListUserToCat(MouseEvent event) throws IOException {

        String searched = listUserToCatText.getText();
        Vector<String> TemporaryList = new Vector<>();
        boolean isEqual = false;

        for (int i = 0; i < reservation.Category.size() ; i++) {
            if(reservation.Category.get(i).equals(searched)){
                TemporaryList.add(reservation.User_id.get(i));
                isEqual = true;
            }
        }

        for (int i = 0; i < TemporaryList.size()-1; i++) {
            for (int j = i+1 ; j < TemporaryList.size() ; j++) {
                if(TemporaryList.get(i).equals(TemporaryList.get(j))){
                    TemporaryArray.add(j);
                }
            }
        }
        Collections.sort(TemporaryArray);
        Set<Integer> junkies = new LinkedHashSet<Integer>(TemporaryArray);
        TemporaryArray.clear();
        TemporaryArray.addAll(junkies);
        for (int i = TemporaryArray.size()-1; i >= 0 ; i--) {
            TemporaryList.removeElementAt(TemporaryArray.get(i));
        }

        System.out.println(searched + " kategorisinde rezervasyon yapan kullanıcılar : " + TemporaryList);
        if(!isEqual) System.out.println("Kullanıcı bulunamadı");

        TemporaryList.clear();
        TemporaryArray.clear();
        }

    @FXML
    void ListUserToLoc(MouseEvent event) throws  IOException{

        Vector<String> TemporaryList = new Vector<>();
        String searched = ListUserToLocText.getText();
        boolean isEqual = false;

        for (int i = 0; i < reservation.Location_id.size() ; i++) {
            if(reservation.Location_id.get(i).equals(searched)){
                TemporaryList.add(reservation.User_id.get(i));
                isEqual = true;
            }
        }

        for (int i = 0; i < TemporaryList.size()-1; i++) {
            for (int j = i+1 ; j < TemporaryList.size() ; j++) {
                if(TemporaryList.get(i).equals(TemporaryList.get(j))){
                    TemporaryArray.add(j);
                }
            }
        }
        Collections.sort(TemporaryArray);
        Set<Integer> junkies = new LinkedHashSet<Integer>(TemporaryArray);
        TemporaryArray.clear();
        TemporaryArray.addAll(junkies);
        for (int i = TemporaryArray.size()-1; i >= 0 ; i--) {
            TemporaryList.removeElementAt(TemporaryArray.get(i));
        }

        System.out.println(searched + " konumunda rezervasyon yapan kullanıcılar : " + TemporaryList);
        if(!isEqual) System.out.println("Kullanıcı bulunamadı");
        TemporaryList.clear();
        TemporaryArray.clear();
    }

    /**
     * Kategori işlemlerini kapsayan fonksiyonlar
     * @param event
     * @throws IOException
     */
     @FXML
    void AddCategory(ActionEvent event ) throws IOException {
        //Bu kısıma ek olarak düğüme de ekleme yapılacak

        String Category = AddCategoryText.getText();
         boolean temp = false;
         for (int i = 0; i < reservation.Category_T.size(); i++) {
             if(reservation.Category_T.get(i).equals(Category)){
                 temp= true;  break;
             }
         }

        if(!Category.equals("") && !temp) {
            FileWriter fileWriter = new FileWriter("Kategoriler.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            boolean isEqual = false;

            for (int i = 0; i < reservation.Category_T.size(); i++) {
                if (reservation.Category_T.get(i).equals(Category) && Category != "") {
                    isEqual = true;
                    break;
                }
            }


            if (isEqual) System.out.println("Ekleme işlemi başarısız! Lütfen geçerli bir kategori giriniz.");

            else {
                reservation.Category_T.add(Category);
                System.out.println(reservation.Category_T);
                System.out.println("Ekleme işlemi başarılı!");
                int k = reservation.Category_T.size() - 1;
                bufferedWriter.write(reservation.Category_T.get(k) + "\n");
            }
            bufferedWriter.close();
        }else
            System.out.println("Geçersiz kategori.");
    }
    @FXML
    void AddCategory_N(ActionEvent event) throws IOException{
        //Bu kısıma ek olarak düğüme de ekleme yapılacak
        String Category = SearchCategoryText.getText();
        boolean temp = false;
        for (int i = 0; i < reservation.Category_T.size(); i++) {
            if(reservation.Category_T.get(i).equals(Category)){
                temp= true;  break;
            }
        }

        if(!Category.equals("") && !temp) {
            FileWriter fileWriter = new FileWriter("Kategoriler.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            boolean isEqual = false;

            for (int i = 0; i < reservation.Category_T.size(); i++) {
                if (reservation.Category_T.get(i).equals(Category) && Category != "") {
                    isEqual = true;
                    break;
                }
            }


            if (isEqual) System.out.println("Ekleme işlemi başarısız! Lütfen geçerli bir kategori giriniz.");

            else {
                reservation.Category_T.add(Category);
                System.out.println(reservation.Category_T);
                System.out.println("Ekleme işlemi başarılı!");
                int k = reservation.Category_T.size() - 1;
                bufferedWriter.write(reservation.Category_T.get(k) + "\n");
            }
            bufferedWriter.close();
        }else
            System.out.println("Geçersiz kategori.");

    }
    @FXML
    void DeleteCategory(ActionEvent event) throws  IOException {
        String Category = DeleteCategoryText.getText();
        boolean isEqual = false;
        for (int i = 0; i < reservation.Category_T.size(); i++) {
            if(reservation.Category_T.get(i).equals(Category)){
                isEqual= true;  break;
            }
        }

        if(!Category.equals("") && isEqual) {
            System.out.println("Önceki hali : " + reservation.Category_T);
            reservation.Category_T.remove(Category);
            FileWriter fileWriter = new FileWriter(Main.fileTwo, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            FileOutputStream fileOutputStream = new FileOutputStream(Main.fileTwo);


            for (int i = 0; i < reservation.Category_T.size(); i++) {
                if (reservation.Category_T.get(i).equals(Category)) {
                    fileOutputStream.write("".getBytes());
                }
            }
            for (int i = 0; i < reservation.Category_T.size(); i++) {
                bufferedWriter.write(reservation.Category_T.get(i) + "\n");
            }
            System.out.println("Silme işlemi başarılı : " + Category);
            System.out.println("Sonraki : " + reservation.Category_T);
            bufferedWriter.close();
            fileOutputStream.close();
        }else System.out.println("Geçersiz işlem.");
    }
    @FXML
    void DeleteCategory_N(ActionEvent event) throws  IOException {
        String Category = SearchCategoryText.getText();
        boolean isExist = false;
        for (int i = 0; i < reservation.Category_T.size(); i++) {
            if(reservation.Category_T.get(i).equals(Category)){
                isExist= true;  break;
            }
        }

        if(!Category.equals("") && isExist) {
            System.out.println("Önceki hali : " + reservation.Category_T);
            reservation.Category_T.remove(Category);
            FileWriter fileWriter = new FileWriter(Main.fileTwo, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            FileOutputStream fileOutputStream = new FileOutputStream(Main.fileTwo);


            for (int i = 0; i < reservation.Category_T.size(); i++) {
                if (reservation.Category_T.get(i).equals(Category)) {
                    fileOutputStream.write("".getBytes());
                }
            }
            for (int i = 0; i < reservation.Category_T.size(); i++) {
                bufferedWriter.write(reservation.Category_T.get(i) + "\n");
            }
            System.out.println("Silme işlemi başarılı : " + Category);
            System.out.println("Sonraki : " + reservation.Category_T);
            bufferedWriter.close();
            fileOutputStream.close();
        }else System.out.println("Geçersiz işlem.");
    }


    static int check = 0;

     @FXML
    void SearchCategory(ActionEvent event) {

         String searched = SearchCategoryText.getText();
         boolean isExist = false;

         for (int i = 0; i < reservation.Category_T.size(); i++) {
             if(searched.equals(reservation.Category_T.get(i))){
                 isExist=true;  break;
             }

         }
       //Animasyon bloğu
        if(check == 0 && !searched.equals("")) {
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.1), SearchCategoryText);
            translateTransition.setByY(-28);
            translateTransition.play();
            check++;
        }
         if(isExist){
             SearchCategoryAddButton.setVisible(true); SearchCategoryAddButton.setDisable(true);
             SearchCategoryDeleteButton.setVisible(true);SearchCategoryDeleteButton.setDisable(false);
         }else{
             SearchCategoryAddButton.setVisible(true);SearchCategoryAddButton.setDisable(false);
             SearchCategoryDeleteButton.setVisible(true); SearchCategoryDeleteButton.setDisable(true);
         }


    }
    /*****************************************************************/


    /**
     * Dosya işlemlerine ait Kullanıcı ekleme fonksiyonu
     * Arayüzde kullanıcıdan alınan User değerini User_id
     * Stringine atadık ve User_id_T listesinin elemanları
     * ile kontrol ettik. Eğer eşleşme varsa isEqual booleanı
     * kontrolü ile kullanıcıya hata olduğunu ve tekrar
     * denemesi gerektiğini söyledik eşleşme yoksa User_id_T
     * listesinin bir sonraki elemanına eklenip aynı zamanda
     * Kullanıcılar.txt dosyasına yazıldı.
     * @param event
     * @throws IOException
     */

    @FXML
    void AddUser(ActionEvent event) throws IOException {


        String User_id = AddUserText.getText();

        FileWriter fileWriter = new FileWriter("Kullanıcılar.txt",true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        boolean isEqual = false;

        for (int i = 0; i < reservation.User_id_T.size(); i++)
        {
            if(reservation.User_id_T.get(i).equals(User_id))
            {
                isEqual=true; break;
            }
        }

        if(!isEqual && !User_id.equals("")){
            reservation.User_id_T.add(User_id);
            System.out.println(reservation.User_id_T);
            System.out.println("Ekleme işlemi başarılı!");
            int k = reservation.User_id_T.size()-1;
            bufferedWriter.write(reservation.User_id_T.get(k) + "\n");
        }
        else    System.out.println("Ekleme işlemi başarısız! Lütfen geçerli bir isim giriniz.");
          bufferedWriter.close();
          /***********************************************************************************/
    }

    @FXML
    void DeleteUser(ActionEvent event) throws  IOException {
         String searched = DeleteUserIdText.getText();
         String category = DeleteUserCatText.getText();
        boolean isExist = false;
        for (int i = 0; i < reservation.User_id_T.size(); i++) {
            if(reservation.User_id_T.get(i).equals(searched)){
                isExist= true;  break;
            }
        }
        if(DeleteUserButtonThree.isSelected()){

             if(isExist && !searched.equals("")){
                reservation.User_id_T.remove(searched);
                FileWriter fileWriter = new FileWriter(Main.fileOne,true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                FileOutputStream fileOutputStream = new FileOutputStream(Main.fileOne);


                    for (int i = 0; i < reservation.User_id_T.size(); i++) {
                        if(reservation.User_id_T.get(i).equals(searched)){
                            fileOutputStream.write("".getBytes());
                         }
                    }
                    for (int i = 0; i < reservation.User_id_T.size(); i++) {
                        bufferedWriter.write(reservation.User_id_T.get(i) + "\n");
                    }
                        System.out.println("Silme işlemi başarılı : " + searched);
                        System.out.println("Sonraki : " + reservation.User_id_T);
                        bufferedWriter.close();
                        fileOutputStream.close();
             }
             else System.out.println("Silme işlemi başarısız.Tekrar deneyiniz.");
         }
    }




        /**
         * CheckPaneOne,CheckPaneTwo ve CheckPaneThree fonksiyonları
         * Arayüzdeki işlemleri yaparken görsel efektiflik kazandırmak
         * ve isterleri gerçekleştiren butonları koşullara göre yerleştirir.
         */

        @FXML
        void CheckPaneOne(ActionEvent event) {
            kullanıcıIslemleriPane.setVisible(true);
            kategoriIslemleriPane.setVisible(false);
            listelemeIslemleriPane.setVisible(false);
            introText.setVisible(false);

        }

        @FXML
        void CheckPaneTwo(ActionEvent event) {
            kullanıcıIslemleriPane.setVisible(false);
            kategoriIslemleriPane.setVisible(true);
            listelemeIslemleriPane.setVisible(false);
            introText.setVisible(false);
        }

        @FXML
        void CheckPaneThree(ActionEvent event) {
            kullanıcıIslemleriPane.setVisible(false);
            kategoriIslemleriPane.setVisible(false);
            listelemeIslemleriPane.setVisible(true);
            introText.setVisible(false);


        }

    /**
     * ButtonControl ve ButtonControlTwo fonksiyonları
     * RadioButonlarının karışıklığını önlemek amacıyla yazılmıştır.
     */

    @FXML
    public void ButtonControl() {
        if(ListUserToCatRB.isSelected()){
            ListAllRB.setSelected(false);
            ListUserToLocRB.setSelected(false);
            listCatToUserRB.setSelected(false);

            ListUserToLocText.setDisable(true);
            ListCatToUserText.setDisable(true);
            listAllText.setDisable(true);
            listUserToCatText.setDisable(false);

            CheckButtonThree.setDisable(true);
            CheckButtonTwo.setDisable(false);
            CheckButtonOne.setDisable(true);
            CheckButtonFour.setDisable(true);
        }
        else if(ListAllRB.isSelected()){
            ListUserToLocRB.setSelected(false);
            ListUserToCatRB.setSelected(false);
            listCatToUserRB.setSelected(false);

            CheckButtonThree.setDisable(true);
            CheckButtonTwo.setDisable(true);
            CheckButtonOne.setDisable(true);
            CheckButtonFour.setDisable(false);

            ListCatToUserText.setDisable(true);
            ListUserToLocText.setDisable(true);
            listAllText.setDisable(false);
            listUserToCatText.setDisable(true);
        }
        else if(ListUserToLocRB.isSelected()){
            ListAllRB.setSelected(false);
            ListUserToCatRB.setSelected(false);
            listCatToUserRB.setSelected(false);

            CheckButtonThree.setDisable(true);
            CheckButtonTwo.setDisable(true);
            CheckButtonOne.setDisable(false);
            CheckButtonFour.setDisable(true);

            ListCatToUserText.setDisable(true);
            ListUserToLocText.setDisable(false);
            listAllText.setDisable(true);
            listUserToCatText.setDisable(true);

        }else if(listCatToUserRB.isSelected()){
            ListAllRB.setSelected(false);
            ListUserToCatRB.setSelected(false);
            ListUserToLocRB.setSelected(false);

            CheckButtonThree.setDisable(false);
            CheckButtonTwo.setDisable(true);
            CheckButtonOne.setDisable(true);
            CheckButtonFour.setDisable(true);

            ListCatToUserText.setDisable(false);
            ListUserToLocText.setDisable(true);
            listAllText.setDisable(true);
            listUserToCatText.setDisable(true);
        }
    }
    @FXML
    public void ButtonControlTwo() {
        if(DeleteUserButtonOne.isSelected()){
            DeleteUserButtonTwo.setSelected(false);
            DeleteUserButtonThree.setSelected(false);
        }
        if(DeleteUserButtonTwo.isSelected()){
            DeleteUserButtonOne.setSelected(false);
            DeleteUserButtonThree.setSelected(false);
        }
        if(DeleteUserButtonThree.isSelected()){
            DeleteUserButtonOne.setSelected(false);
            DeleteUserButtonTwo.setSelected(false);
        }
    }

    /*********************************************************************/

    @FXML
    void initialize() {
        assert KullaniciIslemleriPane != null : "fx:id=\"KullaniciIslemleriPane\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert introText != null : "fx:id=\"introText\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert kullanıcıIslemleriPane != null : "fx:id=\"kullanıcıIslemleriPane\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert AddUserText != null : "fx:id=\"AddUserText\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert AddUserCatText != null : "fx:id=\"AddUserCatText\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert AddUserSubCatText != null : "fx:id=\"AddUserSubCatText\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert DeleteUserButtonOne != null : "fx:id=\"DeleteUserButtonOne\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert DeleteUserButtonTwo != null : "fx:id=\"DeleteUserButtonTwo\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert DeleteUserButtonThree != null : "fx:id=\"DeleteUserButtonThree\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert DeleteUserIdText != null : "fx:id=\"DeleteUserIdText\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert DeleteUserCatText != null : "fx:id=\"DeleteUserCatText\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert kategoriIslemleriPane != null : "fx:id=\"kategoriIslemleriPane\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert AddCategoryText != null : "fx:id=\"AddCategoryText\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert DeleteCategoryText != null : "fx:id=\"DeleteCategoryText\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert SearchCategoryText != null : "fx:id=\"SearchCategoryText\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert SearchCategoryDeleteButton != null : "fx:id=\"SearchCategoryDeleteButton\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert AddCategoryText1 != null : "fx:id=\"AddCategoryText1\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert SearchCategoryAddButton != null : "fx:id=\"SearchCategoryAddButton\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert listelemeIslemleriPane != null : "fx:id=\"listelemeIslemleriPane\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert listCatToUserRB != null : "fx:id=\"listCatToUserRB\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert ListUserToCatRB != null : "fx:id=\"ListUserToCatRB\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert ListUserToLocRB != null : "fx:id=\"ListUserToLocRB\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert ListAllRB != null : "fx:id=\"ListAllRB\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert ListUserToLocText != null : "fx:id=\"ListUserToLocText\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert listUserToCatText != null : "fx:id=\"listUserToCatText\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert ListCatToUserText != null : "fx:id=\"ListCatToUserText\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert listAllText != null : "fx:id=\"listAllText\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert CheckButtonOne != null : "fx:id=\"CheckButtonOne\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert CheckButtonTwo != null : "fx:id=\"CheckButtonTwo\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert CheckButtonThree != null : "fx:id=\"CheckButtonThree\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";
        assert CheckButtonFour != null : "fx:id=\"CheckButtonFour\" was not injected: check your FXML file 'ProjectInterfaces.fxml'.";

    }

}


