package sample;


import java.io.*;
import java.util.*;

public class Reservation {

    /**
     * Dosyalardan alınması gereken verilerin tanımlandığı kod bloğu
     * Tanımlarken vektör yapısı kullanılmıştır
     */

    public static Vector<String> User_id = new Vector<>();
    public static Vector<String> Location_id = new Vector<>();
    public static Vector<String> User_Time = new Vector<>();
    public static Vector<String> Latitute = new Vector<>();
    public static Vector<String> Longtitute = new Vector<>();
    public static Vector<String> City = new Vector<>();
    public static Vector<String> Category = new Vector<>();
    public static Vector<String> Sub_Category = new Vector<>();
    public static Vector<String> User_id_T= new Vector<>();
    public static Vector<String> Category_T = new Vector<>();
    public static Vector<String> Sub_Category_T = new Vector<>();
    static ArrayList<Integer> junk = new ArrayList<>();
    static ArrayList<Integer> junkies = new ArrayList<>();
    static ArrayList<Integer> junkie_Sub = new ArrayList<>();


    /**
     *
     * @param fileName
     * @throws IOException
     */

    public static void ReadFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;


        //Virgül görene kadar tüm değerler vektörlere iliklendi
        while (bufferedReader.readLine()!=null){
            line = bufferedReader.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(line,",");

            User_id.add(stringTokenizer.nextToken());
            Location_id.add(stringTokenizer.nextToken());
            User_Time.add(stringTokenizer.nextToken());
            Latitute.add(stringTokenizer.nextToken());
            Longtitute.add(stringTokenizer.nextToken());
            City.add(stringTokenizer.nextToken());
            Category.add(stringTokenizer.nextToken(":").replace(",",""));
            Sub_Category.add(stringTokenizer.nextToken());

        }

        bufferedReader.close();


        /**
         * Kullanıcı ağacına eklenecek olan tekrarsız liste
         */

        User_id_T =(Vector) User_id.clone();
        for (int i = 0; i < User_id.size()-1; i++) {
            for (int j = i+1; j < User_id.size(); j++) {
                if(User_id.get(i).equals(User_id.get(j)) && i != j)
                {
                    junkies.add(j);
                }
            }
        }
        Collections.sort(junkies);
        Set<Integer> junks = new LinkedHashSet<Integer>(junkies);
        junkies.clear();
        junkies.addAll(junks);
        for (int i = junkies.size()-1; i >= 0 ; i--) {
            User_id_T.removeElementAt(junkies.get(i));
        }
    /**********************************************************************/

        /**
         * Kategori ağacına eklenecek olan tekrarsız liste
         */

        Category_T =(Vector) Category.clone();
        for (int i = 0; i < Category.size()-1; i++) {
            for (int j = i+1; j < Category.size(); j++) {
                if(Category.get(i).equals(Category.get(j)) && i != j)
                {
                        junk.add(j);
                }
            }
        }
        Collections.sort(junk);
        Set<Integer> junkie = new LinkedHashSet<Integer>(junk);
        junk.clear();
        junk.addAll(junkie);
        for (int i = junk.size()-1; i >= 0 ; i--) {
            Category_T.removeElementAt(junk.get(i));
        }

    /**************************************************************/

    /**
     * Alt kategori ağacına eklenecek olan tekrarsız liste
     */


        Sub_Category_T =(Vector) Sub_Category.clone();
        for (int i = 0; i < Sub_Category.size()-1; i++) {
            for (int j = i+1; j < Sub_Category.size(); j++) {
                if(Sub_Category.get(i).equals(Sub_Category.get(j)) && i != j)
                {
                    junkie_Sub.add(j);
                }
            }
        }
        Collections.sort(junkie_Sub);
        Set<Integer> junkies = new LinkedHashSet<Integer>(junkie_Sub);
        junkie_Sub.clear();
        junkie_Sub.addAll(junkies);
        for (int i = junkie_Sub.size()-1; i >= 0 ; i--) {
            Sub_Category_T.removeElementAt(junkie_Sub.get(i));
        }
    /*************************************************************/


    }

    public static void WriteUsers() throws IOException{
        FileWriter fileWriter = new FileWriter("Kullanıcılar.txt",true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        Reservation reservation = new Reservation();
        reservation.ReadFile("rezervasyon.txt");

        Vector<String> UserList;

        UserList = (Vector) User_id_T.clone();//kalınan yer

        for (int i = 0; i < UserList.size(); i++) {
            bufferedWriter.write(UserList.get(i) + "\n");
        }

        bufferedWriter.close();
    }

    public static void WriteCategories()throws IOException{
        FileWriter fileWriter = new FileWriter("Kategoriler.txt",true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        Reservation reservation = new Reservation();
        reservation.ReadFile("rezervasyon.txt");

        for (int i = 0; i < Category_T.size(); i++) {
            bufferedWriter.write(Category_T.get(i) + "\n");
        }
            bufferedWriter.close();
    }

    public static void WriteSubCategories() throws  IOException{
        FileWriter fileWriter = new FileWriter("AltKategoriler.txt",true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        Reservation reservation = new Reservation();
        reservation.ReadFile("rezervasyon.txt");

        for (int i = 0; i < Sub_Category_T.size(); i++) {
            bufferedWriter.write(Sub_Category_T.get(i) + "\n");
        }
        bufferedWriter.close();
    }
}
