package ru.otus.springstudentcheck4.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Check")
public class CheckTest {

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Check check = new Check(1, "test question", "test answer");

        assertEquals("test question", check.getQuestion().trim());
        assertEquals("test answer", check.getAnswer().trim());
    }
}
