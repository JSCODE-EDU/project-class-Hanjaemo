package jscode.jscodestudy.domain;

import jscode.jscodestudy.dto.PostDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;

    @Column(name = "created_time", updatable = false)
    private LocalDateTime createdTime;

    @Builder
    public Post(String title, String content, LocalDateTime createdTime) {
        this.title = title;
        this.content = content;
        this.createdTime = LocalDateTime.now();
    }

    public Post update(PostDto updatePost) {
        this.title = updatePost.getTitle();
        this.content = updatePost.getContent();
        return this;
    }
}
