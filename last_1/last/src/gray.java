import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class gray {
	/*�վ�RGB�T���A�ӵۤ����վ�*/	
    public static void main(String[] args){
        try{
            BufferedImage sourceImage = ImageIO.read(new File("Lenna.jpg"));
            Gray(sourceImage);
        }catch(Exception e){

        }
    }

    public static int[] Gray(BufferedImage RGB_img){
        int width = RGB_img.getWidth();
        int height = RGB_img.getHeight();
        int [] pixels = new int[width * height];
        int [] pixels_result = new int[width * height];

        // 取得RGB pixels 值
        RGB_img.getRGB(0, 0, width, height, pixels, 0, width);
        
        for(int i = 0; i < width*height ; i++){
            int rgb = pixels[i];
            
            int red = (rgb >> 16) & 0xff;  
            int green= (rgb >> 8) & 0xff;  
            int blue= rgb & 0xff;		   
            // 灰階公式：gray=R*0.299+G*0.587+B*0.114
            int gray = (int)(0.299 * red + 0.587 * green + 0.114 * blue); 
            pixels[i] = (0xff000000 | gray<<16 | gray<<8 | gray); 
            pixels_result[i] = gray; 
        }
        
        
        BufferedImage gray_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        gray_image.setRGB(0, 0, width, height, pixels, 0, width);
        try {
            File file_gray = new File("gray.bmp");
            ImageIO.write(gray_image, "bmp", file_gray);
        }catch (Exception e) {

            e.printStackTrace();
        }

        return pixels_result;
    }

}
