import edu.duke.*;

public class Part4 {
    void findingWebLinks () {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String word : ur.words()) {
            String temp = word.toLowerCase();
            int index = temp.indexOf("youtube.com");
            if (index != -1) {
                int beg = temp.lastIndexOf("\"", index);
                int end = temp.indexOf("\"", index+1);
                System.out.println(word.substring(beg+1, end));
            }
        }
    }
}
