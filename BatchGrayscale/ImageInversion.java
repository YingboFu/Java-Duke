import edu.duke.*;
import java.io.File;


public class ImageInversion {
    public void imageInversion () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
            
            for (Pixel p : outImage.pixels()) {
                Pixel inP = inImage.getPixel(p.getX(), p.getY());
                
                p.setRed(255 - inP.getRed());
                p.setGreen(255 - inP.getGreen());
                p.setBlue(255 - inP.getBlue());
            }
            
            String newName = "inverted-" + inImage.getFileName();
            outImage.setFileName("invert_images/" + newName);
            outImage.save();
        }
    }
    
}
