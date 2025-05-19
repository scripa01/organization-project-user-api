package md.pbl.project.organizationprojectuserapi.model.organization;

import jakarta.persistence.*;
import lombok.*;
import md.pbl.project.organizationprojectuserapi.model.project.Project;
import md.pbl.project.organizationprojectuserapi.model.user.User;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "organizations")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Organization {
    @Id
    @SequenceGenerator(name = "organization_sequence", sequenceName = "organization_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_sequence")
    private Long id;
    private String name;
    private String description;
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Project> projects = new HashSet<>();

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users = new HashSet<>();
}
