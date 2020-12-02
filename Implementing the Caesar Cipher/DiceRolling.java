import java.util.Random;

public class DiceRolling {
    void simulate (int rolls) {
        Random rand = new Random();
        int[] counts = new int[13];
        for (int i = 0; i < rolls; i++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            
            counts[d1 + d2] ++;
            System.out.println(d1 + " + " + d2 + " = " + (d1 + d2));
        }
        
        for (int i = 2; i < counts.length; i++) {
            System.out.println(i + "'s=\t" + counts[i] + "\t" + 100.0 * counts[i] / rolls);
        }
    }
}
