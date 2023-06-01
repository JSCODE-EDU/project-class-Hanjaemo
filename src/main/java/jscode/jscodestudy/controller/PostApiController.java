package jscode.jscodestudy.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jscode.jscodestudy.domain.Post;
import jscode.jscodestudy.dto.PostDto;
import jscode.jscodestudy.dto.Result;
import jscode.jscodestudy.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostApiController {

    private final PostService postService;

    @ResponseStatus(CREATED)
    @PostMapping()
    @ApiOperation(value = "게시글 작성", notes = "새 게시글을 작성하는 API")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "서버 내부 오류입니다. 관리자에게 문의해주시기 바랍니다.")
    })
    public PostDto writePost(@Valid @RequestBody PostDto postDto) {
        Post post = postService.writePost(postDto);
        return PostDto.from(post);
    }

    @ResponseStatus(OK)
    @GetMapping()
    @ApiOperation(value = "게시글 전체 조회", notes = "전제 게시글을 모두 조회하는 API")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "존재하지 않는 게시글입니다."),
            @ApiResponse(code = 500, message = "서버 내부 오류입니다. 관리자에게 문의해주시기 바랍니다.")
    })
    public Result<PostDto> findAllPost() {
        List<Post> findPost = postService.findAllPost();
        List<PostDto> findPostDto = findPost.stream()
                .map(m -> PostDto.from(m))
                .collect(Collectors.toList());
        return Result.from(findPostDto);
    }

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    @ApiOperation(value = "특정 게시글 조회", notes = "특정 id의 게시글만 조회하는 API")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "존재하지 않는 게시글입니다."),
            @ApiResponse(code = 500, message = "서버 내부 오류입니다. 관리자에게 문의해주시기 바랍니다.")
    })
    public PostDto findById(@PathVariable("id") Long id) {
        Post findPost = postService.findPost(id);
        return PostDto.from(findPost);
    }

    @ResponseStatus(OK)
    @PatchMapping("/{id}")
    @ApiOperation(value = "특정 게시글 수정", notes = "특정 id의 게시글을 수정하는 API")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "존재하지 않는 게시글입니다."),
            @ApiResponse(code = 500, message = "서버 내부 오류입니다. 관리자에게 문의해주시기 바랍니다.")
    })
    public PostDto updatePost(@PathVariable("id") Long id, @Valid @RequestBody PostDto postDto) {
        Post updatePost = postService.updatePost(id, postDto);
        return PostDto.from(updatePost);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    @ApiOperation(value = "특정 게시글 삭제", notes = "특정 id의 게시글을 삭제하는 API")
    @ApiResponses({
            @ApiResponse(code = 204, message = "성공"),
            @ApiResponse(code = 404, message = "존재하지 않는 게시글입니다."),
            @ApiResponse(code = 500, message = "서버 내부 오류입니다. 관리자에게 문의해주시기 바랍니다.")
    })
    public String deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return "ok";
    }

    @ResponseStatus(OK)
    @GetMapping("/search")
    @ApiOperation(value = "게시글 검색", notes = "검색 조건에 맞는 게시글을 조회하는 API")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "검색 키워드는 공백을 제외한 1글자 이상이어야 합니다."),
            @ApiResponse(code = 404, message = "존재하지 않는 게시글입니다."),
            @ApiResponse(code = 500, message = "서버 내부 오류입니다. 관리자에게 문의해주시기 바랍니다.")
    })
    public Result<PostDto> findAllPostByTitle(@Valid @RequestParam(required = false) String keyword) {
        List<Post> findPost = postService.findAllPostByTitle(keyword);
        List<PostDto> findPostDto = findPost.stream()
                .map(m -> PostDto.from(m))
                .collect(Collectors.toList());
        return Result.from(findPostDto);
    }
}
