package jscode.jscodestudy.repository;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class PostSearch {

    @NotBlank
    private String title;

    public PostSearch(String title) {
        this.title = title;
    }
}
