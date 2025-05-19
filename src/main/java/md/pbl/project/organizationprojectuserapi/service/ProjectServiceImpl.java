package md.pbl.project.organizationprojectuserapi.service;

import lombok.RequiredArgsConstructor;
import md.pbl.project.organizationprojectuserapi.constants.ErrorCode;
import md.pbl.project.organizationprojectuserapi.exceptions.PblCustomException;
import md.pbl.project.organizationprojectuserapi.model.ObjectMapper;
import md.pbl.project.organizationprojectuserapi.model.organization.Organization;
import md.pbl.project.organizationprojectuserapi.model.project.Project;
import md.pbl.project.organizationprojectuserapi.model.project.ProjectDto;
import md.pbl.project.organizationprojectuserapi.repository.OrganizationRepository;
import md.pbl.project.organizationprojectuserapi.repository.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projRepo;
    private final OrganizationRepository orgRepo;
    private final ObjectMapper mapper;

    public List<ProjectDto> getByOrg(Long orgId) {
        return projRepo.findByOrganizationId(orgId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public ProjectDto get(Long orgId, Long projectId) throws PblCustomException {
        Project p = projRepo.findById(projectId)
                .filter(pr -> pr.getOrganization().getId().equals(orgId))
                .orElseThrow(() -> new PblCustomException("Project does not exist",
                        ErrorCode.PROJECT_DOESNT_EXIST,
                        HttpStatus.NOT_FOUND));
        return mapper.toDto(p);
    }

    @Transactional
    public ProjectDto create(Long orgId, ProjectDto dto) throws PblCustomException {
        Organization org = orgRepo.findById(orgId).orElseThrow(() -> new PblCustomException("Organization does not exist",
                ErrorCode.ORGANISATION_DOESNT_EXIST,
                HttpStatus.NOT_FOUND));
        Project p = mapper.toEntity(dto);
        p.setOrganization(org);
        return mapper.toDto(projRepo.save(p));
    }

    @Transactional
    public ProjectDto update(Long orgId, Long projectId, ProjectDto dto) throws PblCustomException {
        Project projectFromDb = projRepo.findById(projectId)
                .filter(pr -> pr.getOrganization().getId().equals(orgId))
                .orElseThrow(() -> new PblCustomException("Project does not exist",
                        ErrorCode.PROJECT_DOESNT_EXIST,
                        HttpStatus.NOT_FOUND));
        mapper.updateEntityFromDto(dto, projectFromDb);
        return mapper.toDto(projRepo.save(projectFromDb));
    }

    @Transactional
    public void delete(Long orgId, Long projectId) throws PblCustomException {
        projRepo.delete(getEntity(orgId, projectId));
    }

    private Project getEntity(Long orgId, Long projectId) throws PblCustomException {
        return projRepo.findById(projectId)
                .filter(pr -> pr.getOrganization().getId().equals(orgId))
                .orElseThrow(() -> new PblCustomException("Project does not exist",
                        ErrorCode.PROJECT_DOESNT_EXIST,
                        HttpStatus.NOT_FOUND));
    }
}
