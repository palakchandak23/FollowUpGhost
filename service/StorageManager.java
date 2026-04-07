package service;

import model.Commitment;
import java.util.*;

public class StorageManager {

    private Map<String, List<Commitment>> map = new HashMap<>();

    public void addCommitment(Commitment c) {
        map.putIfAbsent(c.getPerson(), new ArrayList<>());
        map.get(c.getPerson()).add(c);
    }

    public void displayAll() {
        for (String person : map.keySet()) {
            System.out.println("\n👤 " + person);
            for (Commitment c : map.get(person)) {
                System.out.println(c);
            }
        }
    }
}