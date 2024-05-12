package by.it_academy.jd2.mail.controller.http;

import by.it_academy.jd2.mail.controller.factory.AppFactory;
import by.it_academy.jd2.mail.core.dto.UserDto;
import by.it_academy.jd2.mail.service.api.IUserRegistrationService;
import by.it_academy.jd2.mail.service.exceptions.FailMailSendException;
import by.it_academy.jd2.mail.service.impl.UserRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    private final IUserRegistrationService userService;

    public RegistrationController(IUserRegistrationService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDto userDto) throws FailMailSendException {

        userService.create(userDto);
    }
}
