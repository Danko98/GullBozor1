package uz.gullbozor.gullbozor.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SignInDto {

    @NotNull
    private String password; //userning passwordi

    @NotNull
    private String repeatPassword; //userning takroriy passwordi

}
