import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class DB {


    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int x = 0;
        while (true) {
            System.out.print("What do you want to do?\n" +
                    "1. Load Data\n" +
                    "2. Language\n" +
                    "3. Age\n" +
                    "4. Special\n" +
                    "5. Exit\n");
            int val = scan.nextInt();
            if (val == 5) {
                return;
            } else if (val == 1 && x == 0) {
                openDB();
                x = 1;
            } else if (val == 2 && x == 1) {// later.

            } else if (val == 3 && x == 1) {//later.

            } else if (val == 4 && x == 1) {// later

            }
            else if (val == 1 && x ==1){
                System.out.println("cant load more than once");
            }
            else if (val > 1 && val <= 4 && x == 0) {
                System.out.println("data base was not created please enter 1 first");
            } else {
                System.out.println("enter a correct number.");
            }
        }

    }

    static void openDB() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        String drop = "drop table SOF";
        String sql = "create table SOF ("
                + "ind text primary key,"
                + "MainBranch text,"
                + "Employment text,"
                + "Country text,"
                + "Age1stCode text,"
                + "LearnCode text,"
                + "Years text,"
                + "LanguageHaveWorkedWith text,"
                + "Age text,"
                + "gender text"
                + ")";
        try {
            Statement st = c.createStatement();
            st.executeUpdate(drop);// remove later !!!!!!!<<<<<<<<<<<<<<<<<<<
            st.executeUpdate(sql);
            System.out.println("updating... ");
        } catch (Exception e) {
            System.out.println("table already exists");
        }
        fill_DB(c);

    }
    static void fill_DB(Connection c) {
        String fName = "mewo.csv";
        int batchsize = 100;
        try {
            String sql = "INSERT INTO SOF (ind , MainBranch, Employment, Country, Age1stCode, LearnCode," +
                    "Years, LanguageHaveWorkedWith, Age , gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = c.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(fName));
            String lineText;

            int count = 0;

            lineText = lineReader.readLine();
            while ((lineText = lineReader.readLine()) != null) {
                String[] data1 = new String[10];
                data1 = lineText.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                String[] data = new String[10];
                for(int i = 0; i < data1.length; i++){
                    data[i] = data1[i];
                }
                for(int i = data1.length;i < 10; i ++){
                    data[i] = "";
                }
                String ind = data[0];
                String MainBranch = data[1];
                String Employment = data[2];
                String Country = data[3];
                String Age1stCode = data[4];
                String LearnCode = data[5];
                String Years = data[6];
                String LanguageHaveWorkedWith = data[7];
                String Age = data[8];
                String gender = data[9];
                statement.setString(1, ind);
                statement.setString(2,MainBranch);
                statement.setString(3,Employment);
                statement.setString(4,Country);
                statement.setString(5,Age1stCode);
                statement.setString(6,LearnCode);
                statement.setString(7,Years);
                statement.setString(8,LanguageHaveWorkedWith);
                statement.setString(9,Age);
                statement.setString(10,gender);

                statement.addBatch();
                statement.executeBatch();
            }

            lineReader.close();
            statement.executeBatch();
            c.close();

        } catch (SQLException e ) {
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println(e);
        }
    }



    static void print_languages(Connection c) {





    }

}

