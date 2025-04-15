package org.mik.springtaskaspect.repository;

import org.mik.springtaskaspect.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> { }
