package md.pbl.project.organizationprojectuserapi.service;

import md.pbl.project.organizationprojectuserapi.exceptions.PblCustomException;
import md.pbl.project.organizationprojectuserapi.model.project.ProjectDto;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getByOrg(Long orgId);

    ProjectDto get(Long orgId, Long projId) throws PblCustomException;

    ProjectDto create(Long orgId, ProjectDto dto) throws PblCustomException;

    ProjectDto update(Long orgId, Long projId, ProjectDto dto) throws PblCustomException;

    void delete(Long orgId, Long projId) throws PblCustomException;
}
