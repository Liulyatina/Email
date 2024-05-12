package by.it_academy.jd2.mail.controller.http;

import by.it_academy.jd2.mail.controller.factory.AppFactory;
import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.service.api.IMailCreationService;
import by.it_academy.jd2.mail.service.api.IMailSendService;
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
    private final IMailCreationService mailService;

    public MailController(IMailCreationService mailService) {
        this.mailService = mailService;
    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    public String get(@RequestParam(value = "id", required = false) Long id,
                      @RequestParam(value = "page", defaultValue = "1") Integer page,
                      @RequestParam(value = "size", defaultValue = "10") Integer size) throws IOException {
        if (id != null) {
            MailEntity mail = mailService.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mail not found"));
            return mapper.writeValueAsString(mail);
        } else {
            List<MailEntity> data = mailService.findAll(page, size);
            return mapper.writeValueAsString(data);
        }
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> saveMail(@RequestBody MailDTO mailDTO) throws IOException{
        mailService.create(mailDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping(value = "/bulk", produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> saveMails(@RequestBody List<MailDTO> mailDTOs) throws IOException{
        for (MailDTO mailDTO : mailDTOs) {
            mailService.create(mailDTO);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }
}


