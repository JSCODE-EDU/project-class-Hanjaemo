package jscode.jscodestudy.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jscode.jscodestudy.domain.Post;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "게시글 정보")
public class PostDto {

    @ApiModelProperty(name = "id", value = "아이디", example = "1", required = true, dataType = "Long")
    private Long id;

    @NotBlank(message = "제목은 공백 없이 1글자 이상 15글자 이하여야 합니다.")
    @Size(min = 1, max = 15, message = "제목은 공백 없이 1글자 이상 15글자 이하여야 합니다.")
    @ApiModelProperty(name = "title", value = "제목", example = "제목", required = true, dataType = "String")
    private String title;

    @NotEmpty(message = "내용은 1글자 이상 1000글자 이하여야 합니다.")
    @Size(min = 1, max = 1000, message = "내용은 1글자 이상 1000글자 이하여야 합니다.")
    @ApiModelProperty(name = "content", value = "내용", example = "내용", required = true, dataType = "String")
    private String content;

    @ApiModelProperty(name = "createdTime", value = "생성일자", required = true, dataType = "LocalDateTime")
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
