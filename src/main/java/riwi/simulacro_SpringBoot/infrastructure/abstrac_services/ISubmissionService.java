package riwi.simulacro_SpringBoot.infrastructure.abstrac_services;

import riwi.simulacro_SpringBoot.api.dto.requests.SubmissionRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.SubmissionResponse;

public interface ISubmissionService extends CrudService<SubmissionRequest, SubmissionResponse,Long>{
}
