package service;

import model.Commitment;
import java.time.LocalDate;
import java.util.regex.*;

public class CommitmentExtractor {

    public Commitment extract(String emailText, String person) {

        Pattern pattern = Pattern.compile(
            "(will|I'll|let me|going to)\\s+(send|check|schedule|share|update|call|do|complete|be done|surely)",
            Pattern.CASE_INSENSITIVE
        );

        Matcher matcher = pattern.matcher(emailText);

        if (!matcher.find()) return null;

        String task = matcher.group();
        LocalDate deadline = parseDeadline(emailText);

        return new Commitment(task, person, deadline); // ✅ FIXED
    }

    private LocalDate parseDeadline(String text) {
        text = text.toLowerCase();

        if (text.contains("tomorrow")) return LocalDate.now().plusDays(1);
        if (text.contains("today")) return LocalDate.now();
        if (text.contains("friday")) return LocalDate.now().plusDays(3);
        if (text.contains("monday")) return LocalDate.now().plusDays(5);

        return LocalDate.now().plusDays(2);
    }
}