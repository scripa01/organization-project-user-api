package md.pbl.project.organizationprojectuserapi.model;

import md.pbl.project.organizationprojectuserapi.model.organization.Organization;
import md.pbl.project.organizationprojectuserapi.model.organization.OrganizationDto;
import md.pbl.project.organizationprojectuserapi.model.organization.OrganizationFullDto;
import md.pbl.project.organizationprojectuserapi.model.project.Project;
import md.pbl.project.organizationprojectuserapi.model.project.ProjectDto;
import md.pbl.project.organizationprojectuserapi.model.user.User;
import md.pbl.project.organizationprojectuserapi.model.user.UserDto;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Component
public interface ObjectMapper {
    // Organization mappings
    OrganizationDto toDto(Organization organization);

    Organization toEntity(OrganizationDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(OrganizationDto dto, @MappingTarget Organization entity);

    // Project mappings
    @Mapping(source = "organization.id", target = "organizationId")
    ProjectDto toDto(Project project);

    Project toEntity(ProjectDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ProjectDto dto, @MappingTarget Project entity);

    // User mappings
    UserDto toDto(User user);

    User toEntity(UserDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UserDto dto, @MappingTarget User entity);

    // List mappings
    List<OrganizationDto> toOrganizationDtoList(List<Organization> organizations);

    List<ProjectDto> toProjectDtoList(List<Project> projects);

    List<UserDto> toUserDtoList(List<User> users);

    @Mapping(source = "users", target = "userDtoSet")
    @Mapping(source = "projects", target = "projectDtoSet")
    OrganizationFullDto toDto(Organization orgRepoById, Set<User> users, Set<Project> projects);

}
