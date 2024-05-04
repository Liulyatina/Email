package by.it_academy.jd2.mail.controller.http;

import by.it_academy.jd2.mail.controller.factory.AppFactory;
import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.service.api.ISaveMailService;
import by.it_academy.jd2.mail.service.api.ISearchMailService;
import by.it_academy.jd2.mail.service.api.dto.MailDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/enail")
public class MailController {

    private final ObjectMapper mapper = AppFactory.getMapper();
    private final ISearchMailService searchMailService;
    private final ISaveMailService saveMailService;

    public MailController(ISearchMailService searchMailService, ISaveMailService saveMailService) {
        this.searchMailService = searchMailService;
        this.saveMailService = saveMailService;
    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    public String get(@RequestParam(value = "id", required = false) Long id,
                      @RequestParam(value = "page", required = false) Integer page,
                      @RequestParam(value = "size", required = false) Integer size) throws IOException {
        if (id != null) {
            return mapper.writeValueAsString(searchMailService.findById(id).get());
        } else {
            List<MailEntity> data = searchMailService.findAll(page, size);
            return mapper.writeValueAsString(data);
        }
    }

    @PostMapping("/api/send-mail")
    public ResponseEntity<String> savedMail(@RequestBody MailDTO mailDTO) {
        try {
            saveMailService.saveMail(mailDTO);
            return ResponseEntity.ok("Письмо успешно сохранено");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
