package uz.gullbozor.gullbozor.verifysms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class TwilioController {
    @Autowired
    PhoneVerificationService phoneVerificationService;


    @PostMapping("/sendOtp")
    public ResponseEntity<String > sendOtp(@RequestBody PhoneNumberDto phoneDto) {
        VerificationResult result= phoneVerificationService.startVerification(phoneDto.getPhoneNum());
        if (result.isValid()) {
            return new ResponseEntity<>("Otp Sent..", HttpStatus.OK);
        }
        return new ResponseEntity<>("Otp failed to sent..",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/verifyotp")
    public ResponseEntity<String > verifyOtp(@RequestBody PhoneNumberDto phoneAndOtp) {
        VerificationResult result= phoneVerificationService.checkVerification(phoneAndOtp.getPhoneNum(),phoneAndOtp.getOtp());
        if (result.isValid()) {
            return new ResponseEntity<>("Your number is Valid..", HttpStatus.OK);
        }
        return new ResponseEntity<>("Your number is not Valid",HttpStatus.BAD_REQUEST);
    }
}
