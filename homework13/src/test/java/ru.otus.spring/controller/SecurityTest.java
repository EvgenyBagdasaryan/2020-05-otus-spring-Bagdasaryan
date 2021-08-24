package ru.otus.spring.controller;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.repo.UserRepository;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;
import ru.otus.spring.service.GenreService;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentScan("ru.otus.spring.service")
@WebMvcTest(controllers = BookController.class)
@MockBeans({@MockBean(BookService.class), @MockBean(AuthorService.class), @MockBean(GenreService.class), @MockBean(CommentService.class), @MockBean(UserRepository.class)})
class SecurityTest {

    @Autowired
    private MockMvc mockMvc;


    @WithMockUser(username = "user2", authorities = {"BANNED_USER"})
    @ParameterizedTest
    @ValueSource(strings = {"/comment", "comment/delete/1", "comment/addcomment", "comment/edit/1"})
    void shouldForbiddenAllCommentPages(String value) throws Exception {
        mockMvc.perform(post(value)).andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @ValueSource(strings = {"/comment", "comment/delete/1", "comment/addcomment", "comment/edit/1"})
    void commentParameterizedNotAuthenticated(String value) throws Exception {
        mockMvc.perform(post(value)).andExpect(unauthenticated());
    }

    @WithMockUser(username = "admin")
    @ParameterizedTest
    @ValueSource(strings = {"/comment", "comment/delete/1", "comment/addcomment", "comment/edit/1"})
    void commentParameterizedAuthenticated(String value) throws Exception {
        mockMvc.perform(post(value)).andExpect(authenticated());
    }

    @WithMockUser(username = "user2", authorities = {"BANNED_USER"})
    @ParameterizedTest
    @ValueSource(strings = {"/", "/addbook", "/delete/1", "/edit/1", "/view/1"})
    void shouldForbiddenAllPages(String value) throws Exception {
        mockMvc.perform(post(value)).andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @ValueSource(strings = {"/", "/addbook", "/delete/1", "/edit/1", "/view/1"})
    void parameterizedNotAuthenticated(String value) throws Exception {
        mockMvc.perform(post(value)).andExpect(unauthenticated());
    }

    @WithMockUser(username = "admin")
    @ParameterizedTest
    @ValueSource(strings = {"/", "/addbook", "/delete/1", "/edit/1", "/view/1"})
    void parameterizedAuthenticated(String value) throws Exception {
        mockMvc.perform(post(value)).andExpect(authenticated());
    }

}
