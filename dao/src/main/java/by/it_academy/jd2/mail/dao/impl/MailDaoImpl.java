package by.it_academy.jd2.mail.dao.impl;

import by.it_academy.jd2.mail.dao.api.IMailDao;
import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.dao.factory.FactoryDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;
import java.util.Optional;

public class MailDaoImpl implements IMailDao {
    @Override
    public List<MailEntity> findAll(Integer page, Integer size) {
        try (EntityManager em = FactoryDao.getEntityManager()) {
            em.getTransaction().begin();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<MailEntity> query = cb.createQuery(MailEntity.class);
            query.from(MailEntity.class);
            List<MailEntity> resultList = em.createQuery(query)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
            em.getTransaction().commit();
            return resultList;
        }
    }

    @Override
    public Optional<MailEntity> findById(Long id) {
        try (EntityManager em = FactoryDao.getEntityManager()) {
            em.getTransaction().begin();
            MailEntity entity = em.find(MailEntity.class, id);
            em.getTransaction().commit();
            return Optional.ofNullable(entity);
        }
    }

    @Override
    public int save(MailEntity email) {
        try (EntityManager em = FactoryDao.getEntityManager()) {
            em.getTransaction().begin();
            em.persist(email);
            em.getTransaction().commit();
            return 1;
        }
    }
}
