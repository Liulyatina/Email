package by.it_academy.jd2.mail.service.api.dto;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO {
    private String recipient;
    private String subject;
    private String text;
}