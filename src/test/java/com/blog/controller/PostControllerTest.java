package com.blog.controller;

import com.blog.domain.Post;
import com.blog.repository.PostRepository;
import com.blog.request.PostCreate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private ObjectMapper objectMapper;

   @Autowired
   private PostRepository postRepository;

   @BeforeEach
   void clean(){
      postRepository.deleteAll();
   }

   @Test
   @DisplayName("/posts 요청 시 hello world를 출력한다")
   void test() throws Exception{

      //given
      PostCreate request = PostCreate.builder()
              .title("제목입니다")
              .content("내용입니다.")
              .build();

      String json = objectMapper.writeValueAsString(request);

      //expected
      mockMvc.perform(post("/posts")
                      .contentType(APPLICATION_JSON)
                      .content(json)
              ) // application/json
              .andExpect(status().isOk())
              .andExpect(content().string(""))
              .andDo(print());
   }
   @Test
   @DisplayName("/posts 요청 시 title값은 필수다.")
   void test2() throws Exception{

      //given
      PostCreate request = PostCreate.builder()
              .content("내용입니다.")
              .build();

      String json = objectMapper.writeValueAsString(request);
      //expected
      mockMvc.perform(post("/posts")
                      .contentType(APPLICATION_JSON)
                      .content(json)
              ) // application/json
              .andExpect(status().isBadRequest())
              .andExpect(jsonPath("$.code").value("400"))
              .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
              .andExpect(jsonPath("$.validation.title").value("제목이 필요해요."))
              .andDo(print());
   }
   @Test
   @DisplayName("/posts 요청 시 DB에 값이 저장된다.")
   void test3() throws Exception {

      //given
      PostCreate request = PostCreate.builder()
              .title("제목입니다.")
              .content("내용입니다.")
              .build();

      String json = objectMapper.writeValueAsString(request);
      //when
      mockMvc.perform(post("/posts")
                      .contentType(APPLICATION_JSON)
                      .content(json)
              )
              .andExpect(status().isOk())
              .andDo(print());

      //then
      assertEquals(1L, postRepository.count());

      Post post = postRepository.findAll().get(0);
      assertEquals("제목입니다.", post.getTitle());
      assertEquals("내용입니다.", post.getContent());
   }
   @Test
   @DisplayName("글 한 개 조회")
   void test4() throws Exception {
      //given
      Post post = Post.builder()
              .title("123456789012345")
              .content("bar")
              .build();
      postRepository.save(post);

      //expected
      mockMvc.perform(get("/posts/{postId}", post.getId())
                      .contentType(APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.id").value(post.getId()))
              .andExpect(jsonPath("$.title").value("1234567890"))
              .andExpect(jsonPath("$.content").value("bar"))
              .andDo(print());
   }
}