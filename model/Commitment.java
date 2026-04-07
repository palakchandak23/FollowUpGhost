package model;

import java.time.LocalDate;

public class Commitment {
    private static int counter = 0;

    private int id;
    private String task;
    private String person;
    private LocalDate deadline;
    private boolean completed;
    private int priorityScore;

    public Commitment(String task, String person, LocalDate deadline) {
        this.id = ++counter;
        this.task = task;
        this.person = person;
        this.deadline = deadline;
        this.completed = false;
        this.priorityScore = calculatePriority();
    }

    private int calculatePriority() {
        int daysLeft = (int) java.time.temporal.ChronoUnit.DAYS
                .between(LocalDate.now(), deadline);
        return daysLeft;
    }

    public int getId() { return id; }
    public String getTask() { return task; }
    public String getPerson() { return person; }
    public LocalDate getDeadline() { return deadline; }
    public boolean isCompleted() { return completed; }
    public int getPriorityScore() { return priorityScore; }

    public void markCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return "[ID " + id + "] " + task +
                " | Person: " + person +
                " | Deadline: " + deadline +
                " | Completed: " + completed;
    }
}