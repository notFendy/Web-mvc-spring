package uz.pdp.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String gender;
    private Date createdDate;
}
