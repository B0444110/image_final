import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class pepper {
/*產生一個亂數，並在極值的部分將圖像像素值改成255或是0*/
    public static int[] Pepper(int [] gamma_img, int width, int height){
        int [] pixels = new int[gamma_img.length];
        int [] pepper_result = new int[gamma_img.length];

        for(int i = 0; i < gamma_img.length; i++){
            pepper_result[i] = gamma_img[i];
        }

        Random random = new Random();
        for(int i = 0; i < gamma_img.length; i++){

            int pepper = random.nextInt(10);

            if(pepper == 9){
                pepper_result[i]=0;
            }
            else if(pepper == 0){
                pepper_result[i]=255;
            }
            pixels[i] = (0xff000000 | pepper_result[i] << 16 | pepper_result[i] << 8 | pepper_result[i]);
        }

        BufferedImage pepper_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pepper_image.setRGB(0, 0, width, height, pixels, 0, width);

        try {
            File file_pepper = new File("peper.bmp");
            ImageIO.write(pepper_image, "bmp", file_pepper);
        }catch (Exception e) {

            e.printStackTrace();
        }

        return pepper_result;
    } 
}
