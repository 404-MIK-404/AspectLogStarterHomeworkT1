package org.mik.springtaskaspect.service;

import lombok.AllArgsConstructor;
import org.mik.springtaskaspect.mapper.TaskListMapper;
import org.mik.springtaskaspect.mapper.TaskMapper;
import org.mik.springtaskaspect.model.dto.TaskDto;
import org.mik.springtaskaspect.model.entity.Task;
import org.mik.springtaskaspect.repository.TaskRepository;
import org.mik.starterhomeworkaspect.aspect.annotation.LoggingAroundTrackingExecution;
import org.mik.starterhomeworkaspect.aspect.annotation.LoggingBeforeTrackingExecution;
import org.mik.starterhomeworkaspect.aspect.annotation.LoggingReturnTrackingExecution;
import org.mik.starterhomeworkaspect.aspect.annotation.LoggingThrowTrackingExecution;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    private final TaskListMapper taskListMapper;



    @LoggingAroundTrackingExecution
    public List<TaskDto> listTask() {
        return taskListMapper.convertListEntityToListDto(taskRepository.findAll());
    }

    @LoggingBeforeTrackingExecution
    public TaskDto taskById(Long idTask){
        return taskMapper.convertEntityToDto(
                taskRepository.findById(idTask).orElseThrow(()->new RuntimeException("Задача не найдена !"))
        );
    }

    @LoggingThrowTrackingExecution
    public TaskDto updateTask(Long taskId, TaskDto taskDTO){
        Task task = taskMapper.convertDtoToEntity(taskDTO);
        return taskMapper.convertEntityToDto(taskRepository.save(task));
    }

    @LoggingThrowTrackingExecution
    public TaskDto createTask(TaskDto taskDTO) {
        Task task = taskMapper.convertDtoToEntity(taskDTO);
        return taskMapper.convertEntityToDto(taskRepository.save(task));
    }


    @LoggingReturnTrackingExecution
    public boolean deleteTaskById(Long idTask) {
        if (taskRepository.existsById(idTask)){
            taskRepository.deleteById(idTask);
            return true;
        } else {
            return false;
        }
    }


}
