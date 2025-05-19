package md.pbl.project.organizationprojectuserapi.service;

import lombok.RequiredArgsConstructor;
import md.pbl.project.organizationprojectuserapi.constants.ErrorCode;
import md.pbl.project.organizationprojectuserapi.exceptions.PblCustomException;
import md.pbl.project.organizationprojectuserapi.model.ObjectMapper;
import md.pbl.project.organizationprojectuserapi.model.project.Project;
import md.pbl.project.organizationprojectuserapi.model.project.ProjectDto;
import md.pbl.project.organizationprojectuserapi.model.user.User;
import md.pbl.project.organizationprojectuserapi.model.user.UserDto;
import md.pbl.project.organizationprojectuserapi.repository.OrganizationRepository;
import md.pbl.project.organizationprojectuserapi.repository.ProjectRepository;
import md.pbl.project.organizationprojectuserapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectUserServiceImpl implements ProjectUserService {
    private final ProjectRepository projectRepo;
    private final UserRepository userRepo;
    private final OrganizationRepository orgRepo;
    private final ObjectMapper mapper;

    @Override
    public List<UserDto> getUsersByProject(Long orgId, Long projectId) throws PblCustomException {
        Project project = projectRepo.findById(projectId)
                .filter(p -> p.getOrganization().getId().equals(orgId))
                .orElseThrow(() -> new PblCustomException("Project does not exist",
                        ErrorCode.PROJECT_DOESNT_EXIST,
                        HttpStatus.NOT_FOUND));
        return project.getMembers().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProjectDto> getProjectsByUser(Long orgId, Long userId) throws PblCustomException {
        User user = userRepo.findById(userId)
                .filter(u -> u.getOrganization().getId().equals(orgId))
                .orElseThrow(() -> new PblCustomException("User does not exist",
                        ErrorCode.USER_DOESNT_EXIST,
                        HttpStatus.NOT_FOUND));
        return user.getProjects().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void assignUserToProject(Long orgId, Long projectId, Long userId, String role) throws PblCustomException {
        Project project = projectRepo.findById(projectId)
                .filter(p -> p.getOrganization().getId().equals(orgId))
                .orElseThrow(() -> new PblCustomException("Project does not exist",
                        ErrorCode.PROJECT_DOESNT_EXIST,
                        HttpStatus.NOT_FOUND));
        User user = userRepo.findById(userId)
                .filter(u -> u.getOrganization().getId().equals(orgId))
                .orElseThrow(() -> new PblCustomException("User does not exist",
                        ErrorCode.USER_DOESNT_EXIST,
                        HttpStatus.NOT_FOUND));
        project.getMembers().add(user);
        user.getProjects().add(project);
        projectRepo.save(project);
    }

    @Transactional
    @Override
    public void removeUserFromProject(Long orgId, Long projectId, Long userId) throws PblCustomException {
        Project project = projectRepo.findById(projectId)
                .filter(p -> p.getOrganization().getId().equals(orgId))
                .orElseThrow(() -> new PblCustomException("Project does not exist",
                        ErrorCode.PROJECT_DOESNT_EXIST,
                        HttpStatus.NOT_FOUND));
        User user = userRepo.findById(userId)
                .filter(u -> u.getOrganization().getId().equals(orgId))
                .orElseThrow(() -> new PblCustomException("User does not exist",
                        ErrorCode.USER_DOESNT_EXIST,
                        HttpStatus.NOT_FOUND));
        project.getMembers().remove(user);
        user.getProjects().remove(project);
        projectRepo.save(project);
    }
}
