package by.it_academy.jd2.mail.dao.impl;

import by.it_academy.jd2.mail.dao.api.IUserDao;
import by.it_academy.jd2.mail.dao.entity.UserEntity;
import by.it_academy.jd2.mail.dao.factory.DaoFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class UserDao implements IUserDao {
    @Override
    public void create(UserEntity user) {
        EntityManager em = DaoFactory.getEntityManager();
        List<UserEntity> existingUsers = getByLogin(user.getEmail());

        if (!existingUsers.isEmpty()) {
            throw new IllegalArgumentException("Пользователь с таким логином уже существует");
        }

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<UserEntity> getByLogin(String email) {
        EntityManager em = DaoFactory.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root).where(cb.equal(root.get("email"), email));
        return em.createQuery(query).getResultList();
    }
}
