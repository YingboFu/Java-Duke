import edu.duke.*;
import java.io.File;


public class BatchGrayscale {
    public void batchGrayscale () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
            
            for (Pixel p : outImage.pixels()) {
                Pixel inP = inImage.getPixel(p.getX(), p.getY());
                int average = (inP.getRed() + inP.getGreen() + inP.getBlue()) / 3;
                p.setRed(average);
                p.setGreen(average);
                p.setBlue(average);
            }
            
            String newName = "gray-" + inImage.getFileName();
            outImage.setFileName("images/" + newName);
            outImage.save();
        }
    }
    
}
