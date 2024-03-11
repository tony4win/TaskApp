package com.dkit.oop.sd2.TaskObjects;

import com.dkit.oop.sd2.DAOs.MySqlTaskDAO;
import com.dkit.oop.sd2.DAOs.TaskDaoInterface;
import com.dkit.oop.sd2.DTOs.Task;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;
import java.util.Scanner;


public class TaskApp {

    public static void main(String[] args) {
        TaskDaoInterface taskDao = new MySqlTaskDAO();
        TaskApp taskApp = new TaskApp(taskDao);

        while (true) {
            taskApp.displayMenu();
            taskApp.handleMenu();
        }
    }
    
    Scanner sc = new Scanner(System.in);
    private TaskDaoInterface taskDao;

    public TaskApp(TaskDaoInterface taskDao) {
        this.taskDao = taskDao;
    }

    public void displayMenu() {
        System.out.println("1. Display All Tasks");
        System.out.println("99. Exit");
    }

    public void handleMenu() {

        String choice = sc.nextLine();

        switch (choice) {
            case "1":
                displayAllTasks();
                break;
            case "99":
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
        }
    }

    private void displayAllTasks() {
        try {
            List<Task> allTasks = taskDao.getAllTasks();
            System.out.println("All Tasks:");
            displayTasks(allTasks);
        } catch (DaoException e) {
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }
    }

    private void displayTasks(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

}


