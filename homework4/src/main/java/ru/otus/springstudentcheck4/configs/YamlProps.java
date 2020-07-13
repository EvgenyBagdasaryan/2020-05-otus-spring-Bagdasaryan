package ru.otus.springstudentcheck4.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class YamlProps {

    private String filePath;
    private int successfullyNumberValidChecks;
    private Locale locale;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getSuccessfullyNumberValidChecks() {
        return successfullyNumberValidChecks;
    }

    public void setSuccessfullyNumberValidChecks(int successfullyNumberValidChecks) {
        this.successfullyNumberValidChecks = successfullyNumberValidChecks;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
