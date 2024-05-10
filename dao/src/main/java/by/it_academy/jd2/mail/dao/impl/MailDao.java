package by.it_academy.jd2.mail.dao.impl;

import by.it_academy.jd2.mail.dao.api.IMailDao;
import by.it_academy.jd2.mail.dao.entity.MailEntity;
import by.it_academy.jd2.mail.dao.entity.MailStatus;
import by.it_academy.jd2.mail.dao.factory.DaoFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;

public class MailDao implements IMailDao {

    @Override
    public List<MailEntity> findAll(Integer page, Integer size) {
        EntityManager em = DaoFactory.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<MailEntity> query = cb.createQuery(MailEntity.class);
            Root<MailEntity> root = query.from(MailEntity.class);
            query.select(root);
            return em.createQuery(query)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<MailEntity> findById(Long id) {
        EntityManager em = DaoFactory.getEntityManager();
        try {
            return Optional.ofNullable(em.find(MailEntity.class, id));
        } finally {
            em.close();
        }
    }

    @Override
    public MailEntity update(MailEntity entity) {
        EntityManager em = DaoFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            MailEntity updateEntity = em.merge(entity);
            em.getTransaction().commit();
            return updateEntity;
        } finally {
            em.close();
        }
    }

    @Override
    public List<MailEntity> getByStatus(MailStatus mailStatus) {
        EntityManager em = DaoFactory.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<MailEntity> query = cb.createQuery(MailEntity.class);
            Root<MailEntity> root = query.from(MailEntity.class);
            query.select(root).where(cb.equal(root.get("status"), mailStatus));
            return em.createQuery(query).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void save(MailEntity mail) {
        EntityManager em = DaoFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(mail);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
