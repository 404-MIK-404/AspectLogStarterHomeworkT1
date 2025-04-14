package org.mik.springtaskaspect.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mik.springtaskaspect.enums.TaskStatusEnum;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private Long id;

    private String title;

    private String description;

    private TaskStatusEnum statusName;

    private Long idUser;

}
