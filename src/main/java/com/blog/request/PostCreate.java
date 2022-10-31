package com.blog.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class PostCreate {

    @NotBlank(message = "제목이 필요해요.")
    private String title;

    @NotBlank(message = "내용이 필요해요.")
    private String content;

}
