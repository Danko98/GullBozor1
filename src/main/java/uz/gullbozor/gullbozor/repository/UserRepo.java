package uz.gullbozor.gullbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullbozor.gullbozor.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    boolean existsByUserNameAndPassword(String userName, String password);

//    boolean existsByEmail(String email);
}
