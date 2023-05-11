package jscode.jscodestudy;

import lombok.AccessLevel;
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
    private LocalDateTime createdTime = LocalDateTime.now();

    public Post update(Post updatePost) {
        this.title = updatePost.getTitle();
        this.content = updatePost.getContent();
        return updatePost;
    }
}
