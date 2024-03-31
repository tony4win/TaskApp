package com.dkit.oop.sd2.TaskObjects;

import com.dkit.oop.sd2.DAOs.MySqlTaskDAO;
import com.dkit.oop.sd2.DAOs.TaskDaoInterface;
import com.dkit.oop.sd2.DTOs.Task;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;
import java.util.Scanner;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

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
        Task addedTask = null;

        switch (choice) {
            case "1":
                displayAllTasks();
                break;
            case "2":
                addedTask = insertTask();
                System.out.println("Added Task: "+ addedTask);
                break;
            case "99":
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
        }
    }

    /* Feature 1 - Get All Tasks
    *  author - Jianfeng
    *  */
    private void displayAllTasks() {
        try {
            List<Task> allTasks = taskDao.getAllTasks();
            System.out.println("All Tasks:");
            displayTasks(allTasks);
        } catch (DaoException e) {
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }
    }

    /* Grab all Tasks from List*/
    private void displayTasks(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    /* Get Date from User to Insert
    *  for Feature 4
    *  author - Tony
    * */

    public Date getDate(){
                
        System.out.println("Enter the date (YYYY-MM-DD):");

        String dateString = input.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;

        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        }

        //return date;
        return new Date();
    }

    /* Feature 4 - inserting Task
    *  author - Tony
    * */
    private Task insertTask() {

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
        Date due_date = getDate();
        System.out.println(due_date+"++++++");

        Task newTask = new Task(title, status, priority, description, due_date);

        try {
            taskDao.insertTask(newTask);
            System.out.println("Task inserted successfully!");
        } catch (DaoException e) {
            System.out.println("Error inserting task: " + e.getMessage());
        }

        return newTask;
    }
}


