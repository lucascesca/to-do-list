package application;

import entities.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UI {
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void menu(Scanner sc) throws ParseException {
        List<Task> tasks = new ArrayList<>();
        int ch;
        System.out.println("Bem-vindo, selecione uma das opções:");

        do {
            System.out.println("1. Adicionar");
            System.out.println("2. Listar");
            System.out.println("3. Remover");
            System.out.println("4. Mudar Status");
            System.out.println("5. Para sair");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    //addTasksMenu(tasks, sc);
                    break;
                case 2:
                    UI.clearScreen();
                    //UI.printTasksMenu(tasks);
                    break;
                case 3:
                    //removeTaskMenu(tasks, sc);
                    break;
                case 4:
                    //statusMenu(tasks, sc);
                    break;
                default:
                    break;
            }
        } while (ch != 5);
    }

    //https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
