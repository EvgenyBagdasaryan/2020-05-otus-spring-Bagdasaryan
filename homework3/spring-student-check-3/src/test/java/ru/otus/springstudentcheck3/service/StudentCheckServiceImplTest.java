package ru.otus.springstudentcheck3.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springstudentcheck3.configs.YamlProps;
import ru.otus.springstudentcheck3.dao.ChecksDao;
import ru.otus.springstudentcheck3.domain.Check;

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
    private ExaminationService es;

    @Autowired
    private StudentCheckService studentService;

    @Test
    @DisplayName("вызывать методы checksDao с нужными параметрами")
    void getCheckByNum() {

        when(studentService.getCheckByNum(eq(1))).thenReturn(new Check(1, "Vvedite Familiu i Imia", "FIO"));

        given(checksDao.getCheckByNum(eq(1)))
                .willReturn(new Check(1, "Vvedite Familiu i Imia", "FIO"));
        assertThat(studentService.getCheckByNum(1)).isNotNull();
        assertThat(studentService.getCheckByNum(1).getQuestion().trim()).isEqualTo("Vvedite Familiu i Imia");
    }
}
