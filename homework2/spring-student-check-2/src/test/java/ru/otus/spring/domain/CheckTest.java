package ru.otus.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Check")
public class CheckTest {

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Check check = new Check(1, "Vvedite Familiu i Imia", "FIO");

        assertEquals("Vvedite Familiu i Imia", check.getQuestion().trim());
        assertEquals("FIO", check.getAnswer().trim());
    }
}
