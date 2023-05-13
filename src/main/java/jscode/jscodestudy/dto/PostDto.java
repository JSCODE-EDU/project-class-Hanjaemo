package jscode.jscodestudy.dto;

import jscode.jscodestudy.domain.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime created;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.created = post.getCreatedTime();
    }

    public static PostDto from(Post post) {
        return new PostDto(post);
    }
}
