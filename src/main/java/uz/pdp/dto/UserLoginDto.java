package uz.pdp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserLoginDto {
    @Email
    private String email;
    @NotBlank
    @Length(min = 6, max = 50)
    private String password;
}
