package application;

import java.text.ParseException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        UI.menu(sc);

        sc.close();
    }
}
