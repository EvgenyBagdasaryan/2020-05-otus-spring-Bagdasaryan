package ru.otus.springstudentcheck4.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование ScanningCSV")
public class ScanningCSVTest {

    @DisplayName("Считывание csv файла")
    @Test
    public void getScannedCSV(){
        ScanningCSV scan = new ScanningCSV();
        scan.setFilePathName("/test.txt");
        String allChecks = scan.getScannedCSV();
        Assertions.assertThat(allChecks)
                .isNotEmpty()
                .contains("test question");
    }
}