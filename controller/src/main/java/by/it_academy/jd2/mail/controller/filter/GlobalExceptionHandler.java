package by.it_academy.jd2.mail.controller.filter;

import by.it_academy.jd2.mail.service.exceptions.FailMailSendException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GlobalExceptionHandler {

    private final static Logger logger = LogManager.getLogger();

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegal(IllegalArgumentException e) {
        logger.log(Level.WARN, "Пользователь сделал что-то не так", e);

        Map<String, Object> errorObj = new HashMap<>();
        errorObj.put("error", e.getMessage());

        return new ResponseEntity<>(errorObj, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleIllegal(Exception e) {
        logger.log(Level.ERROR, "Ошибка на стороне сервера", e);

        Map<String, Object> errorObj = new HashMap<>();
        errorObj.put("error", e.getMessage());

        return new ResponseEntity<>(errorObj, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FailMailSendException.class)
    public ResponseEntity<Map<String, Object>> handleMailSendFailure(FailMailSendException e) {
        logger.log(Level.ERROR, "Ошибка при отправке почты", e);

        Map<String, Object> errorObj = new HashMap<>();
        errorObj.put("error", e.getMessage());

        return new ResponseEntity<>(errorObj, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}