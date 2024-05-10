package by.it_academy.jd2.mail.controller.http;

import by.it_academy.jd2.mail.controller.factory.AppFactory;
import by.it_academy.jd2.mail.core.dto.UserDto;
import by.it_academy.jd2.mail.service.api.IUserRegistrationService;
import by.it_academy.jd2.mail.service.exceptions.FailMailSendException;
import by.it_academy.jd2.mail.service.impl.UserRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    private final ObjectMapper mapper = AppFactory.getMapper();
    private final IUserRegistrationService userService;

    public RegistrationController(IUserRegistrationService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public String registration(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("birthday") String birthday,
                               @RequestParam("fullName") String fullName) throws IOException, FailMailSendException {

        LocalDate parsedBrithday = LocalDate.parse(birthday);

        UserDto userDto = UserDto.builder()
                .email(email)
                .password(password)
                .birthday(parsedBrithday.atStartOfDay())
                .fullName(fullName)
                .build();

        userService.create(userDto);

        return "Пользователь успешно зарегистрирован";

    }
}
