package md.pbl.project.organizationprojectuserapi.model.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import md.pbl.project.organizationprojectuserapi.model.project.ProjectDto;
import md.pbl.project.organizationprojectuserapi.model.user.UserDto;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrganizationFullDto extends OrganizationDto {
    Set<UserDto> userDtoSet;
    Set<ProjectDto> projectDtoSet;
}
