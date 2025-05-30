package md.pbl.project.organizationprojectuserapi.service;

import lombok.RequiredArgsConstructor;
import md.pbl.project.organizationprojectuserapi.constants.ErrorCode;
import md.pbl.project.organizationprojectuserapi.exceptions.PblCustomException;
import md.pbl.project.organizationprojectuserapi.model.ObjectMapper;
import md.pbl.project.organizationprojectuserapi.model.organization.Organization;
import md.pbl.project.organizationprojectuserapi.model.user.User;
import md.pbl.project.organizationprojectuserapi.model.user.UserDto;
import md.pbl.project.organizationprojectuserapi.repository.OrganizationRepository;
import md.pbl.project.organizationprojectuserapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final OrganizationRepository orgRepo;
    private final ObjectMapper mapper;

    public List<UserDto> getByOrg(Long orgId) {
        return userRepo.findByOrganizationId(orgId).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public UserDto get(Long orgId, Long userId) throws PblCustomException {
        User u = userRepo.findById(userId)
                .filter(usr -> usr.getOrganization().getId().equals(orgId))
                .orElseThrow(() -> new PblCustomException("User does not exist",
                        ErrorCode.USER_DOESNT_EXIST,
                        HttpStatus.NOT_FOUND));
        return mapper.toDto(u);
    }

    @Transactional
    public UserDto create(Long orgId, UserDto dto) throws PblCustomException {
        Organization org = orgRepo.findById(orgId).orElseThrow(() -> new PblCustomException("Organisation does not exist",
                ErrorCode.ORGANISATION_DOESNT_EXIST,
                HttpStatus.NOT_FOUND));
        User u = mapper.toEntity(dto);
        u.setOrganization(org);
        return mapper.toDto(userRepo.save(u));
    }

    @Transactional
    public UserDto update(Long orgId, Long userId, UserDto dto) throws PblCustomException {
        User userFromDb = userRepo.findById(userId)
                .filter(usr -> usr.getOrganization().getId().equals(orgId))
                .orElseThrow(() -> new PblCustomException("User does not exist",
                        ErrorCode.USER_DOESNT_EXIST,
                        HttpStatus.NOT_FOUND));
        mapper.updateEntityFromDto(dto, userFromDb);
        return mapper.toDto(userRepo.save(userFromDb));
    }

    @Transactional
    public void delete(Long orgId, Long userId) throws PblCustomException {
        userRepo.delete(getEntity(orgId, userId));
    }

    @Override
    public List<UserDto> getByUsername(String username) {
        User userByUsernameOrId = userRepo.findUserByUsername(username);
        if (userByUsernameOrId == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(mapper.toDto(userByUsernameOrId));

    }

    private User getEntity(Long orgId, Long userId) throws PblCustomException {
        return userRepo.findById(userId)
                .filter(usr -> usr.getOrganization().getId().equals(orgId))
                .orElseThrow(() -> new PblCustomException("User does not exist",
                        ErrorCode.USER_DOESNT_EXIST,
                        HttpStatus.NOT_FOUND));
    }
}
