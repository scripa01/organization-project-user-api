package md.pbl.project.organizationprojectuserapi.repository;

import md.pbl.project.organizationprojectuserapi.model.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
