package riwi.simulacro_SpringBoot.infrastructure.abstrac_services;

import riwi.simulacro_SpringBoot.api.dto.requests.UserRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.UserResponse;

public interface IUserService extends CrudService<UserRequest, UserResponse,Long> {

}
