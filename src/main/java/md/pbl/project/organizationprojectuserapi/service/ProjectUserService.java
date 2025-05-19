package md.pbl.project.organizationprojectuserapi.service;

import md.pbl.project.organizationprojectuserapi.exceptions.PblCustomException;
import md.pbl.project.organizationprojectuserapi.model.project.ProjectDto;
import md.pbl.project.organizationprojectuserapi.model.user.UserDto;

import java.util.List;

public interface ProjectUserService {
    List<UserDto> getUsersByProject(Long orgId, Long projectId) throws PblCustomException;

    List<ProjectDto> getProjectsByUser(Long orgId, Long userId) throws PblCustomException;

    void assignUserToProject(Long orgId, Long projectId, Long userId, String role) throws PblCustomException;

    void removeUserFromProject(Long orgId, Long projectId, Long userId) throws PblCustomException;


}
