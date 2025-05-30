package md.pbl.project.organizationprojectuserapi.service;

import md.pbl.project.organizationprojectuserapi.exceptions.PblCustomException;
import md.pbl.project.organizationprojectuserapi.model.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getByOrg(Long orgId);

    UserDto get(Long orgId, Long userId) throws PblCustomException;

    UserDto create(Long orgId, UserDto dto) throws PblCustomException;

    UserDto update(Long orgId, Long userId, UserDto dto) throws PblCustomException;

    void delete(Long orgId, Long userId) throws PblCustomException;

    List<UserDto> getByUsername(String username) throws PblCustomException;
}
