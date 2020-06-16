package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.ChecksDao;
import ru.otus.spring.domain.Check;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentCheckServiceImplTest {

    @Mock
    private ChecksDao checksDao;

    private StudentCheckService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentCheckServiceImpl(checksDao);
    }

    @Test
    void getCheckByNum() {

        when(studentService.getCheckByNum(eq(1))).thenReturn(new Check(1, "Vvedite Familiu i Imia", "FIO"));

        given(checksDao.getCheckByNum(eq(1)))
                .willReturn(new Check(1, "Vvedite Familiu i Imia", "FIO"));
        assertThat(studentService.getCheckByNum(1)).isNotNull();
        assertThat(studentService.getCheckByNum(1).getQuestion().trim()).isEqualTo("Vvedite Familiu i Imia");
    }
}