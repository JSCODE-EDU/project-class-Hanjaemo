package jscode.jscodestudy.controller;

import jscode.jscodestudy.domain.Post;
import jscode.jscodestudy.dto.PostDto;
import jscode.jscodestudy.dto.Result;
import jscode.jscodestudy.repository.PostSearch;
import jscode.jscodestudy.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/posts")
    public PostDto writePost(@RequestBody PostDto postDto) {
        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();
        postService.writePost(post);
        return PostDto.from(post);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/posts")
    public Result<PostDto> findAllPostBy() {
        List<Post> findPost = postService.findAllPost();
        List<PostDto> findPostDto = findPost.stream()
                .map(m -> PostDto.from(m))
                .collect(Collectors.toList());
        return Result.from(findPostDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/posts/{id}")
    public PostDto findById(@PathVariable("id") Long id) {
        Post findPost = postService.findPost(id);
        return PostDto.from(findPost);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/posts/{id}")
    public PostDto updatePost(@PathVariable("id") Long id, @RequestBody PostDto postDto) {
        Post updatePost = postService.updatePost(id, postDto);
        return PostDto.from(updatePost);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return "ok";
    }
}
