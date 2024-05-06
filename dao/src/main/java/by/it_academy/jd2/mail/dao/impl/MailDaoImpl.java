package by.it_academy.jd2.mail.dao.impl;

import by.it_academy.jd2.mail.dao.api.IMailDao;
import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.dao.factory.DaoFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class MailDaoImpl implements IMailDao {

    @Override
    public List<MailEntity> findAll(Integer page, Integer size) {
        EntityManager em = DaoFactory.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MailEntity> query = cb.createQuery(MailEntity.class);
        Root<MailEntity> root = query.from(MailEntity.class);
        query.select(root);
        return em.createQuery(query)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public Optional<MailEntity> findById(Long id) {
        EntityManager em = DaoFactory.getEntityManager();
        return Optional.ofNullable(em.find(MailEntity.class, id));
    }

    @Override
    public void save(MailEntity mail) {
        EntityManager em = DaoFactory.getEntityManager();
        em.getTransaction().begin();
        em.persist(mail);
        em.getTransaction().commit();
    }
}