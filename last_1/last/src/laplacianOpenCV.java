import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.highgui.HighGui;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

class LaplaceDemo  {

	public void run(String[] args) {
        // Declare the variables we are going to use
        Mat src, src_gray = new Mat(), dst = new Mat();
        int kernel_size = 3;
        int scale = 1;
        int delta = 0;
        int ddepth = CvType.CV_16S;
        String window_name = "Laplace Demo";
        String imageName = ((args.length > 0) ? args[0] : "C:\\Users\\user\\Desktop\\last_1\\last\\src\\gamma_1.0.bmp");
        src = Imgcodecs.imread(imageName, Imgcodecs.IMREAD_COLOR); // Load an image
       
        // Check if image is loaded fine
        if( src.empty() ) {
            System.out.println("Error opening image");
            System.out.println("Program Arguments: [image_name -- default ../data/lena.jpg] \n");
            System.exit(-1);
        }
        // Reduce noise by blurring with a Gaussian filter ( kernel size = 3 )
        Imgproc.GaussianBlur( src, src, new Size(3, 3), 0, 0, Core.BORDER_DEFAULT ); 
        // Convert the image to grayscale
        Imgproc.cvtColor( src, src_gray, Imgproc.COLOR_RGB2GRAY );
        Mat abs_dst = new Mat();
        Imgproc.Laplacian( src_gray, dst, ddepth, kernel_size, scale, delta, Core.BORDER_DEFAULT );
        // converting back to CV_8U
        Core.convertScaleAbs( dst, abs_dst );
        // HighGui.imshow( window_name, abs_dst );
        Imgcodecs.imwrite("C:\\Users\\user\\Desktop\\last_1\\last\\Laplacian.jpg", dst);
        // HighGui.waitKey(0);
        System.exit(0);
    }
}
public class laplacianOpenCV {
    public static void main(String[] args) {
        // Load the native library.
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new  LaplaceDemo().run(args);
    }
}
