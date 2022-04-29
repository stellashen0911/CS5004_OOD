
import images.ImageModel;
import java.awt.image.BufferedImage;

/**
 * MockImageModel class is a mock Image Model class.
 * This mock model is aiming to test the image controller.
 */
public class MockImageModel implements ImageModel {
  private StringBuilder log;
  
  /**
   * Constructs a mock image model with given string builder.
   * @param log the string representation
   */
  public MockImageModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void loadImage(String filename) throws IllegalArgumentException {
    log.append("Load Success! " + filename);
    return;
  }

  @Override
  public void saveImage(String filename) throws IllegalArgumentException {
    log.append("Save Success! " + filename);
    return;
  }

  @Override
  public void applyBlur() {
    log.append("Blur Success!");
    return;
  }

  @Override
  public void applySharpen() {
    log.append("Sharpen Success!");
    return;
  }

  @Override
  public void applyGrayscale() {
    log.append("Grayscale Success!");
    return;
  }

  @Override
  public void applySepia() {
    log.append("Sepia Success!");
    return;
  }

  @Override
  public void applyDither() {
    log.append("Dither Success!");
    return;
  }

  @Override
  public void applyMosaic(int seeds) throws IllegalArgumentException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public BufferedImage convertToImageFile() {
    return null;
  }
  
}
