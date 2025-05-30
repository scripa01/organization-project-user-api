package md.pbl.project.organizationprojectuserapi.rest;

import md.pbl.project.organizationprojectuserapi.exceptions.PblCustomException;
import md.pbl.project.organizationprojectuserapi.model.project.ProjectDto;
import md.pbl.project.organizationprojectuserapi.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/core/organizations/{orgId}/projects")
public class ProjectController {
    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> list(@PathVariable Long orgId) {
        return ResponseEntity.ok(service.getByOrg(orgId));
    }

    @GetMapping("/{projId}")
    public ResponseEntity<ProjectDto> get(@PathVariable Long orgId, @PathVariable Long projId) throws PblCustomException {
        return ResponseEntity.ok(service.get(orgId, projId));
    }

    @PostMapping
    public ResponseEntity<ProjectDto> create(@PathVariable Long orgId, @RequestBody ProjectDto dto) throws PblCustomException {
        return ResponseEntity.status(201).body(service.create(orgId, dto));
    }

    @PutMapping("/{projId}")
    public ResponseEntity<ProjectDto> update(@PathVariable Long orgId, @PathVariable Long projId, @RequestBody ProjectDto dto) throws PblCustomException {
        return ResponseEntity.ok(service.update(orgId, projId, dto));
    }

    @DeleteMapping("/{projId}")
    public ResponseEntity<Void> delete(@PathVariable Long orgId, @PathVariable Long projId) throws PblCustomException {
        service.delete(orgId, projId);
        return ResponseEntity.noContent().build();
    }
}
