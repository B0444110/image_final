import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class otsu {
//設一門檻值(數值通常取所有像素之平均值)，大於門檻值則設255，小於則設0
    public static void OTSU(int [] gamma_img, int width, int height){
        int threshold = 0;
        int total = 0;
        int [] pixels = new int[width * height];
        int [] otsu_result = new int[width * height];

        for (int i = 0; i < gamma_img.length ;i++){
            otsu_result[i] = gamma_img[i];
        }

        for(int i = 0; i < gamma_img.length ;i++){
            total += gamma_img[i];
        }

        threshold = total / (width*height);

        for(int i = 0; i < gamma_img.length; i++){
            if(gamma_img[i] >= threshold)
                otsu_result[i] = 255;
            else
                otsu_result[i] = 0;

            pixels[i] = (0xff000000 | otsu_result[i] << 16 | otsu_result[i] << 8 | otsu_result[i]);
        }

        BufferedImage otsu_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        otsu_image.setRGB(0, 0, width, height, pixels, 0, width);

        try {
            File file_otsu = new File("otsu.bmp");
            ImageIO.write(otsu_image, "bmp", file_otsu);
        }catch (Exception e) {

            e.printStackTrace();
        }
    }

}
