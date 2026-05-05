import java.util.*;

public class LoadBalancing {

    static void printLoad(int servers, int processes) {
        if (servers <= 0) {
            System.out.println("No servers available to distribute load.");
            return;
        }

        int each = processes / servers;
        int extra = processes % servers;

        for (int i = 0; i < servers; i++) {
            int load = (i < extra) ? each + 1 : each;
            System.out.println("Server " + (i + 1) + " has " + load + " Processes");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of Servers: ");
        int servers = sc.nextInt();

        System.out.print("Enter the number of Processes: ");
        int processes = sc.nextInt();

        while (true) {
            System.out.println("\n--- Load Distribution ---");
            printLoad(servers, processes);

            System.out.println("\n1. Add Servers");
            System.out.println("2. Remove Servers");
            System.out.println("3. Add Processes");
            System.out.println("4. Remove Processes");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("How many servers to add? ");
                    servers += sc.nextInt();
                    break;

                case 2:
                    System.out.print("How many servers to remove? ");
                    int removeServers = sc.nextInt();
                    if (servers - removeServers <= 0) {
                        System.out.println("Cannot have zero or negative servers!");
                    } else {
                        servers -= removeServers;
                    }
                    break;

                case 3:
                    System.out.print("How many processes to add? ");
                    processes += sc.nextInt();
                    break;

                case 4:
                    System.out.print("How many processes to remove? ");
                    int removeProcesses = sc.nextInt();
                    if (processes - removeProcesses < 0) {
                        System.out.println("Processes cannot be negative!");
                    } else {
                        processes -= removeProcesses;
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}