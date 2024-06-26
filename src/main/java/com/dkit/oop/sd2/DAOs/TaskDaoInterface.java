package com.dkit.oop.sd2.DAOs;

import com.dkit.oop.sd2.DTOs.Task;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.util.List;

public interface TaskDaoInterface {

    /* Feature 1 - Jienfang */
    List<Task> getAllTasks() throws DaoException;

    /* Feature 2 - Megan */
    Task getTaskById(int taskId) throws DaoException;

    /* Feature 3 - Megan */
    public Task deleteTaskById (int taskId ) throws DaoException;

    /* Feature 4 - Tony */
    Task insertTask(Task newTask) throws DaoException;
}

