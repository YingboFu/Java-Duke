import edu.duke.*;
import java.util.*;

public class VBTester {
    public void sliceStringTester () {
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.sliceString("abcdefghijklm", 0, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 4, 5));
    }
    
    public void tryKeyLengthTester () {
        FileResource fr = new FileResource();
        String tmp = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(Arrays.toString(vb.tryKeyLength(tmp, 4, 'e')));
    }
    
    public void mostCommonCharInTester () {
        FileResource fr = new FileResource("dictionaries/English");
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.mostCommonCharIn(vb.readDictionary(fr)));
    }
}
