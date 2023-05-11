package jscode.jscodestudy;

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
        return em.createQuery("select p from Post p", Post.class)
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
