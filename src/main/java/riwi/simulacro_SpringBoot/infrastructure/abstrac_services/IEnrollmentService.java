package riwi.simulacro_SpringBoot.infrastructure.abstrac_services;

import riwi.simulacro_SpringBoot.api.dto.requests.EnrollmentRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.EnrollmentResponse;

public interface IEnrollmentService extends CrudService<EnrollmentRequest, EnrollmentResponse,Long>{
}
