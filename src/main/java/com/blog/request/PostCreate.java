package com.blog.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class PostCreate {

    @NotBlank(message = "제목이 필요해요.")
    private String title;

    @NotBlank(message = "내용이 필요해요.")
    private String content;

    @Builder
    public PostCreate(String title, String content){
        this.title = title;
        this.content = content;
    }
    //빌더의 장점
    // - 가독성이 좋다.
    // - 값 생성에 대한 유연함
    // - 필요한 값만 받을 수 있다.
    // - 객체의 불변성을 확보할 수 있다.

}
