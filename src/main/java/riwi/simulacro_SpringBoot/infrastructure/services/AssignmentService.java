package riwi.simulacro_SpringBoot.infrastructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import riwi.simulacro_SpringBoot.api.dto.requests.AssignmentRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.AssignmentResponse;
import riwi.simulacro_SpringBoot.api.dto.responses.LessonResponse;
import riwi.simulacro_SpringBoot.api.dto.responses.UserResponse;
import riwi.simulacro_SpringBoot.domain.entities.Assignment;
import riwi.simulacro_SpringBoot.domain.entities.Lesson;
import riwi.simulacro_SpringBoot.domain.repositories.AssignmentRepository;
import riwi.simulacro_SpringBoot.domain.repositories.LessonRepository;
import riwi.simulacro_SpringBoot.infrastructure.abstrac_services.IAssignmentService;
import riwi.simulacro_SpringBoot.util.exceptions.IdNotFoundException;

import java.time.LocalDateTime;

//28.
@Service
@AllArgsConstructor
public class AssignmentService implements IAssignmentService {

    // 29
    @Autowired
    private final AssignmentRepository assignmentRepository;

    @Autowired
    private final LessonRepository lessonRepository;

    // 34 Create
    @Override
    public AssignmentResponse create(AssignmentRequest request) {
        Assignment assignment = this.requestToAssignment(request, new Assignment());

        Lesson lesson = this.lessonRepository.findById(request.getLesson())
                .orElseThrow(() -> new IdNotFoundException("lesson"));

        assignment.setDueDate(LocalDateTime.now());
        assignment.setLesson(lesson);

        return this.entityResponse(this.assignmentRepository.save(assignment));
    }

    // 36 Get
    @Override
    public AssignmentResponse get(Long aLong) {
        return this.entityResponse(this.find(aLong));
    }

    //38 Update
    @Override
    public AssignmentResponse update(AssignmentRequest request, Long id) {
        Assignment assignment = this.find(id);
        Assignment assignmentUpdate = this.requestToAssignment(request, assignment);

        Lesson lesson = this.lessonRepository.findById(request.getLesson()).orElseThrow(()-> new IdNotFoundException("Lesson"));
        assignmentUpdate.setLesson(lesson);

        return this.entityResponse(this.assignmentRepository.save(assignmentUpdate));

    }

    // 35 Delete
    @Override
    public void delete(Long id) {
        Assignment assignment = this.find(id);
        this.assignmentRepository.delete(assignment);
    }

    //37 Get All
    @Override
    public Page<AssignmentResponse> getAll(int page, int size) {
        if (page < 0) {
            page = 0;
        }
        PageRequest pagination = PageRequest.of(page , size);

        return this.assignmentRepository.findAll(pagination).map(this::entityResponse);
    }

    // 30 Find
    private Assignment find(Long id) {
        return this.assignmentRepository.findById(id).orElseThrow(() -> new IdNotFoundException("assignment"));
    }

    // 31 ****
    private AssignmentResponse entityResponse(Assignment entity) {
        

        AssignmentResponse response = new AssignmentResponse();
        BeanUtils.copyProperties(entity, response);

        LessonResponse lessonResponse = new LessonResponse();
        BeanUtils.copyProperties(entity.getLesson(),lessonResponse);

        UserResponse user = new UserResponse();
        BeanUtils.copyProperties(entity.getLesson().getCourses().getUser(),user);

        response.setLesson(lessonResponse);
        return response;
    }

    // 32 Request
    private Assignment requestToAssignment(AssignmentRequest request, Assignment assignment) {
        BeanUtils.copyProperties(request, assignment);
        return assignment;
    }

}
