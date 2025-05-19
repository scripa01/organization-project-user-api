package md.pbl.project.organizationprojectuserapi.service;

import lombok.RequiredArgsConstructor;
import md.pbl.project.organizationprojectuserapi.constants.ErrorCode;
import md.pbl.project.organizationprojectuserapi.exceptions.PblCustomException;
import md.pbl.project.organizationprojectuserapi.model.ObjectMapper;
import md.pbl.project.organizationprojectuserapi.model.organization.Organization;
import md.pbl.project.organizationprojectuserapi.model.organization.OrganizationDto;
import md.pbl.project.organizationprojectuserapi.model.organization.OrganizationFullDto;
import md.pbl.project.organizationprojectuserapi.repository.OrganizationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository orgRepo;
    private final ObjectMapper mapper;

    public List<OrganizationDto> getAll() {
        return orgRepo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public OrganizationDto get(Long id) throws PblCustomException {
        Organization orgRepoById = orgRepo.findById(id).orElseThrow(() -> new PblCustomException("Organization does not exist",
                ErrorCode.ORGANISATION_DOESNT_EXIST,
                HttpStatus.NOT_FOUND));
        return mapper.toDto(orgRepoById);
    }

    public OrganizationFullDto getFullDto(Long id) throws PblCustomException {
        Organization orgRepoById = orgRepo.findById(id).orElseThrow(() -> new PblCustomException("Organization does not exist",
                ErrorCode.ORGANISATION_DOESNT_EXIST,
                HttpStatus.NOT_FOUND));
        return mapper.toDto(orgRepoById, orgRepoById.getUsers(), orgRepoById.getProjects());
    }

    @Transactional
    public OrganizationDto create(OrganizationDto dto) {
        return mapper.toDto(orgRepo.save(mapper.toEntity(dto)));
    }

    @Transactional
    public OrganizationDto update(Long id, OrganizationDto dto) throws PblCustomException {
        Organization orgFromDb = orgRepo.findById(id).orElseThrow(() -> new PblCustomException("Organization does not exist",
                ErrorCode.ORGANISATION_DOESNT_EXIST,
                HttpStatus.NOT_FOUND));
        mapper.updateEntityFromDto(dto, orgFromDb);
        return mapper.toDto(orgRepo.save(orgFromDb));
    }

    @Transactional
    public void delete(Long id) {
        orgRepo.deleteById(id);
    }
}
