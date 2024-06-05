package riwi.simulacro_SpringBoot.infrastructure.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import riwi.simulacro_SpringBoot.api.dto.requests.EnrollmentRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.CourseResponse;
import riwi.simulacro_SpringBoot.api.dto.responses.EnrollmentResponse;
import riwi.simulacro_SpringBoot.api.dto.responses.UserResponse;
import riwi.simulacro_SpringBoot.domain.entities.Courses;
import riwi.simulacro_SpringBoot.domain.entities.Enrollment;
import riwi.simulacro_SpringBoot.domain.entities.User;
import riwi.simulacro_SpringBoot.domain.repositories.CoursesRepository;
import riwi.simulacro_SpringBoot.domain.repositories.EnrollmentRepository;
import riwi.simulacro_SpringBoot.domain.repositories.UserRepository;
import riwi.simulacro_SpringBoot.infrastructure.abstrac_services.IEnrollmentService;
import riwi.simulacro_SpringBoot.util.exceptions.IdNotFoundException;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class EnrollmentService  implements IEnrollmentService {

    @Autowired
    private final EnrollmentRepository enrollmentRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final CoursesRepository coursesRepository;

    @Override
    public EnrollmentResponse create(EnrollmentRequest request) {
        User user = this.userRepository.findById(request.getUser()).orElseThrow(()->new IdNotFoundException("User"));
        Courses courses = this.coursesRepository.findById(request.getCourse()).orElseThrow(()->new IdNotFoundException("Course"));
        Enrollment enrollment =this.requestToEntity(request,new Enrollment());
        enrollment.setCourse(courses);
        enrollment.setUser(user);
        enrollment.setEnrollmentDate(LocalDate.now());

        return this.responseEntity(this.enrollmentRepository.save(enrollment));


    }

    @Override
    public EnrollmentResponse get(Long aLong) {
        return this.responseEntity(this.find(aLong));
    }

    @Override
    public EnrollmentResponse update(EnrollmentRequest request, Long aLong)
    {
        Enrollment enrollment = this.find(aLong);
        User user = this.userRepository.findById(request.getUser()).orElseThrow(()->new IdNotFoundException("User"));
        Courses courses = this.coursesRepository.findById(request.getCourse()).orElseThrow(()->new IdNotFoundException("Course"));

        Enrollment enrollmentUpdate = this.requestToEntity(request,enrollment);
        enrollmentUpdate.setUser(user);
        enrollmentUpdate.setCourse(courses);

        return this.responseEntity(this.enrollmentRepository.save(enrollmentUpdate));

    }

    @Override
    public void delete(Long aLong) {
        Enrollment enrollment = this.find(aLong);
        this.enrollmentRepository.delete(enrollment);
    }

    @Override
    public Page<EnrollmentResponse> getAll(int page, int size) {
        if (page<0)
            page=0;

        PageRequest pagination = PageRequest.of(page,size);

        return this.enrollmentRepository.findAll(pagination).map(this::responseEntity);
    }

    public Enrollment find(Long id){
        return this.enrollmentRepository.findById(id).orElseThrow(()->new IdNotFoundException("enrollment"));
    }

    public Enrollment requestToEntity(EnrollmentRequest request, Enrollment entity){
        BeanUtils.copyProperties(request,entity);
        return entity;
    }

    public EnrollmentResponse responseEntity(Enrollment entity){
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(entity.getUser(),userResponse);
        CourseResponse courseResponse = new CourseResponse();
        BeanUtils.copyProperties(entity.getCourse(),courseResponse);
        EnrollmentResponse enrollmentResponse = new EnrollmentResponse();
        BeanUtils.copyProperties(entity,enrollmentResponse);
        enrollmentResponse.setUserId(userResponse);
        enrollmentResponse.setCourseId(courseResponse);

        return enrollmentResponse;
    }
}
