package ru.otus.spring.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DocumentNotFoundException extends Exception {

    private String documentId;
    private String documentName;

    @Override
    public String toString() {
        return "Объект [" + documentName + "] c id = " + documentId + " отсутствует";
    }
}
