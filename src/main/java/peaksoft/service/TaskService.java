package peaksoft.service;

import peaksoft.model.Task;

import java.util.List;

public interface TaskService {
    void saveTask(Long id, Task task);//

    void updateTask(Task task,Long id);//

    List<Task> getAllTasks(Long id);//

    Task getTaskById(Long id);//

    void deleteTask(Long id);//
}
