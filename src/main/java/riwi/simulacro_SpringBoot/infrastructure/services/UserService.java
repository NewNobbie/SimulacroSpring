package riwi.simulacro_SpringBoot.infrastructure.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import riwi.simulacro_SpringBoot.api.dto.requests.UserRequest;
import riwi.simulacro_SpringBoot.api.dto.responses.UserResponse;
import riwi.simulacro_SpringBoot.domain.entities.User;
import riwi.simulacro_SpringBoot.domain.repositories.UserRepository;
import riwi.simulacro_SpringBoot.infrastructure.abstrac_services.IUserService;
import riwi.simulacro_SpringBoot.util.exceptions.IdNotFoundException;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private  final UserRepository userRepository;
    @Override
    public UserResponse create(UserRequest request) {
        User user = this.requestToUser(request,new User());
        return this.entityResponse(this.userRepository.save(user));
    }

    @Override
    public UserResponse get(Long aLong) {
        return this.entityResponse(this.find(aLong));
    }


    @Override
    public UserResponse update(UserRequest request, Long aLong) {
        User user=this.find(aLong);
        User userUpdate =this.requestToUser(request,user);
        return this.entityResponse(this.userRepository.save(userUpdate));
    }

    @Override
    public void delete(Long aLong) {
    User user = this.find(aLong);
    this.userRepository.delete(user);
    }

    @Override
    public Page<UserResponse> getAll(int page, int size) {
       if (page<0)
           page=0;
        PageRequest pagination = PageRequest.of(page, size);
        return this.userRepository.findAll(pagination).map(user->this.entityResponse(user));
    }


    private User find(Long id){
        return this.userRepository.findById(id).orElseThrow(()->new IdNotFoundException("User"));
    }
    public static UserResponse entityResponse(User entity){
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(entity,response);
        return response;
    }
    private User requestToUser (UserRequest request,User user){
        BeanUtils.copyProperties(request,user);
        return user;
    }




}
