package by.it_academy.jd2.mail.service.api;

public interface IConverter<S, T> {

    public T toEntity(S source);

    public S toDTO(T target);
}
