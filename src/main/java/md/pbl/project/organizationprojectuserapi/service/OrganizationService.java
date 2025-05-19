package md.pbl.project.organizationprojectuserapi.service;

import md.pbl.project.organizationprojectuserapi.exceptions.PblCustomException;
import md.pbl.project.organizationprojectuserapi.model.organization.OrganizationDto;
import md.pbl.project.organizationprojectuserapi.model.organization.OrganizationFullDto;

import java.util.List;

public interface OrganizationService {
    List<OrganizationDto> getAll();

    OrganizationDto get(Long id) throws PblCustomException;

    OrganizationFullDto getFullDto(Long id) throws PblCustomException;

    OrganizationDto create(OrganizationDto dto);

    OrganizationDto update(Long id, OrganizationDto dto) throws PblCustomException;

    void delete(Long id);
}
