package org.example.ppmtool.services;

import org.example.ppmtool.domain.Backlog;
import org.example.ppmtool.domain.Project;
import org.example.ppmtool.exceptions.ProjectIDException;
import org.example.ppmtool.repositories.BacklogRepository;
import org.example.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;
    public Project saveOrUpdateProject (Project project){
        //Logic
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if (project.getId()==null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }
            if(project.getId()!=null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }


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

 public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
 }

 public void deleteProjectByIdentifier(String projectId ){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null){
            throw new ProjectIDException("Cannot find Project with ID '"+projectId+"'. This project does not exist");
        }
        projectRepository.delete(project);
 }
}

