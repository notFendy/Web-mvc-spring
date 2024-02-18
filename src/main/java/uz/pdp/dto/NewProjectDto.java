package uz.pdp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class NewProjectDto {
    private Long project_id;
    @NotBlank
    @Length(min = 6, max = 50)
    private String project_name;
    @Email
    private String project_developer;
    private String file_name;
    private String start_time;
    private String end_time;
}
