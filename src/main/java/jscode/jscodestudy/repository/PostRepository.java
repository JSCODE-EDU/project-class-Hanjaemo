package jscode.jscodestudy.repository;

import jscode.jscodestudy.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }

    public List<Post> findAll() {
//        String jpql = "select p from Post p";
//        TypedQuery<Post> query = em.createQuery(jpql, Post.class);
//        if (StringUtils.hasText(postSearch.getTitle())) {
//            jpql += " where p.tile like :title";
//        }
//        jpql += " order by p.createdTime desc limit 100";
//
//        if (StringUtils.hasText(postSearch.getTitle())) {
//            query.setParameter("title", postSearch.getTitle());
//        }
//
//        return query.getResultList();

        return em.createQuery("select p from Post p order by p.createdTime desc", Post.class)
                .setMaxResults(100)
                .getResultList();
    }

    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    public void delete(Long id) {
        Post findPost = em.find(Post.class, id);
        em.remove(findPost);
    }
}
