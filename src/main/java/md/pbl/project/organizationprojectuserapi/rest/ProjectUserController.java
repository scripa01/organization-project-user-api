package md.pbl.project.organizationprojectuserapi.rest;

import md.pbl.project.organizationprojectuserapi.exceptions.PblCustomException;
import md.pbl.project.organizationprojectuserapi.model.project.ProjectDto;
import md.pbl.project.organizationprojectuserapi.model.user.UserDto;
import md.pbl.project.organizationprojectuserapi.service.ProjectUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/core/organizations/{orgId}/project-users")
public class ProjectUserController {
    private final ProjectUserService service;

    public ProjectUserController(ProjectUserService service) {
        this.service = service;
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<List<UserDto>> getUsersByProject(@PathVariable Long orgId, @PathVariable Long projectId) throws PblCustomException {
        return ResponseEntity.ok(service.getUsersByProject(orgId, projectId));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ProjectDto>> getProjectsByUser(@PathVariable Long orgId, @PathVariable Long userId) throws PblCustomException {
        return ResponseEntity.ok(service.getProjectsByUser(orgId, userId));
    }

    @PostMapping("/projects/{projectId}/users/{userId}")
    public ResponseEntity<Void> assignUser(@PathVariable Long orgId, @PathVariable Long projectId, @PathVariable Long userId, @RequestParam String role) throws PblCustomException {
        service.assignUserToProject(orgId, projectId, userId, role);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/projects/{projectId}/users/{userId}")
    public ResponseEntity<Void> removeUser(@PathVariable Long orgId, @PathVariable Long projectId, @PathVariable Long userId) throws PblCustomException {
        service.removeUserFromProject(orgId, projectId, userId);
        return ResponseEntity.noContent().build();
    }
}
