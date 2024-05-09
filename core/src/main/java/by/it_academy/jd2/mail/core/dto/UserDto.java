package by.it_academy.jd2.mail.core.dto;

import lombok.*;

import java.time.LocalDateTime;
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private LocalDateTime birthday;
    private String fullName;
}
