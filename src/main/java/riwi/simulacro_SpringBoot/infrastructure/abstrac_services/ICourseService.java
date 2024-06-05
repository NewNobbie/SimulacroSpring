package riwi.simulacro_SpringBoot.infrastructure.abstrac_services;

import riwi.simulacro_SpringBoot.api.dto.requests.CourseRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.CourseResponse;

// 7 
public interface ICourseService extends CrudService<CourseRequest,CourseResponse,Long>{
}
