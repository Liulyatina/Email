package by.it_academy.jd2.mail.dao.entity;

import jakarta.persistence.*;

@Table(name = "emails", schema = "mail_app")
@Entity
public class MailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recipient;
    private String subject;
    private String text;

    public MailEntity() {
    }

    public MailEntity(Long id, String recipient, String subject, String text) {
        this.id = id;
        this.recipient = recipient;
        this.subject = subject;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
