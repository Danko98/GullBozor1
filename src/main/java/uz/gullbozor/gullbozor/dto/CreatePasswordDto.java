package uz.gullbozor.gullbozor.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreatePasswordDto {

    @NotNull
    private String password; //userning passwordi

    @NotNull
    private String repeatPassword; //userning takroriy passwordi

}
