package application;

import entities.Task;
import entities.TaskManager;
import entities.enums.Status;

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

    public static void menu(TaskManager taskManager, Scanner sc) {
        int validator = 0;
        do {
            clearScreen();
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
                    System.out.printf("Data deve ser posterior ou igual à %s\n", LocalDate.now().format(fmt));
                    System.out.println();
                    System.out.printf("Digite novamente uma data para a tarefa \"%s\": ", description);
                    dueDate = LocalDate.parse(sc.nextLine(), fmt);
                    clearScreen();
                }
                System.out.print("Horário da tarefa: ");
                LocalTime dueTime = LocalTime.parse(sc.nextLine(), fmt2);
                while (dueDate.equals(LocalDate.now()) && dueTime.isBefore(LocalTime.now())
                        || dueTime.getMinute() == LocalTime.now().getMinute()) {
                    clearScreen();
                    System.out.printf("Horário deve ser posterior a %s\n", LocalTime.now().format(fmt2));
                    System.out.println();
                    System.out.printf("Digite novamente um horário para a tarefa \"%s\": ", description);
                    dueTime = LocalTime.parse(sc.nextLine(), fmt2);
                }

                Task task = new Task(description, dueDate, dueTime);
                boolean i = tasks.addTask(task);

                if (i) {
                    clearScreen();
                    System.out.printf("Tarefa \"%s\" adicionada com sucesso\n", task.getDescription());

                    System.out.println();

                    System.out.print("Deseja adicionar novamente (s/n)? ");
                    c = userChoice(sc);
                } else {
                    System.out.println();
                    System.out.println("Tarefa não adicionada!");
                    System.out.println();
                    task = tasks.getSpecificTask(task);
                    System.out.printf("Tarefa \"%s\" já está agendada para %s às %s.\n",
                            task.getDescription(), task.getDueDateTime().format(fmt), task.getDueDateTime().format(fmt2));

                    System.out.println();

                    System.out.print("Deseja tentar novamente (s/n)? ");
                    c = userChoice(sc);
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

    private static void printTasksMenu(TaskManager taskManager, Scanner sc) {
        do {
            List<Task> tasks = taskManager.getTasks();
            if (tasks.isEmpty()) {
                addShortcut(taskManager, sc);
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
                    if (tasks.isEmpty()) {
                        System.out.println("Esta lista está vazia");
                    }
                    else {
                        printTasks(tasks);
                    }
                }

                System.out.println();
                System.out.print("Deseja listar novamente (s/n)? ");
            }
        } while (userChoice(sc));
    }

    private static void printTasks(List<Task> tasks) {
        for (Task t : tasks) {
            System.out.println(t + translateStatus(t.getStatus()));
        }
    }

    private static void removeTaskMenu(TaskManager taskManager, Scanner sc) {
        do {
            try {
                if (taskManager.getTasks().isEmpty()) {
                    addShortcut(taskManager, sc);
                    break;
                } else {

                    printTasks(taskManager.getTasks());

                    System.out.println();
                    System.out.print("Digite o ID da tarefa para deletar: ");
                    int id = sc.nextInt();

                    clearScreen();
                    System.out.printf("Tarefa \"%s\" removida com sucesso!\n", taskManager.removeTask(id).getDescription());

                    System.out.println();
                    System.out.print("Deseja continuar (s/n)? ");
                }
            } catch (NullPointerException e) {
                System.out.println("ID digitado inexistente!");
                System.out.println();
                System.out.print("Deseja tentar novamente (s/n)? ");
            }
        } while (userChoice(sc));
    }

    private static void statusMenu(TaskManager taskManager, Scanner sc) {
        do {
            try {
                if (taskManager.getTasks().isEmpty()) {
                    addShortcut(taskManager, sc);
                    break;
                }
                else {
                    System.out.println("Escolha o id da tarefa desejada:");
                    printTasks(taskManager.getTasks());
                    int id = sc.nextInt();
                    clearScreen();
                    System.out.printf("Escolha o novo status para a tarefa \"%s\":\n", taskManager.getTasks().get(id - 1).getDescription());
                    System.out.println("1. Realizada");
                    System.out.println("2. Em andamento");
                    System.out.println("3. Pendente");
                    int status = sc.nextInt();
                    clearScreen();

                    taskManager.changeTaskStatus(id, status);

                    System.out.printf("Status de \"%s\" alterado com sucesso!\n", taskManager.getTasks().get(id - 1).getDescription());

                    System.out.println();
                    System.out.print("Deseja continuar (s/n)? ");
                }
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("O valor digitado não está disponível!");
                System.out.println();
                System.out.print("Deseja tentar novamente (s/n)? ");
            }

        } while (userChoice(sc));
    }

    private static void addShortcut(TaskManager taskManager, Scanner sc) {
        //Offers a shortcut for addTaskMenu() if list is empty when functions from 2 to 4 are called.
        System.out.println("A lista está vazia");
        System.out.println();
        System.out.print("Digite 1 para adicionar uma tarefa ou 0 para voltar: ");
        int ch = sc.nextInt();
        if (ch == 1) {
            addTasksMenu(taskManager, sc);
        }
    }

    private static boolean userChoice(Scanner sc) {
        char ch = sc.next().charAt(0);
        clearScreen();

        return Character.toLowerCase(ch) != 'n';
    }

    private static String translateStatus(Status status) {
        switch (status) {
            case DONE:
                return "Realizada";
            case ONGOING:
                return "Em andamento";
            default:
                break;
        }
        return "Pendente";
    }

    //https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
