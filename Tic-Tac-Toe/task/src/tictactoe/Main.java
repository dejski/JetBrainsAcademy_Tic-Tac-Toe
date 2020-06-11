package tictactoe;

import java.util.Scanner;

public class Main {
    static String[][] table = {{"_", "_", "_"}, {"_", "_", "_"}, {"_", "_", "_"}};
    static String znak = "X";

    public static void setZnak(String znak) {
        Main.znak = znak;
    }

    public Main(String[][] table) {
        this.table = table;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


//        System.out.print("Enter cells: ");
//        String data = scanner.nextLine();

//        int licznik = 1;

//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                table[i][j] = String.valueOf(data.charAt(licznik - 1));
//                licznik++;
//            }
//        }

        printTable(table);

        while (true) {
            int c1;
            do {
                System.out.print("Enter the coordinates:");
                while (!scanner.hasNextInt()) {
                    System.out.println("You should enter numbers!");
                    scanner.next(); // this is important!
                }
                c1 = scanner.nextInt();
            } while (c1 <= 0);

            int c2;
            do {
//            System.out.print("Enter the coordinates:");
                while (!scanner.hasNextInt()) {
                    System.out.println("You should enter numbers!");
                    scanner.next(); // this is important!
                }
                c2 = scanner.nextInt();
            } while (c2 <= 0);

            check(c1, c2);

            printTable(table);
            if (!getStatus(table).equals("Game not finished")) {
                System.out.println(getStatus(table));
                break;
            }
        }
        getStatus(table);
    }

    private static boolean check(int c1, int c2) {
        int kolumna = 0;
        int wiersz = 0;

        if (c1 == 1) {
            kolumna = 0;
        } else if (c1 == 2) {
            kolumna = 1;
        } else if (c1 == 3) {
            kolumna = 2;
        } else {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        if (c2 == 1) {
            wiersz = 2;
        } else if (c2 == 2) {
            wiersz = 1;
        } else if (c2 == 3) {
            wiersz = 0;
        } else {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

//        System.out.println("Wstaw X: " + wiersz + " i " + kolumna);
//        System.out.println(table[wiersz][kolumna]);
        if (table[wiersz][kolumna].equals("_")) {
            table[wiersz][kolumna] = znak;
            if (znak.equals("X")) {
                setZnak("O");
            } else {
                setZnak("X");
            }
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }

    private static void printTable(String[][] table) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.printf("| %s %s %s |%n", table[i][0], table[i][1], table[i][2]);
        }
        System.out.println("---------");
    }

    private static String getStatus(String[][] table) {
        boolean winO = false;
        boolean winX = false;
        boolean notFinished = false;
        int countO = 0;
        int countX = 0;
        String wynik = "";

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j].equals("X")) {
                    countX++;
                } else if (table[i][j].equals("O")) {
                    countO++;
                }
            }
            if (table[i][0].equals("_") || table[i][1].equals("_") || table[i][2].equals("_")) {
                notFinished = true;
            }
            if (
                    (table[i][0].equals("X") && table[i][1].equals("X") && table[i][2].equals("X")) ||
                            (table[0][i].equals("X") && table[1][i].equals("X") && table[2][i].equals("X")) ||
                            (table[0][0].equals("X") && table[1][1].equals("X") && table[2][2].equals("X")) ||
                            (table[0][0].equals("X") && table[1][1].equals("X") && table[2][2].equals("X")) ||
                            (table[0][2].equals("X") && table[1][1].equals("X") && table[2][0].equals("X"))
            ) {
                winX = true;
            }
            if (
                    (table[i][0].equals("O") && table[i][1].equals("O") && table[i][2].equals("O")) ||
                            (table[0][i].equals("O") && table[1][i].equals("O") && table[2][i].equals("O")) ||
                            (table[0][0].equals("O") && table[1][1].equals("O") && table[2][2].equals("O")) ||
                            (table[0][0].equals("O") && table[1][1].equals("O") && table[2][2].equals("O")) ||
                            (table[0][2].equals("O") && table[1][1].equals("O") && table[2][0].equals("O"))
            ) {
                winO = true;
            }
        }

        if (winO && winX || Math.abs(countO - countX) > 1) {
//            System.out.println("Impossible");
            wynik = "Impossible";
        } else if (winO) {
//            System.out.println("O wins");
            wynik = "O wins";
        } else if (winX) {
//            System.out.println("X wins");
            wynik = "X wins";
        } else if (notFinished) {
//            System.out.println("Game not finished");
            wynik = "Game not finished";
        } else {
//            System.out.println("Draw");
            wynik = "Draw";
        }
        return wynik;
    }
}
