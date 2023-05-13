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

    /**
     * 게시글 작성
     */
    public Long writePost(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    /**
     * 게시글 전체 조회
     */
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    /**
     * 특정 게시글 조회
     */
    public Post findPost(Long id) {
        return postRepository.findOne(id);
    }

    /**
     * 특정 게시글 수정
     */
    public Post updatePost(Long id, PostDto updatePost) {
        Post post = postRepository.findOne(id);
        return post.update(updatePost);
    }

    /**
     * 특정 게시글 삭제
     */
    public void deletePost(Long id) {
        postRepository.delete(postRepository.findOne(id).getId());
    }
}
