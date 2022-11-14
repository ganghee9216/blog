package com.blog.controller;

import com.blog.request.PostCreate;
import com.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public void post(@RequestBody @Valid PostCreate request) throws Exception {
       //Case 1. 저장한 데이터 entity -> response로 응답하기
       //Case 2. 저장한 데이터의 primary_id -> response로 응답하기
       //   Client에서는 수신한 id를 글 조회 API를 통해서 글 데이터를 수신받음
       //Case 3. 응답 필요없음 -> 클라이언트에서 모든 post(글) 데이터 context를 잘 관리함
       //Bad Case : 서버에서 -> 반드시 이렇게 할껍니다! fix
       //   -> 서버에서 차라리 유연하게 대응하는게 좋다. -> 코드를 잘 짜야한다.
       //   -> 한 번에 일괄적으로 잘 처리되는 케이스가 없으니 잘 관리하는 형태가 중요하다.
       postService.write(request);
    }

}
