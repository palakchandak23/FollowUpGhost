package service;

import model.Commitment;
import java.time.LocalDate;
import java.util.*;

public class StatsManager {

    private List<Commitment> all = new ArrayList<>();

    public void add(Commitment c) {
        all.add(c);
    }

    public void showStats() {
        int total = all.size();
        int completed = 0;
        int overdue = 0;

        LocalDate today = LocalDate.now();

        for (Commitment c : all) {
            if (c.isCompleted()) completed++;
            else if (today.isAfter(c.getDeadline())) overdue++;
        }

        double score = total == 0 ? 0 : (completed * 100.0 / total);

        System.out.println("\nSTATS");
        System.out.println("Total: " + total);
        System.out.println("Completed: " + completed);
        System.out.println("Overdue: " + overdue);
        System.out.println("Reliability Score: " + score + "%");
    }
}
