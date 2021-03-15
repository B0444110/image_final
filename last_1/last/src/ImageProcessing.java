import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessing {
    public static void main(String args[]){
        
        File file  = new File("C:\\Users\\user\\Desktop\\last_1\\last\\Lenna.jpg");
        try {
        	
            BufferedImage RGB_img = ImageIO.read(file);
            int height = RGB_img.getHeight();
            int width = RGB_img.getWidth();
            int [] gray_img = new int[width * height];
            
            gray_img = gray.Gray(RGB_img); // �Ƕ�
            
            negative.Negative(gray_img, width, height); // �t��
            
            int [] gamma_img1 = new int[width * height];
            int [] gamma_img2 = new int[width * height];
            int [] gamma_img3 = new int[width * height];
            gamma_img1 = gamma.Gamma(gray_img, width, height, 0.5); // Gamma值<1
            gamma_img2 = gamma.Gamma(gray_img, width, height, 1); // Gamma值=1
            gamma_img3 = gamma.Gamma(gray_img, width, height, 2); // Gamma值>1

            
            int [] pepper_img = new int[width * height];
            pepper_img = pepper.Pepper(gamma_img1, width, height); // (Salt and pepper)
            
            median.Median(pepper_img, width, height); // 3x3 
            
            Maxfilter.main(args); // 3x3 
            
            otsu.OTSU(gamma_img3, width, height); // 二值化
            
            laplacianOpenCV.main(args); // Laplacian
            
        } catch (IOException e) {

            e.printStackTrace();
        }
    }    
}