package ru.otus.springstudentcheck4.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springstudentcheck4.configs.YamlProps;
import ru.otus.springstudentcheck4.domain.Check;
import ru.otus.springstudentcheck4.service.IOService;
import ru.otus.springstudentcheck4.service.StudentCheckServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Методы сервиса проверки должны ")
@SpringBootTest(classes = StudentCheckServiceImpl.class)
public class ChecksDaoCSVTest {

    @MockBean
    private ChecksDao checksDao;
    @MockBean
    private YamlProps props;
    @MockBean
    private IOService ioService;


    @BeforeEach
    void setUp() {
        when(checksDao.getCheckByNum(Mockito.anyInt())).thenReturn(new Check(1, "test question", "test answer"));
    }

    @Test
    @DisplayName("вызывать методы checksDao с нужными параметрами")
    void getCheckByNum() {

        assertThat(checksDao.getCheckByNum(75).getQuestion())
                .isNotNull()
                .isEqualTo("test question");

        assertThat(checksDao.getCheckByNum(75).getAnswer())
                .isNotNull()
                .isEqualTo("test answer");

        verify(checksDao, times(2)).getCheckByNum(75);
    }
}
