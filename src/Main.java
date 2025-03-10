import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static String[][] db = new String[32][2];

    public static void dbInitial(){
        for(int i=0; i < db.length; i++){
            for(int j=0; j < db[i].length; j++){
                db[i][j] = "0";
            }
        }
    }

    public static boolean addPerson(String login, String password){

        for(int i=0; i<db.length; i++){
            if(db[i][0].equals(login)){
                return false;
            }
        }

        for(int i=0; i<db.length; i++){
            if(db[i][0] == null){
                db[i][0] = login;
                db[i][1] = password;
                return true;
            }
        }

        return false;
    }


    public static boolean deletePerson(String login){

        for(int i=0; i<db.length; i++){
            if(db[i][0].equals(login)){
                db[i][0] = "0";
                db[i][1] = "0";
                return true;
            }
        }
        return false;
    }

    public static String inputData(String message){
        String result;
        System.out.println(message);

        do {
            result = sc.nextLine().trim();
        } while (result.isEmpty());

        return result;
    }

    public static void main(String[] args) {
        while (true){
            String login;
            String password;
            System.out.println("1. ADD");
            System.out.println("2. DELETE");
            System.out.println("3. LIST");
            switch (sc.nextInt()){
                case 1:
                    login = inputData("Login");
                    password = inputData("Password");

                    addPerson(login, password);
                    break;
                case 2:
                    login = inputData("Login");
                    deletePerson(login);
                    break;


                case 3:
                    for(int i=0; i< db.length; i++){
                        System.out.println(db[i][0] + " - " + db[i][1]);
                    }
                    break;
            }

        }

    }
}