package by.it_academy.jd2.mail.controller.http;

import by.it_academy.jd2.mail.controller.factory.AppFactory;
import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.service.api.IMailCreationService;
import by.it_academy.jd2.mail.service.api.IMailSendService;
import by.it_academy.jd2.mail.service.api.ISearchMailService;
import by.it_academy.jd2.mail.service.api.dto.MailDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    private final ObjectMapper mapper = AppFactory.getMapper();
    private final ISearchMailService searchMailService;
    private final IMailCreationService mailService;
    private final IMailSendService sendService;

    public MailController(ISearchMailService searchMailService, IMailCreationService mailService, IMailSendService sendService) {
        this.searchMailService = searchMailService;
        this.mailService = mailService;
        this.sendService = sendService;
    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    public String get(@RequestParam(value = "id", required = false) Long id,
                      @RequestParam(value = "page", defaultValue = "1") Integer page,
                      @RequestParam(value = "size", defaultValue = "10") Integer size) throws IOException {
        if (id != null) {
            MailEntity mail = searchMailService.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mail not found"));
            return mapper.writeValueAsString(mail);
        } else {
            List<MailEntity> data = searchMailService.findAll(page, size);
            return mapper.writeValueAsString(data);
        }
    }


    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> saveMail(@RequestBody MailDTO mailDTO) throws IOException{
        try {
            mailService.create(mailDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Письмо успешно сохранено и отправлено");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Не удалось сохранить и отправить письмо: " + e.getMessage());
        }
    }
}
