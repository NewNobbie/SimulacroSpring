package riwi.simulacro_SpringBoot.infrastructure.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import riwi.simulacro_SpringBoot.api.dto.requests.MessageRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.CourseResponse;
import riwi.simulacro_SpringBoot.api.dto.responses.MessageResponse;
import riwi.simulacro_SpringBoot.api.dto.responses.UserResponse;
import riwi.simulacro_SpringBoot.domain.entities.Courses;
import riwi.simulacro_SpringBoot.domain.entities.Message;
import riwi.simulacro_SpringBoot.domain.entities.User;
import riwi.simulacro_SpringBoot.domain.repositories.CoursesRepository;
import riwi.simulacro_SpringBoot.domain.repositories.MessageRepository;
import riwi.simulacro_SpringBoot.domain.repositories.UserRepository;
import riwi.simulacro_SpringBoot.infrastructure.abstrac_services.IMessageService;
import riwi.simulacro_SpringBoot.util.exceptions.IdNotFoundException;

import java.time.LocalDate;
import java.util.Date;

@Service
@AllArgsConstructor
public class MessageService implements IMessageService {
    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final CoursesRepository coursesRepository;

    @Override
    public MessageResponse create(MessageRequest request) {
        User userSender = this.userRepository.findById(request.getSenderId()).orElseThrow(()->new IdNotFoundException("userSender"));
        User userReceiver = this.userRepository.findById(request.getReceiverId()).orElseThrow(()->new IdNotFoundException("userReceiver"));
        Courses courses = this.coursesRepository.findById(request.getCoursesId()).orElseThrow(()->new IdNotFoundException("course"));

        Message message = this.requestToEntity(request,new Message());
        message.setSentDate(LocalDate.now());
        message.setCoursesId(courses);
        message.setSenderId(userSender);
        message.setReceiverId(userReceiver);

        return this.entityResponse(this.messageRepository.save(message));
    }

    @Override
    public MessageResponse get(Long aLong) {
        return this.entityResponse(this.find(aLong));
    }

    @Override
    public MessageResponse update(MessageRequest request, Long aLong) {
        Message message = this.find(aLong);
        User userSender = this.userRepository.findById(request.getSenderId()).orElseThrow(()->new IdNotFoundException("userSender"));
        User userReceiver = this.userRepository.findById(request.getReceiverId()).orElseThrow(()->new IdNotFoundException("userReceiver"));
        Courses courses = this.coursesRepository.findById(request.getCoursesId()).orElseThrow(()->new IdNotFoundException("course"));

        Message messageUpdate =this.requestToEntity(request,message);
        messageUpdate.setSentDate(LocalDate.now());
        messageUpdate.setCoursesId(courses);
        messageUpdate.setSenderId(userSender);
        messageUpdate.setReceiverId(userReceiver);

        return this.entityResponse(this.messageRepository.save(messageUpdate));
    }

    @Override
    public void delete(Long aLong) {
        Message message =this.find(aLong);
        this.messageRepository.delete(message);
    }

    @Override
    public Page<MessageResponse> getAll(int page, int size) {
        if (page<0)
            page=0;
        PageRequest pagination = PageRequest.of(page, size);
        return this.messageRepository.findAll(pagination).map(this::entityResponse);
    }
    private Message find( Long id){
        return this.messageRepository.findById(id).orElseThrow(()->new IdNotFoundException("Message"));
    }

    private MessageResponse entityResponse ( Message entity){
        MessageResponse response = new MessageResponse();
        BeanUtils.copyProperties(entity,response);

        CourseResponse courseResponse = new CourseResponse();
        BeanUtils.copyProperties(entity.getCoursesId(),courseResponse);

        UserResponse userCourse = new UserResponse();
        BeanUtils.copyProperties(entity.getCoursesId().getUser(),userCourse);
        courseResponse.setUser(userCourse);

        UserResponse userSender = new UserResponse();
        BeanUtils.copyProperties(entity.getSenderId(),userSender);


        UserResponse userReceiver = new UserResponse();
        BeanUtils.copyProperties(entity.getReceiverId(),userReceiver);
        response.setCoursesId(courseResponse);
        response.setSenderId(userSender);
        response.setReceiverId(userReceiver);

        return response;
    }

    private Message requestToEntity(MessageRequest request, Message message){
        BeanUtils.copyProperties(request,message);
        return message;
    }
}
