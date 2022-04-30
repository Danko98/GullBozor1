package uz.gullbozor.gullbozor.verifysms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneNumberDto {
    private String phoneNum;
    private String otp;

    public PhoneNumberDto(String phoneNum, String otp) {
        this.phoneNum = phoneNum;
        this.otp = otp;
    }

    public PhoneNumberDto(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
