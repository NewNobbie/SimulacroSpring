package riwi.simulacro_SpringBoot.infrastructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import riwi.simulacro_SpringBoot.api.dto.requests.CourseRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.CourseResponse;
import riwi.simulacro_SpringBoot.api.dto.responses.LessonResponse;
import riwi.simulacro_SpringBoot.api.dto.responses.UserResponse;
import riwi.simulacro_SpringBoot.domain.entities.Courses;
import riwi.simulacro_SpringBoot.domain.entities.Lesson;
import riwi.simulacro_SpringBoot.domain.entities.User;
import riwi.simulacro_SpringBoot.domain.repositories.CoursesRepository;
import riwi.simulacro_SpringBoot.domain.repositories.UserRepository;
import riwi.simulacro_SpringBoot.infrastructure.abstrac_services.ICourseService;
import riwi.simulacro_SpringBoot.util.enums.EnumRole;
import riwi.simulacro_SpringBoot.util.exceptions.IdNotFoundException;
import riwi.simulacro_SpringBoot.util.exceptions.RoleDenegateException;

import java.util.List;
import java.util.stream.Collectors;

// 8
@Service
@AllArgsConstructor
public class CourseService implements ICourseService {

    @Autowired
    final CoursesRepository coursesRepository;

    @Autowired
    final UserRepository userRepository;

    // 12
    // 22
    @Override
    public CourseResponse create(CourseRequest request) {
        Courses courses = this.requesToCourses(request, new Courses());

        User user = this.userRepository.findById(request.getUser()).orElseThrow(()-> new IdNotFoundException("User"));

        courses.setUser(user);

        CourseResponse result =new CourseResponse() ;

        if (!courses.getUser().getRole().equals(EnumRole.INSTRUCTOR) ) {
             throw  new RoleDenegateException();
        }else{
            result= this.entityResponse(this.coursesRepository.save(courses));
        }

        return result;
    }

    // 14
    @Override
    public CourseResponse get(Long id) {
        return this.entityResponse(this.find(id));
    }

    // 16
    @Override
    public CourseResponse update(CourseRequest request, Long aLong) {
        Courses courses = this.find(aLong);
        Courses courseUpdate = this.requesToCourses(request, courses);
        User user = this.userRepository.findById(request.getUser()).orElseThrow(()-> new IdNotFoundException("User"));
        courseUpdate.setUser(user);
        CourseResponse result =new CourseResponse() ;
        if (!courseUpdate.getUser().getRole().equals(EnumRole.INSTRUCTOR) ) {
            throw  new RoleDenegateException();
        }else{
            result= this.entityResponse(this.coursesRepository.save(courseUpdate));
        }

        return result;
    }

    // 13
    @Override
    public void delete(Long aLong) {
        Courses courses = this.find(aLong);
        System.out.println(courses);
        this.coursesRepository.delete(courses);
    }

    // 15
    @Override
    public Page<CourseResponse> getAll(int page, int size) {
        if (page < 0) {
            page = 0;
        }
        PageRequest pagination = PageRequest.of(page, size);

        return this.coursesRepository.findAll(pagination).map(this::entityResponse);
    }

    // 9
    private Courses find(Long id) {
        return this.coursesRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Curso"));
    }

    // 10
    private CourseResponse entityResponse(Courses entity) {
        CourseResponse response = new CourseResponse();
        UserResponse user = UserService.entityResponse(entity.getUser());
        BeanUtils.copyProperties(entity, response);
        if (entity.getLessons()!= null) {
            List<LessonResponse> lesson = entity.getLessons().stream().map(this::entityToResponseLesson).collect(Collectors.toList());
            response.setLessons(lesson);
        }
        response.setUser(user);
        return response;
    }

    private LessonResponse entityToResponseLesson(Lesson entity){
        LessonResponse response = new LessonResponse();
        BeanUtils.copyProperties(entity,response);
        return response;
    }

    // 11
    private Courses requesToCourses(CourseRequest request, Courses courses) {
        BeanUtils.copyProperties(request, courses);
        return courses;
    }

}
