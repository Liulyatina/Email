package by.it_academy.jd2.mail.controller.http;

import by.it_academy.jd2.mail.core.dto.UserDto;
import by.it_academy.jd2.mail.service.api.IUserRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    private final IUserRegistrationService userService;

    public RegistrationController(IUserRegistrationService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDto userDto){

        userService.create(userDto);
    }
}