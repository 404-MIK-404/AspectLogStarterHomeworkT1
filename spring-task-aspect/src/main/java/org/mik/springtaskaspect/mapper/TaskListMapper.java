package org.mik.springtaskaspect.mapper;


import org.mapstruct.Mapper;
import org.mik.springtaskaspect.model.dto.TaskDto;
import org.mik.springtaskaspect.model.entity.Task;

import java.util.List;

@Mapper(componentModel = "spring",uses = TaskMapper.class)
public interface TaskListMapper {

    List<TaskDto> convertListEntityToListDto(List<Task> listTask);

    List<Task> convertListDtoToListEntity(List<TaskDto> listTaskDto);

}
