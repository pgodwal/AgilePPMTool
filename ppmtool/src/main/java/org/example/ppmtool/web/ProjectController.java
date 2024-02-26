package org.example.ppmtool.web;

import jakarta.validation.Valid;
import org.example.ppmtool.domain.Project;
import org.example.ppmtool.services.MapValidationErrorService;
import org.example.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping ("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){

        ResponseEntity<?> erroMap = mapValidationErrorService.MapValidationErrorService(result);
        if(erroMap!=null) return erroMap;

        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public  ResponseEntity<?> getProjectById(@PathVariable String projectId){
        Project project= projectService.findProjectByIdentifier(projectId.toUpperCase());
        return new ResponseEntity<Project>(project,HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
      projectService.deleteProjectByIdentifier(projectId);

      return  new ResponseEntity<String>("Project with ID: '"+projectId+"' was deleted", HttpStatus.OK);
    }
}
