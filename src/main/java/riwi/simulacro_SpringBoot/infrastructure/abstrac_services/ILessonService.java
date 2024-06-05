package riwi.simulacro_SpringBoot.infrastructure.abstrac_services;

import riwi.simulacro_SpringBoot.api.dto.requests.LessonRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.LessonResponse;

public interface ILessonService extends CrudService<LessonRequest, LessonResponse,Long>{
}
