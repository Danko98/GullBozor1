package uz.gullbozor.gullbozor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.dto.EditUserDto;
import uz.gullbozor.gullbozor.entity.UserEntity;
import uz.gullbozor.gullbozor.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public ApiResponse createUser(UserEntity userDto) {

        if (userRepo.existsByUserNameAndPassword(userDto.getUsername(),userDto.getPassword())) {
            return new ApiResponse("This Login and Password already exists",false);
        }

        UserEntity user = new UserEntity();
        user.setUserName(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        userRepo.save(user);

        return new ApiResponse("Successfully created",true);

    }

    public ApiResponse editUser(EditUserDto userDto, Long id) {

        if (!userRepo.existsById(id)) {
            return new ApiResponse("Not found user account",false);
        }

        if (userRepo.existsByUserNameAndPassword(userDto.getUserName(),userDto.getNewPassword())) {
            return new ApiResponse("This Login and Password already exists",false);
        }

        if (!userRepo.existsByUserNameAndPassword(userDto.getUserName(),userDto.getOldPassword())) {
            return new ApiResponse("login or password wrong",false);
        }


        Optional<UserEntity> optionalUser = userRepo.findById(id);

        UserEntity user = optionalUser.get();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getNewPassword());
        userRepo.save(user);

        return new ApiResponse("Successfully edited",true);

    }

    public ApiResponse getUserById(Long id) {
        if (!userRepo.existsById(id)) {
            return new ApiResponse("Not found User account",false);
        }
        Optional<UserEntity> optionalUserEntity = userRepo.findById(id);
        return new ApiResponse(optionalUserEntity.get());
    }

    public List<UserEntity> getUserList() {
        return userRepo.findAll();
    }

    public ApiResponse deleteUserById(Long id) {
        if (!userRepo.existsById(id)) {
            return new ApiResponse("Not found user account ",false);
        }
        userRepo.deleteById(id);
        return new ApiResponse("Successfully deleted",true);
    }



}
