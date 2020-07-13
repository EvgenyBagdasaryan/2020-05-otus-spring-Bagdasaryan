package ru.otus.springstudentcheck4.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springstudentcheck4.domain.Check;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование ConsoleIOService")
public class
ConsoleIOServiceTest {

    @MockBean
    private Check check;

    private static final String TEXT_TO_PRINT1 = "выходная строка ";
    private static final String TEXT_TO_PRINT2 = "выходная строка 2";

    @MockBean
    private IOService ioService;
    private ByteArrayOutputStream bos;

    @BeforeEach
    void setUp() {
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        ioService = new ConsoleIOService();
    }

    @DisplayName("должно печатать \"" + TEXT_TO_PRINT1 + "\"")
    @SneakyThrows
    @Test
    void shouldPrintTextToPrint1() {
        ioService.out(TEXT_TO_PRINT1);
        assertThat(bos.toString()).isEqualTo(TEXT_TO_PRINT1 + "\r\n");
    }

    @DisplayName("должно печатать \"" + TEXT_TO_PRINT2 + "\"")
    @SneakyThrows
    @Test
    void shouldPrintTextToPrint2() {
        ioService.out(TEXT_TO_PRINT2);
        assertThat(bos.toString()).isEqualTo(TEXT_TO_PRINT2 + "\r\n");
    }
}
