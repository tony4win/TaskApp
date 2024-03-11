package com.dkit.oop.sd2.TaskObjects;

import com.dkit.oop.sd2.DAOs.MySqlTaskDAO;
import com.dkit.oop.sd2.DAOs.TaskDaoInterface;
import com.dkit.oop.sd2.DTOs.Task;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;
import java.util.Scanner;
import java.util.Date;


public class TaskApp {

    Scanner input = new Scanner(System.in);
    private TaskDaoInterface taskDao;
    public TaskApp(TaskDaoInterface taskDao) {
        this.taskDao = taskDao;
    }


    public static void main(String[] args) {
        TaskDaoInterface taskDao = new MySqlTaskDAO();
        TaskApp taskApp = new TaskApp(taskDao);

        while (true) {
            taskApp.displayMenu();
            taskApp.handleMenu();
        }
    }

    public void displayMenu() {
        System.out.println("1. Display All Tasks");
        System.out.println("2. Insert Task");
        System.out.println("99. Exit");
    }

    public void handleMenu() {

        String choice = input.nextLine();

        switch (choice) {
            case "1":
                displayAllTasks();
                break;
            case "2":
                insertTask();
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

    /*Feature 4 - inserting Task */
    private void insertTask() {

        System.out.println("Task Title:");
        String title = input.nextLine();

        System.out.println("Task Status (DONE,PROGRESS,OPEN) :");
        String status = input.nextLine();

        System.out.println("Task Priority (CRITICAL, HIGH, MEDIUM, LOW, MIN) :");
        String priority = input.nextLine();

        System.out.println("Task Description:");
        String description = input.nextLine();

        /* Change to allow user to insert Date
        * will need to use a Parser */
        Date due_date = new Date();

        Task newTask = new Task(title, status, priority, description, due_date);

        try {
            taskDao.insertTask(newTask);
            System.out.println("Task inserted successfully!");
        } catch (DaoException e) {
            System.out.println("Error inserting task: " + e.getMessage());
        }
    }
}


