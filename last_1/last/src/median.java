import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class median {

    public static void Median(int [] peper_img, int width, int height){
        int [] pixels = new int[peper_img.length];
        int [] median_result = new int[peper_img.length];

        for(int i = 0; i < peper_img.length; i++){
            median_result[i] = peper_img[i];
        }
/*將九個值進行排序，利用中間的值取代最大的值（即九宮格右下的值）。*/
        for(int i = 1; i < width - 1; i++){
            for(int j = 1; j < height - 1 ; j++){
                int [] window = new int[9];

                window[0]=peper_img[width*j + i - width - 1]; //左上
                window[1]=peper_img[width*j + i - width]; //上
                window[2]=peper_img[width*j + i - width + 1]; //右上
                window[3]=peper_img[width*j + i - 1]; //左
                window[4]=peper_img[width*j + i]; //中
                window[5]=peper_img[width*j + i + 1]; //右
                window[6]=peper_img[width*j + i + width - 1]; //左下
                window[7]=peper_img[width*j + i + width]; //下
                window[8]=peper_img[width*j + i + width + 1]; //右下

                Arrays.sort(window);
                median_result[width*j + i] = window[4]; //取代
            }
        }

        for(int i = 0; i < peper_img.length; i++){
            pixels[i] = (0xff000000 | median_result[i] << 16 | median_result[i] << 8 | median_result[i]);
        }

        BufferedImage median_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        median_image.setRGB(0, 0, width, height, pixels, 0, width);

        try {
            File file_median = new File("median.bmp");
            ImageIO.write(median_image, "bmp", file_median);
        }catch (Exception e) {

            e.printStackTrace();
        }
    }

}
