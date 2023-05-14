package jscode.jscodestudy.service;

import jscode.jscodestudy.domain.Post;
import jscode.jscodestudy.dto.PostDto;
import jscode.jscodestudy.repository.PostRepository;
import jscode.jscodestudy.repository.PostSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Long writePost(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    public List<Post> findAllPost(String keyword) {
        PostSearch postSearch = new PostSearch(keyword);
        return postRepository.findAll(postSearch);
    }

    public Post findPost(Long id) {
        return postRepository.findOne(id);
    }

    public Post updatePost(Long id, PostDto updatePost) {
        Post post = postRepository.findOne(id);
        return post.updatePost(updatePost);
    }

    public void deletePost(Long id) {
        postRepository.delete(postRepository.findOne(id).getId());
    }
}
