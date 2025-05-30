package md.pbl.project.organizationprojectuserapi.model.user;

import jakarta.persistence.*;
import lombok.*;
import md.pbl.project.organizationprojectuserapi.model.organization.Organization;
import md.pbl.project.organizationprojectuserapi.model.project.Project;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private String email;
    private String fullName;
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToMany(mappedBy = "members")
    private Set<Project> projects;
    private Role role;

    public User(String username, String email, String fullName, OffsetDateTime createdAt, Organization organization, Set<Project> projects, Role role) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.createdAt = createdAt;
        this.organization = organization;
        this.projects = projects;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(fullName, user.fullName) && Objects.equals(createdAt, user.createdAt) && Objects.equals(organization, user.organization) && Objects.equals(projects, user.projects) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, fullName, createdAt, organization, projects, role);
    }
}
