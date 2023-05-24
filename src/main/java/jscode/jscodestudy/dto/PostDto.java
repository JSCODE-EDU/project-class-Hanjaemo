package jscode.jscodestudy.dto;

import jscode.jscodestudy.domain.Post;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class PostDto {
    private Long id;

    @NotBlank(message = "제목은 공백 없이 1글자 이상 15글자 이하여야 합니다.")
    @Size(min = 1, max = 15, message = "제목은 공백 없이 1글자 이상 15글자 이하여야 합니다.")
    private String title;

    @NotEmpty(message = "내용은 1글자 이상 1000글자 이하여야 합니다.")
    @Size(min = 1, max = 1000, message = "내용은 1글자 이상 1000글자 이하여야 합니다.")
    private String content;

    private LocalDateTime createdTime;

    public PostDto() {
    }

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdTime = post.getCreatedTime();
    }

    public static PostDto from(Post post) {
        return new PostDto(post);
    }
}
