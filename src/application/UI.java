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

        do {
            System.out.println("1. Adicionar");
            System.out.println("2. Listar");
            System.out.println("3. Remover");
            System.out.println("4. Mudar Status");
            System.out.println("5. Para sair");
            System.out.println();
            System.out.print("Selecione uma das opções: ");
            ch = sc.nextInt();
            clearScreen();

            switch (ch) {
                case 1:
                    addTasksMenu(tasks, sc);
                    break;
                case 2:
                    do
                        printTasksMenu(tasks);
                    while (check(sc));
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

    private static void addTasksMenu(List<Task> tasks, Scanner sc) throws ParseException {
        do {
            System.out.print("Descrição: ");
            sc.nextLine();
            String description = sc.nextLine();
            System.out.print("Data da tarefa: ");
            Date date = sdf.parse(sc.next());

            tasks.add(new Task(description, date));

            System.out.println();
            System.out.println("Tarefa adicionada com sucesso");
            System.out.println();

            System.out.print("Deseja continuar (y/n): ");
        } while (check(sc));
    }

    private static void printTasksMenu(List<Task> tasks) {
        if (!tasks.isEmpty()) {
            for (Task t : tasks) {
                System.out.println(t);
            }
        } else System.out.println("Nada para listar");

        System.out.println();
        System.out.print("Deseja listar novamente (y/n)? ");
    }

    private static boolean check(Scanner sc) {
        char ch = sc.next().charAt(0);
        clearScreen();

        return Character.toLowerCase(ch) != 'n';
    }

    //https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
