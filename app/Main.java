package app;
import model.Commitment;
import service.CommitmentExtractor;
import service.Prioritymanager;
import service.StorageManager;
import service.StatsManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CommitmentExtractor extractor = new CommitmentExtractor();
        Prioritymanager pq = new Prioritymanager();
        StorageManager sm = new StorageManager();
        StatsManager stats = new StatsManager();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== FollowUpGhost ====");
            System.out.println("1. Add Email");
            System.out.println("2. Show Top Priority");
            System.out.println("3. Mark Complete");
            System.out.println("4. Show All");
            System.out.println("5. Show Stats");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter person: ");
                    String person = sc.nextLine();

                    Commitment commitment = extractor.extract(email, person);

                    if (commitment != null) {
                        pq.addCommitment(commitment);
                        sm.addCommitment(commitment);
                        stats.add(commitment);
                        System.out.println("Commitment added!");
                    } else {
                        System.out.println("No commitment detected.");
                    }
                    break;

                case 2:
                    System.out.println("Top Priority:");
                    System.out.println(pq.getTopPriority());
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // clear buffer
                    pq.markCompleted(id);
                    System.out.println("Marked completed!");
                    break;

                case 4:
                    sm.displayAll();
                    break;

                case 5:
                    stats.showStats();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}
