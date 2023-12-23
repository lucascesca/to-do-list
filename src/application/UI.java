package application;

import entities.Task;
import entities.TaskManager;

import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UI {
    static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("HH:mm");

    public static void menu(TaskManager taskManager, Scanner sc) throws ParseException {
        int validator = 0;
        clearScreen();

        do {
            try {
                System.out.println("1. Adicionar");
                System.out.println("2. Listar");
                System.out.println("3. Remover");
                System.out.println("4. Mudar Status");
                System.out.println("5. Para sair");
                System.out.println();
                System.out.print("Selecione uma das opções: ");
                validator = sc.nextInt();

                clearScreen();
                switch (validator) {
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
                    case 5:
                        break;
                    default:
                        System.out.println("Apenas valores de 1 a 5");
                        System.out.println();
                        break;
                }
            }
            catch (InputMismatchException e) {
                clearScreen();
                sc.nextLine();
                System.err.println("Digite apenas apenas números inteiros!");
                System.out.println();
            }
        } while (validator != 5);
    }

    private static void addTasksMenu(TaskManager tasks, Scanner sc) {
        boolean c = true;
        do {
            try {
                sc.nextLine();
                clearScreen();
                System.out.print("Descrição: ");
                String description = sc.nextLine();
                System.out.print("Data da tarefa: ");
                LocalDate dueDate = LocalDate.parse(sc.nextLine(), fmt);
                while (dueDate.isBefore(LocalDate.now())) {
                    clearScreen();
                    System.out.println("Data deve ser posterior ou igual à atual");
                    System.out.println();
                    System.out.printf("Digite novamente uma data para a tarefa \"%s\": ", description);
                    dueDate = LocalDate.parse(sc.nextLine(), fmt);
                }
                System.out.print("Horário da tarefa: ");
                LocalTime dueTime = LocalTime.parse(sc.nextLine(), fmt2);
                while (dueDate.getDayOfMonth() == LocalDate.now().getDayOfMonth() && dueTime.isBefore(LocalTime.now()) ||
                        dueTime.getMinute() == LocalTime.now().getMinute()) {
                    clearScreen();
                    System.out.println("Horário deve ser posterior ao atual");
                    System.out.println();
                    System.out.printf("Digite novamente um horário para a tarefa \"%s\": ", description);
                    dueTime = LocalTime.parse(sc.nextLine(), fmt2);
                }

                Task task = new Task(description, dueDate, dueTime);
                int i = tasks.addTask(task);

                switch (i) {
                    case 0:
                        System.out.println();
                        System.out.println("Tarefa adicionada com sucesso");

                        System.out.println();

                        System.out.print("Deseja continuar (y/n): ");
                        c = check(sc);
                        break;
                    case 1:
                        System.out.println();
                        System.out.println("Tarefa não adicionada!");
                        System.out.println();
                        System.out.printf("O horário fornecido (%s) na data %s já possui uma tarefa\n", task.getDueDateTime().format(fmt2), task.getDueDateTime().format(fmt));

                        System.out.println();

                        System.out.print("Deseja tentar novamente? (y/n): ");
                        c = check(sc);
                }
            }
            catch (DateTimeException e) {
                System.out.println();
                System.err.println("Valor inválido");
                System.out.println();
                System.out.println("Pressione enter para continuar");
            }

        } while (c);
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

                System.out.println();
                System.out.print("Digite sua escolha: ");
                int ch = sc.nextInt();

                if (ch > 4 || ch < 1) {
                    System.out.println();
                    System.out.println("Somente números de 1 a 4");
                }
                else {
                    tasks = taskManager.getTasks(ch);

                    clearScreen();
                    printTasks(tasks);
                }

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

            taskManager.changeTaskStatus(id, status);

            System.out.println();
            System.out.println("Status alterado com sucesso!");

            System.out.println();
            System.out.println("Deseja continuar (s/n)?");
        } while (check(sc));
    }

    private static void aux(TaskManager tasks, Scanner sc) {
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
