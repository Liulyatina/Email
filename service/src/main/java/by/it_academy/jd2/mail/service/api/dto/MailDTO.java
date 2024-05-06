package by.it_academy.jd2.mail.service.api.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO {
    private Long id;
    private String recipient;
    private String subject;
    private String text;
}