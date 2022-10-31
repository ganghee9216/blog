package com.blog.controller;

import com.blog.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class PostController {
    //ssr -> jsp, thymeleaf, mustache, freemarker
        // -> html rendering
    //spa -> vue
        // -> javascript + <-> API(JSON)

    //Http method
    //GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT
    //글 등록
    //POST

    //3번 이상 반복작업이 이루어지면 뭔가 잘못하고 있는게 아닐지 의심하자
    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreate params, BindingResult result) throws Exception {
        //데이터를 검증하는 이유

        // 1. client 개발자가 깜박할 수 있다. 실수로 값을 안 보낼 수 있다.
        // 2. client bug로 값이 누락될 수 있다.
        // 3. 외부의 개입으로 값이 임의로 조작될 수 있다.
        // 4. DB에 값을 저장할 때 의도치 않은 오류가 발생할 수 있다.
        // 5. 서버 개발자의 편안함을 위해서
        if(result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField(); // title
            String errorMessage = firstFieldError.getDefaultMessage(); // 에러 메세지

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);
            return error;
        }

        return Map.of();
    }

}
