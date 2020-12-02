import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay () {
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    private void update (String person) {
        int index = names.indexOf(person);
        if (index == -1) {
            names.add(person);
            counts.add(1);
        } else {
            int value = counts.get(index);
            counts.set(index, value + 1);
        }
    }
    
    private void findAllCharacters () {
        names.clear();
        counts.clear();
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            int index = line.indexOf(".");
            if (index != -1) {
                String name = line.substring(0, index);
                update(name);
                
            }
        }
    }
    
    private void charactersWithNumParts (int num1, int num2) {
       for (int i = 0; i < names.size(); i++) {
           if (counts.get(i) >= num1 && counts.get(i) <= num2) {
               System.out.println(names.get(i) + "\t" + counts.get(i));
           }
       } 
    } 
    
    public void tester () {
        findAllCharacters();
        System.out.println();
        charactersWithNumParts(10,15); 
    }
}
