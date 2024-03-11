package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Task;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class MySqlTaskDAO extends MySqlDao implements TaskDaoInterface {
    @Override
    public List<Task> getAllTasks() throws DaoException {
        List<Task> tasksList = new ArrayList<>();
        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tasks");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String status = resultSet.getString("status");
                String priority = resultSet.getString("priority");
                String description = resultSet.getString("description");
                java.sql.Date dueDate = resultSet.getDate("due_date");

                Task task = new Task(id, title, status, priority, description, dueDate);
                tasksList.add(task);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in getAllTasks(): " + e.getMessage());
        }
        return tasksList;
    }

}