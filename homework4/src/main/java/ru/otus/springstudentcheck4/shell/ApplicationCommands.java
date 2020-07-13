package ru.otus.springstudentcheck4.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.springstudentcheck4.service.StudentCheckService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final StudentCheckService studentCheckService;
    private String userName;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "AnyUser") String userName) {
        this.userName = userName;
        return String.format("Добро пожаловать: %s", userName);
    }

    @ShellMethod(value = "Examination students", key = {"e", "examination"})
    @ShellMethodAvailability(value = "isCommandAvailable")
    public String examinationStudents() {
        return studentCheckService.examination();
    }

    private Availability isCommandAvailable() {
        return userName == null? Availability.unavailable("Сначала залогиньтесь"): Availability.available();
    }
}
