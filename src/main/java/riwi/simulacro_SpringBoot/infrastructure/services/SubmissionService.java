package riwi.simulacro_SpringBoot.infrastructure.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import riwi.simulacro_SpringBoot.api.dto.requests.SubmissionRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.AssignmentResponse;
import riwi.simulacro_SpringBoot.api.dto.responses.SubmissionResponse;
import riwi.simulacro_SpringBoot.api.dto.responses.UserResponse;
import riwi.simulacro_SpringBoot.domain.entities.Assignment;
import riwi.simulacro_SpringBoot.domain.entities.Submission;
import riwi.simulacro_SpringBoot.domain.entities.User;
import riwi.simulacro_SpringBoot.domain.repositories.AssignmentRepository;
import riwi.simulacro_SpringBoot.domain.repositories.SubmissionRepository;
import riwi.simulacro_SpringBoot.domain.repositories.UserRepository;
import riwi.simulacro_SpringBoot.infrastructure.abstrac_services.ISubmissionService;
import riwi.simulacro_SpringBoot.util.exceptions.IdNotFoundException;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class SubmissionService implements ISubmissionService {
    @Autowired
    private final SubmissionRepository submissionRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AssignmentRepository assignmentRepository;

    @Override
    public SubmissionResponse create(SubmissionRequest request) {
        User user=this.userRepository.findById(request.getUser_id()).orElseThrow(()->new IdNotFoundException("User"));
        Assignment assignment= this.assignmentRepository.findById(request.getAssignment_id()).orElseThrow(()->new IdNotFoundException("assignment"));

        Submission submission   =this.requestToEntity(request,new Submission());
        submission.setUser_id(user);
        submission.setAssignment_id(assignment);
        submission.setSubmissionDate(LocalDate.now());

        return this.entityResponse(this.submissionRepository.save(submission));
    }

    @Override
    public SubmissionResponse get(Long aLong) {
        return this.entityResponse(this.find(aLong));
    }

    @Override
    public SubmissionResponse update(SubmissionRequest request, Long aLong) {
        Submission submission = this.find(aLong);
        Submission submissionUpdate = this.requestToEntity(request,submission);
        User user=this.userRepository.findById(request.getUser_id()).orElseThrow(()->new IdNotFoundException("User"));
        Assignment assignment= this.assignmentRepository.findById(request.getAssignment_id()).orElseThrow(()->new IdNotFoundException("assignment"));

        submissionUpdate.setAssignment_id(assignment);
        submissionUpdate.setUser_id(user);

        return this.entityResponse(this.submissionRepository.save(submissionUpdate));
    }

    @Override
    public void delete(Long aLong) {
        Submission submission=this.find(aLong);
        this.submissionRepository.delete(submission);

    }

    @Override
    public Page<SubmissionResponse> getAll(int page, int size) {
        if (page<0)
            page =0;

        PageRequest pagination = PageRequest.of(page, size);
        return this.submissionRepository.findAll(pagination).map(this::entityResponse);

    }


    private Submission find( Long id){
        return this.submissionRepository.findById(id).orElseThrow(()->new IdNotFoundException("sudmission"));
    }

    private SubmissionResponse entityResponse(Submission entity){
        SubmissionResponse response = new SubmissionResponse();
        BeanUtils.copyProperties(entity,response);

        UserResponse userResponse=new UserResponse();
        BeanUtils.copyProperties(entity.getUser_id(),userResponse);

        AssignmentResponse assignmentResponse= new AssignmentResponse();
        BeanUtils.copyProperties(entity.getAssignment_id(),assignmentResponse);

        response.setAssignment_id(assignmentResponse);
        response.setUser_id(userResponse);
        return  response;
    }
    private Submission requestToEntity(SubmissionRequest request,Submission submission)
    {
        BeanUtils.copyProperties(request,submission);
        return submission;
    }
}
