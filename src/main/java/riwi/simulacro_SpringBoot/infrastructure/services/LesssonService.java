package riwi.simulacro_SpringBoot.infrastructure.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import riwi.simulacro_SpringBoot.api.dto.requests.LessonRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.CourseResponse;
import riwi.simulacro_SpringBoot.api.dto.responses.LessonResponse;
import riwi.simulacro_SpringBoot.api.dto.responses.UserResponse;
import riwi.simulacro_SpringBoot.domain.entities.Courses;
import riwi.simulacro_SpringBoot.domain.entities.Lesson;
import riwi.simulacro_SpringBoot.domain.repositories.CoursesRepository;
import riwi.simulacro_SpringBoot.domain.repositories.LessonRepository;
import riwi.simulacro_SpringBoot.infrastructure.abstrac_services.ILessonService;
import riwi.simulacro_SpringBoot.util.exceptions.IdNotFoundException;


@Service
@AllArgsConstructor
public class LesssonService implements ILessonService {
    
    @Autowired
    private  final LessonRepository lessonRepository;

    @Autowired
    private final CoursesRepository coursesRepository;
    @Override
    public LessonResponse create(LessonRequest request) {
        Courses courses = this.coursesRepository.findById(request.getCourse()).orElseThrow(()->new IdNotFoundException("course"));

        Lesson lesson = this.requestToEntity(request,new Lesson());

        lesson.setCourses(courses);
        return this.entityRespose(this.lessonRepository.save(lesson));
    }

    @Override
    public LessonResponse get(Long aLong) {
        return this.entityRespose(this.find(aLong));
    }

    @Override
    public LessonResponse update(LessonRequest request, Long aLong) {
        Lesson lesson = this.find(aLong);
        Courses courses=this.coursesRepository.findById(request.getCourse()).orElseThrow(()->new IdNotFoundException("course"));
        Lesson lessonUpdate = this.requestToEntity(request,lesson);
        lessonUpdate.setCourses(courses);
        return this.entityRespose(this.lessonRepository.save(lessonUpdate));

    }

    @Override
    public void delete(Long aLong) {
        Lesson lesson = this.find(aLong);
        this.lessonRepository.delete(lesson);

    }

    @Override
    public Page<LessonResponse> getAll(int page, int size) {
        if (page<0)
            page=0;
        PageRequest pagination = PageRequest.of(page, size);
        return this.lessonRepository.findAll(pagination).map(this::entityRespose);
    }

    private Lesson find(Long id){
        return  this.lessonRepository.findById(id).orElseThrow(()->new IdNotFoundException("Lesson"));
    }

    private LessonResponse entityRespose(Lesson entity){
        LessonResponse response = new LessonResponse();
        BeanUtils.copyProperties(entity,response);

        CourseResponse courseResponse = new CourseResponse();
        BeanUtils.copyProperties(entity.getCourses(),courseResponse);

        UserResponse user = new UserResponse();
        BeanUtils.copyProperties(entity.getCourses().getUser(),user);

        courseResponse.setUser(user);
        response.setCourse(courseResponse);
        return response;
    }

    private Lesson requestToEntity(LessonRequest request,Lesson lesson){
        BeanUtils.copyProperties(request,lesson);
        return lesson;
    }
}
