package org.example.ppmtool.services;

import org.example.ppmtool.domain.Project;
import org.example.ppmtool.exceptions.ProjectIDException;
import org.example.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public Project saveOrUpdateProject (Project project){
        //Logic

        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIDException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }


    }
public Project findProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null){
            throw new ProjectIDException("project does not exists");

        }
return project;

 }

}

