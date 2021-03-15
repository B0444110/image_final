import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import javax.imageio.ImageIO;

public class gamma {
	
	public static int[] Gamma(int [] gray_img, int width, int height, double gamma_value){
        int max = -1;
        int min = 256;
        int [] pixels = new int[gray_img.length];
        int [] pixels_result = new int[gray_img.length];
        
        for(int i = 0; i < gray_img.length; i++){ 
            if(gray_img[i] > max){
                max = gray_img[i];
            }
            if(gray_img[i] < min){
                min = gray_img[i];
            }
        }
        
         /*
          * Gamma值小於1
          [(p(i,j)-min/max-min)^gamma]*255
          p(i,j)為像素點的值，min為圖片中像素之最小值，max為圖片中像素之最大值

         */
        
        for(int i = 0; i < gray_img.length; i++){
            double gamma_double = Math.pow((double)(gray_img[i] - min)/(max - min), gamma_value)*255;
            //double gamma_double = Math.pow((double)(gray_img[i])/255, gamma_value)*255;
            int gamma = (int) gamma_double;
            pixels[i] = (0xff000000 | gamma << 16 | gamma << 8 | gamma);
            pixels_result[i] = gamma;
        }

        BufferedImage gama_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
        gama_image.setRGB(0, 0, width, height, pixels, 0, width);

        try {
            File file_gama = new File("gamma_" + gamma_value + ".bmp");
            ImageIO.write(gama_image, "bmp", file_gama);
        }catch (Exception e) {

            e.printStackTrace();
            System.out.println("error!");
        }

        return pixels_result;
    }
	

}
