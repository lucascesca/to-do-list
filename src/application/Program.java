package application;

import entities.TaskManager;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        UI.menu(taskManager, sc);

        sc.close();
    }
}
