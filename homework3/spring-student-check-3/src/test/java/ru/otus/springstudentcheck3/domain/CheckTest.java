package ru.otus.springstudentcheck3.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Check")
@SpringBootTest
public class CheckTest {

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Check check = new Check(1, "Vvedite Familiu i Imia", "FIO");

        assertEquals("Vvedite Familiu i Imia", check.getQuestion().trim());
        assertEquals("FIO", check.getAnswer().trim());
    }
}
