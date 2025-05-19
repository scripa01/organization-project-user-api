package md.pbl.project.organizationprojectuserapi.rest;

import md.pbl.project.organizationprojectuserapi.exceptions.PblCustomException;
import md.pbl.project.organizationprojectuserapi.model.user.UserDto;
import md.pbl.project.organizationprojectuserapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/core/organizations/{orgId}/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> list(@PathVariable Long orgId) {
        return ResponseEntity.ok(service.getByOrg(orgId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> get(@PathVariable Long orgId, @PathVariable Long userId) throws PblCustomException {
        return ResponseEntity.ok(service.get(orgId, userId));
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@PathVariable Long orgId, @RequestBody UserDto dto) throws PblCustomException {
        return ResponseEntity.status(201).body(service.create(orgId, dto));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> update(@PathVariable Long orgId, @PathVariable Long userId, @RequestBody UserDto dto) throws PblCustomException {
        return ResponseEntity.ok(service.update(orgId, userId, dto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long orgId, @PathVariable Long userId) throws PblCustomException {
        service.delete(orgId, userId);
        return ResponseEntity.noContent().build();
    }
}
