package jscode.jscodestudy.repository;

import jscode.jscodestudy.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void join(User user) {
        em.persist(user);
    }

    public User findUser(String email, String password) {
        String jpql = "select u from User u where u.email = :email and u.password = :password";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        return query.setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
    }

    public Long checkEmail(String email) {
        String jpql = "select count(u.email) from User u where u.email = :email";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    public Long checkPassword(String password) {
        String jpql = "select count(u.password) from User u where u.password = :password";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("password", password);
        return query.getSingleResult();
    }
}
