import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockAvoidance {

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            acquireLocks(lock1, lock2);
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            acquireLocks(lock2, lock1);
        }, "Thread-2");

        t1.start();
        t2.start();
    }

    public static void acquireLocks(Lock lock1, Lock lock2) {
        while (true) {
            boolean gotFirst = false;
            boolean gotSecond = false;

            try {
                gotFirst = lock1.tryLock();
                gotSecond = lock2.tryLock();

                if (gotFirst && gotSecond) {
                    System.out.println(Thread.currentThread().getName() +
                            " acquired both locks, performing critical section.");
                    break;
                }
            } finally {
                if (gotFirst && !gotSecond) {
                    lock1.unlock();
                }
                if (!gotFirst && gotSecond) {
                    lock2.unlock();
                }
            }

            // Prevent tight loop (important!)
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Release both locks safely
        lock2.unlock();
        lock1.unlock();

        System.out.println(Thread.currentThread().getName() + " released both locks.");
    }
}