package md.pbl.project.organizationprojectuserapi.model.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String username;
    @Email
    private String email;
    private String fullName;
    private OffsetDateTime createdAt = OffsetDateTime.now();

}
