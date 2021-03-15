import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import java.util.Arrays;

public class Maxfilter {
	public static void main(String[] args){
        try{
        	File laplacian_file  = new File("C:\\Users\\MIS\\Desktop\\HigerImage\\last\\src\\Laplacian.jpg");
            BufferedImage laplacian_img = ImageIO.read(laplacian_file);
            int height2 = laplacian_img.getHeight();
            int width2 = laplacian_img.getWidth();
            int [] laplacian_pixels = new int[width2 * height2];
            laplacian_img.getRGB(0, 0, width2, height2, laplacian_pixels, 0, width2);
            Maxfilter.Max(laplacian_pixels,width2,height2);
        }catch(Exception e){

        }
    }
	
	public static void Max(int [] origin_img, int width, int height){
        int [] pixels = new int[origin_img.length];
        int [] max_result = new int[origin_img.length];

        for(int i = 0; i < origin_img.length; i++){
            max_result[i] = origin_img[i];
        }
/**/
        for(int i = 1; i < width - 1; i++){
            for(int j = 1; j < height - 1 ; j++){
                int [] window = new int[9];

                window[0]=origin_img[width*j + i - width - 1]; 
                window[1]=origin_img[width*j + i - width];
                window[2]=origin_img[width*j + i - width + 1];
                window[3]=origin_img[width*j + i - 1];
                window[4]=origin_img[width*j + i];
                window[5]=origin_img[width*j + i + 1];
                window[6]=origin_img[width*j + i + width - 1];
                window[7]=origin_img[width*j + i + width];
                window[8]=origin_img[width*j + i + width + 1];
                
                int temp = window[4]; 
                Arrays.sort(window);               
                if(temp>window[8]) {
                	max_result[width*j + i] = window[8];
                }
           }
        }

        for(int i = 0; i < origin_img.length; i++){
            pixels[i] = (0xff000000 | max_result[i] << 16 | max_result[i] << 8 | max_result[i]);
        }

        BufferedImage max_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        max_image.setRGB(0, 0, width, height, pixels, 0, width);

        try {
            
            File file_max = new File("max.bmp");
            ImageIO.write(max_image, "bmp", file_max);
            
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}