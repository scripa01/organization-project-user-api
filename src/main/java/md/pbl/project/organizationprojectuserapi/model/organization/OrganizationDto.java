package md.pbl.project.organizationprojectuserapi.model.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrganizationDto {
    Long id;
    String name;
    String description;
    OffsetDateTime createdAt = OffsetDateTime.now();
}
