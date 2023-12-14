package application;

import entities.Task;
import entities.TaskManager;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UI {
    static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void menu(TaskManager taskManager, Scanner sc) throws ParseException {
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
                    addTasksMenu(taskManager, sc);
                    break;
                case 2:
                    printTasksMenu(taskManager, sc);
                    break;
                case 3:
                    removeTaskMenu(taskManager, sc);
                    break;
                case 4:
                    statusMenu(taskManager, sc);
                    break;
                default:
                    break;
            }
        } while (ch != 5);
    }

    private static void addTasksMenu(TaskManager tasks, Scanner sc) throws ParseException {
        do {
            System.out.print("Descrição: ");
            sc.nextLine();
            String description = sc.nextLine();
            System.out.print("Data e hora da tarefa: ");
            LocalDateTime date = LocalDateTime.parse(sc.nextLine(), fmt);

            tasks.addTask(new Task(description, date));

            System.out.println();
            System.out.println("Tarefa adicionada com sucesso");
            System.out.println();

            System.out.print("Deseja continuar (y/n): ");
        } while (check(sc));
    }

    private static void printTasksMenu(TaskManager taskManager, Scanner sc) throws ParseException {
        do {
            List<Task> tasks = taskManager.getTasks();
            if (tasks.isEmpty()) {
                printTasks(tasks);
                System.out.println();
                aux(taskManager, sc);
                break;
            }
            else {
                System.out.println("Quais tarefas deseja listar?");
                System.out.println("1. Realizadas");
                System.out.println("2. Em andamento");
                System.out.println("3. Pendentes");
                System.out.println("4. Todas");
                System.out.print("Digite sua escolha: ");
                int ch = sc.nextInt();

                tasks = (ch == 4) ? tasks : taskManager.getTasks(ch);

                printTasks(tasks);

                System.out.println();
                System.out.print("Deseja listar novamente (y/n)? ");
            }
        } while (check(sc));
    }

    private static void printTasks(List<Task> tasks) {
        if (!tasks.isEmpty()) {
            for (Task t : tasks) {
                System.out.println(t);
            }
        } else System.out.println("A lista está vazia");
    }

    private static void removeTaskMenu(TaskManager tasks, Scanner sc) throws ParseException {
        if (tasks.getTasks().isEmpty()) {
            aux(tasks, sc);
        }
        else {
            do {
                printTasks(tasks.getTasks());

                System.out.print("Digite o ID da tarefa para deletar: ");
                int id = sc.nextInt();
                tasks.removeTask(id);

                System.out.println();
                System.out.println("Deseja continuar (y/n)? ");
            } while (check(sc));
        }
    }

    private static void statusMenu(TaskManager taskManager, Scanner sc) {
        do {
            System.out.println("Escolha o id da tarefa desejada:");
            printTasks(taskManager.getTasks());
            int id = sc.nextInt();
            System.out.println();
            System.out.println("Escolha o novo status para a tarefa:");
            System.out.println("1. Realizada");
            System.out.println("2. Em andamento");
            System.out.println("3. Pendente");
            int status = sc.nextInt();

            taskManager.changeTaskStatus(id, status - 1);

            System.out.println();
            System.out.println("Status alterado com sucesso!");

            System.out.println();
            System.out.println("Deseja continuar (s/n)?");
        } while (check(sc));
    }

    private static void aux(TaskManager tasks, Scanner sc) throws ParseException {
        System.out.print("Digite 1 para adicionar uma tarefa ou 0 para voltar: ");
        int ch = sc.nextInt();
        if (ch == 1) {
            addTasksMenu(tasks, sc);
        }
    }

    private static boolean check(Scanner sc) {
        // Verifies if the user wants to keep doing the command
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
