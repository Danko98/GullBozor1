package uz.gullbozor.gullbozor.service;

import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
import uz.gullbozor.gullbozor.apiResponse.ApiResponse;

@Service
public class AuthService {


//    public ApiResponse verify(Integer smsCode) {
//
//    }
//
//    public ApiResponse smsCodeSender(PhoneNumber phoneNumber) {
//
//    }

   /* @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    public ApiResponse registerUser(RegisterDto registerDto) {



        UserEntity user = new UserEntity();
        user.setFirstName(registerDto.getFirstName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));


        user.setEmailCode(UUID.randomUUID().toString());//?

        userRepo.save(user);

        //Emailga habar yuborish methodini chaqiramiz.

//        sendEmail(user.getEmail(), user.getEmailCode());
        return new ApiResponse("Muvaffaqiyatli ro'yxatdan o'tdingiz. Email habarlarga kirib tasdiqlang!!!");



    }

    *//*public Boolean sendEmail(String sendingEmail, String emailCode) {

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("goldcoder998@gmail.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Akkountni tasdiqlash");
            mailMessage.setText("<a href='http:/localhost:8080/auth/verifyEmail?emailCode=" + emailCode + "+&email=" + sendingEmail + "'> Tasdiqlang</a>");
            return true;
        }catch (Exception e) {
            return false;
        }
    }*//*

*/
}
