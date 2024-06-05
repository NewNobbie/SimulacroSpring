package riwi.simulacro_SpringBoot.infrastructure.abstrac_services;

import riwi.simulacro_SpringBoot.api.dto.requests.AssignmentRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.AssignmentResponse;

//27
public interface IAssignmentService extends CrudService<AssignmentRequest,AssignmentResponse,Long> {
    
}
