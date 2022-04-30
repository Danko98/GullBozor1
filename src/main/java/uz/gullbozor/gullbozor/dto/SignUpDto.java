package uz.gullbozor.gullbozor.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
public class SignUpDto {

    @NotNull
    @Size(min = 3,max = 50)
    private String userName;// ismi

    @NotNull
    @Size(min = 9,max = 16)
    private String phoneNumber; // telefon raqami (+998901234567)



}
