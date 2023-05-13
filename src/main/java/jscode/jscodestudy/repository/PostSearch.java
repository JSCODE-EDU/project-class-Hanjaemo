package jscode.jscodestudy.repository;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostSearch {
    private String title;

    public PostSearch() {
    }

    public PostSearch(String title) {
        this.title = title;
    }
}
