package jscode.jscodestudy.service;

import jscode.jscodestudy.domain.Post;
import jscode.jscodestudy.dto.PostDto;
import jscode.jscodestudy.exception.ErrorCode;
import jscode.jscodestudy.exception.domain.PostException;
import jscode.jscodestudy.repository.PostRepository;
import jscode.jscodestudy.repository.PostSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post writePost(PostDto postDto) {
        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();
        postRepository.save(post);

        return post;
    }

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    public Post findPost(Long id) {
        return Optional.ofNullable(postRepository.findOne(id))
                .orElseThrow(() -> new PostException(ErrorCode.NOT_FOUND));
    }

    public Post updatePost(Long id, PostDto updatePost) {
        Post post = postRepository.findOne(id);
        return post.updatePost(updatePost);
    }

    public void deletePost(Long id) {
        Post post = Optional.ofNullable(postRepository.findOne(id))
                .orElseThrow(() -> new PostException(ErrorCode.NOT_FOUND));
        postRepository.delete(post.getId());
    }

    public List<Post> findAllPostByTitle(String keyword) {
        if (keyword.length() < 1) {
            throw new PostException(ErrorCode.BAD_REQUEST);
        }
        PostSearch postSearch = new PostSearch(keyword);
        return postRepository.findAllByTitle(postSearch);
    }
}
