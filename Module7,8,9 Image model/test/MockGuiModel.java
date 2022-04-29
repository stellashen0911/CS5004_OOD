
import images.ImageModel;
import java.awt.image.BufferedImage;

/**
 * MockGuiModel class is a mock Gui Model class.
 * This mock model is aiming to test the GUI controller.
 */
public class MockGuiModel implements ImageModel {
  private StringBuilder log;
  
  /**
   * Constructs a mock image model with given string builder.
   * @param log the string representation
   */
  public MockGuiModel(StringBuilder log) {
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
    log.append("sharpen Success!");
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
    log.append("Mosaic Success!");
    return;
  }

  @Override
  public BufferedImage convertToImageFile() {
    BufferedImage test = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
    return test;
  }
  
}

