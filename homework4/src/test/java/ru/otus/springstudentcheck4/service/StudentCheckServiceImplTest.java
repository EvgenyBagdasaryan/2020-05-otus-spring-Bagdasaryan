package ru.otus.springstudentcheck4.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springstudentcheck4.configs.YamlProps;
import ru.otus.springstudentcheck4.dao.ChecksDao;
import ru.otus.springstudentcheck4.domain.Check;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("Методы сервиса проверки должны ")
@SpringBootTest(classes = StudentCheckServiceImpl.class)
public class StudentCheckServiceImplTest {

    @MockBean
    private ChecksDao checksDao;
    @MockBean
    private YamlProps props;
    @MockBean
    IOService ioService;

    @Autowired
    private StudentCheckService studentService;

    @Test
    @DisplayName("вызывать методы studentService с нужными параметрами")
    void getCheckByNum() {

        Check check = new Check(1, "test question", "test answer");

        when(checksDao.getCheckByNum(Mockito.anyInt()))
                .thenReturn(check);

        assertThat(studentService.getCheckByNum(72).getQuestion())
                .isNotNull()
                .isEqualTo("test question");

        assertThat(studentService.getCheckByNum(72).getAnswer())
                .isNotNull()
                .isEqualTo("test answer");

        verify(checksDao, times(2)).getCheckByNum(72);
    }
}
