package org.example.ppmtool.services;

import org.example.ppmtool.domain.Project;
import org.example.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public Project saveOrUpdateProject (Project project){
        //Logic

        return projectRepository.save(project);
    }


}
