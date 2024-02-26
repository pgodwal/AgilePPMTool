package org.example.ppmtool.repositories;

import org.example.ppmtool.domain.Backlog;
import org.example.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepository extends CrudRepository <ProjectTask, Long> {
}
