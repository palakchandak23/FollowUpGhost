package service;

import model.Commitment;
import java.util.PriorityQueue;

public class Prioritymanager {

    private PriorityQueue<Commitment> pq = new PriorityQueue<>(
        (a, b) -> Integer.compare(a.getPriorityScore(), b.getPriorityScore())
    );

    public void addCommitment(Commitment c) {
        pq.add(c);
    }

    public Commitment getTopPriority() {
        while (!pq.isEmpty() && pq.peek().isCompleted()) {
            pq.poll(); // remove completed
        }
        return pq.isEmpty() ? null : pq.peek();
    }

    public void markCompleted(int id) {
        for (Commitment c : pq) {
            if (c.getId() == id) {
                c.markCompleted();
                return;
            }
        }
        System.out.println("ID not found");
    }

    public void displayAll() {
        if (pq.isEmpty()) {
            System.out.println("No commitments.");
            return;
        }

        for (Commitment c : pq) {
            if (!c.isCompleted()) {
                System.out.println(c);
            }
        }
    }
}
