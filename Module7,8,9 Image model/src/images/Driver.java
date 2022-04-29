package images;

/**
 * Driver class is a class that test the ImageModel and ConcreteImageModel.
 */
public class Driver {

  /**
   * This is the main function of the Driver Class.
   */
  public static void main(String[] args) {
    
    String filename = "side-walk";
    
    ImageModel model = new ConcreteImageModel();
    
    model.loadImage(filename + ".png");
    model.applyBlur();
    model.saveImage(filename + "-blurred.png");
    model.applyBlur();
    model.saveImage(filename + "-blurred-2.png");

    model.loadImage(filename + ".png");
    model.applySharpen();
    model.saveImage(filename + "-sharpen.png");
    model.applySharpen();
    model.saveImage(filename + "-sharpen-2.png");

    model.loadImage(filename + ".png");
    model.applyGrayscale();
    model.saveImage("side-walk-grayscale.png");

    model.loadImage(filename + ".png");
    model.applySepia();
    model.saveImage("side-walk-sepia.png");

    model.loadImage(filename);
    model.applyDither();
    model.saveImage("manhattan-small-dither.png");
  
    model.loadImage(filename + ".png");
    model.applyMosaic(15000);
    model.saveImage("side-walk-mosaic-15000.png");
  
    model.loadImage(filename + ".png");
    model.applyMosaic(8000);
    model.saveImage("side-walk-mosaic-8000.png");
  
    model.loadImage(filename + ".png");
    model.applyMosaic(4000);
    model.saveImage("side-walk-mosaic-4000.png");
  
    model.loadImage(filename + ".png");
    model.applyMosaic(1000);
    model.saveImage("side-walk-mosaic-1000.png");
  }
}
