import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class negative {
	/*�N��լۤϡA255-�칳����*/
    public static void Negative(int[] gray_img, int width, int height){
        int [] pixels = new int[gray_img.length];

        for(int i = 0; i < gray_img.length; i++){
            //負片:255 - 原像素值
            int negative = 255 - gray_img[i];
            pixels[i] = (0xff000000 | negative<<16 | negative<<8 | negative);
        }

        BufferedImage negative_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        negative_image.setRGB(0, 0, width, height, pixels, 0, width);

        try {
            File file_negative = new File("negative.bmp");
            ImageIO.write(negative_image, "bmp", file_negative);
        }catch (Exception e) {}
    }

}
