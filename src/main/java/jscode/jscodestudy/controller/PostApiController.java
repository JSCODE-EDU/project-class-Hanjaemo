package jscode.jscodestudy.controller;

import jscode.jscodestudy.domain.Post;
import jscode.jscodestudy.dto.PostDto;
import jscode.jscodestudy.dto.Result;
import jscode.jscodestudy.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/posts")
    public PostDto writePost(@Valid @RequestBody PostDto postDto) {
        Post post = postService.writePost(postDto);
        return PostDto.from(post);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/posts")
    public Result<PostDto> findAllPost() {
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
    public PostDto updatePost(@PathVariable("id") Long id, @Valid @RequestBody PostDto postDto) {
        Post updatePost = postService.updatePost(id, postDto);
        return PostDto.from(updatePost);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return "ok";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/posts/search")
    public Result<PostDto> findAllPostByTitle(@Valid @RequestParam(required = false) String keyword) {
        List<Post> findPost = postService.findAllPostByTitle(keyword);
        List<PostDto> findPostDto = findPost.stream()
                .map(m -> PostDto.from(m))
                .collect(Collectors.toList());
        return Result.from(findPostDto);
    }
}
