package com.blog.controller;

import com.blog.request.PostCreate;
import com.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//ssr -> jsp, thymeleaf, mustache, freemarker
// -> html rendering
//spa -> vue
// -> javascript + <-> API(JSON)
//Http method
//GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT
//글 등록
//POST
//3번 이상 반복작업이 이루어지면 뭔가 잘못하고 있는게 아닐지 의심하자

//데이터를 검증하는 이유
// 1. client 개발자가 깜박할 수 있다. 실수로 값을 안 보낼 수 있다.
// 2. client bug로 값이 누락될 수 있다.
// 3. 외부의 개입으로 값이 임의로 조작될 수 있다.
// 4. DB에 값을 저장할 때 의도치 않은 오류가 발생할 수 있다.
// 5. 서버 개발자의 편안함을 위해서
// 단점
// 1. 매번 메소드마다 값을 검증 해야한다.
//      > 개발자가 까먹을 수 있다.
//      > 지겨운 반복 작업
//      > 검증 부분에서 버그가 발생할 여지가 높다.
// 2. 응답값에 hashmap -> 응답 클래스를 만들어주는게 좋다.
// 3. 여러개의 에러처리가 힘들다
// 4. 세번 이상의 반복적인 작업은 피해야한다.
// 5. 코드 && 개발에 관한 모든 것
@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
   @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreate request) throws Exception {

       //변경할 수 없는 맵을 만들어준다.
       return Map.of();
    }

}
