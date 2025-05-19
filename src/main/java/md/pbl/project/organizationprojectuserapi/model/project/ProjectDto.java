package md.pbl.project.organizationprojectuserapi.model.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {
    Long id;
    String name;
    String description;
    Long organizationId;
    OffsetDateTime createdAt = OffsetDateTime.now();

}
