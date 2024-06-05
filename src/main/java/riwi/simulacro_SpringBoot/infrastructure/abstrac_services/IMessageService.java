package riwi.simulacro_SpringBoot.infrastructure.abstrac_services;

import riwi.simulacro_SpringBoot.api.dto.requests.MessageRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.MessageResponse;

public interface IMessageService extends CrudService<MessageRequest, MessageResponse,Long> {
}
