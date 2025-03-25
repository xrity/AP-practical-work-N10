import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static String[][] db = new String[32][2];

    public static void dbInitial() {
        for (int i = 0; i < db.length; i++) {
            for (int j = 0; j < db[i].length; j++) {
                db[i][j] = "0";
            }
        }
    }

    public static boolean addPerson(String login, String password) {

        for (int i = 0; i < db.length; i++) {
            if (db[i][0].equals(login)) {
                System.out.println("Login already used");
                return false;
            }
        }

        for (int i = 0; i < db.length; i++) {
            if (db[i][0].equals("0")) {
                db[i][0] = login;
                db[i][1] = password;
                System.out.println(i);
                System.out.println("Done");
                return true;
            }
        }

        System.out.println("Try again");
        return false;
    }

    public static boolean deletePerson(String login) {

        for (int i = 0; i < db.length; i++) {
            if (db[i][0].equals(login)) {
                db[i][0] = "0";
                db[i][1] = "0";
                System.out.println("Done");
                return true;
            }
        }
        System.out.println("Any matches");
        return false;
    }

    public static String loginEnter() {
        String result;

        do {
            System.out.println("Enter login");
            result = sc.nextLine();

            if (result.length() < 5) {
                System.out.println("too short");
                continue;
            }
            if (result.indexOf(" ") != -1) {
                System.out.println("rewrite with out spaces");
                continue;
            }

            break;
        }
        while (true);


        return result;
    }

    public static String passwordEnter() {
        String result;
        String specialSymbols = "!@#$%^&*()-+=<>?/\\|{}[]:;.,";
        String[] blockedPasswords = {"123456", "abcdef", "letmein"};

        while (true) {
            System.out.println("Enter password");
            result = sc.nextLine();

            if (result.length() < 10) {
                System.out.println("too short");
                continue;
            }

            if (result.contains(" ")) {
                System.out.println("rewrite with out spaces");
                continue;
            }

            boolean hasSpecial = false;
            for (char c : result.toCharArray()) {
                if (specialSymbols.contains(String.valueOf(c))) {
                    hasSpecial = true;
                    break;
                }
            }
            if (!hasSpecial) {
                System.out.println("symbols must be");
                continue;
            }

            int digitCount = 0;
            for (char c : result.toCharArray()) {
                if (Character.isDigit(c)) {
                    digitCount++;
                }
            }
            if (digitCount < 3) {
                System.out.println("< 3 numbers");
                continue;
            }

            String lowerCaseResult = result.toLowerCase();
            for (String blocked : blockedPasswords) {
                if (lowerCaseResult.equals(blocked.toLowerCase())) {
                    System.out.println("Этот пароль запрещён: " + blocked);
                    continue;
                }
            }

            return result;
        }
    }

    public static boolean singUp(String login, String password){
        for (int i = 0; i < db.length; i++) {
            if (db[i][0].equals(login) && db[i][1].equals(password)) {
                System.out.println("Done");
                return true;
            }
        }

        System.out.println("Try again");
        return false;
    }

    public static void main(String[] args) {
        dbInitial();
        while (true){
            String login;
            String password;
            System.out.println("1. ADD");
            System.out.println("2. DELETE");
            System.out.println("3. LIST");
            switch (sc.nextInt()){
                case 1:
                    sc.nextLine();
                    login = loginEnter();
                    password = passwordEnter();

                    addPerson(login, password);
                    break;

                case 2:
                    sc.nextLine();
                    do{
                        System.out.println("Sign up");
                        login = loginEnter();
                        password = passwordEnter();
                    }
                    while (!singUp(login, password));

                    login = loginEnter();
                    deletePerson(login);
                    break;

                case 3:
                    sc.nextLine();
                    for(int i=0; i< db.length; i++){
                        System.out.println(db[i][0] + " - " + db[i][1]);
                    }
                    break;
            }

        }

    }
}