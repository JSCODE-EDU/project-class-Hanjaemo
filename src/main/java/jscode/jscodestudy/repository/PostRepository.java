package jscode.jscodestudy.repository;

import jscode.jscodestudy.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }

    public List<Post> findAll() {
        String jpql = "select p from Post p";

        jpql += " order by p.createdTime desc";

        TypedQuery<Post> query = em.createQuery(jpql, Post.class);

        return query.setMaxResults(100).getResultList();
    }

    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    public void delete(Long id) {
        Post findPost = em.find(Post.class, id);
        em.remove(findPost);
    }

    public List<Post> findAllByTitle(PostSearch postSearch) {
        String jpql = "select p from Post p";

        if (postSearch.getTitle() != null) {
            jpql += " where p.title like :title";
        }

        jpql += " order by p.createdTime desc";

        TypedQuery<Post> query = em.createQuery(jpql, Post.class);

        if (postSearch.getTitle() != null) {
            query.setParameter("title", "%" + postSearch.getTitle() + "%");
        }

        return query.setMaxResults(100).getResultList();
    }
}
