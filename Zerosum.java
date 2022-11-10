/*
 * Arthur Wash
 * Homework5
 * CS602
 */

public class Zerosum extends Thread {
    /*
     * calories[0] = gain
     * calories[1] = burn
     * calories[2] = balance
     */
    int calories[] = {0, 0, 0};

    public void gainCalories() {
        for (int i = 0; i < 1000; i++) {
            synchronized (calories) {
                calories[0]++;
                calories[2]++;
            }
        }
    }

    public void burnCalories() {
        while (calories[1] < 1000) {
            synchronized (calories) {
                    calories[1]++;
                    calories[2]--;
            }
        }
    }

    public void run() {
        if (Thread.currentThread().getName().compareTo("gain") == 0) {
            this.gainCalories();
        } else
            this.burnCalories();
    }
    
    public static void main(String[] args) {
        Zerosum zSum = new Zerosum();
        Thread t1 = new Thread(zSum, "gain");
        Thread t2 = new Thread(zSum, "burn");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {System.out.println("Error: " + e.getStackTrace());}
        System.out.println("Final Balance: " + zSum.calories[2]);
    }
}