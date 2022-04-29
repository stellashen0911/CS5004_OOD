package images;

import java.awt.image.BufferedImage;

/**
 * ImageModel Interface has methods to load and save image.
 * It can also apply many different filters to the image.
 */
public interface ImageModel {

  /**
   * Load an image into the image model.
   * 
   * @param filename the name of the file containing the image.
   * @throws IllegalArgumentException if the filename is invalid or if something
   *                                  goes wrong loading the image
   */
  public void loadImage(String filename) throws IllegalArgumentException;

  /**
   * Save the data in the image model to a file.
   * 
   * @param filename the name of the file to save to
   * @throws IllegalArgumentException if the filename is invalid or if something
   *                                  goes wrong saving the file
   */
  public void saveImage(String filename) throws IllegalArgumentException;

  /**
   * Apply the blur filter to the data in the image model.
   */
  public void applyBlur();

  /**
   * Apply the sharpen filter to the data in the image model.
   */
  public void applySharpen();

  /**
   * Apply the grayscale color transformation to the data in the image model.
   */
  public void applyGrayscale();

  /**
   * Apply the sepia color transformation to the data in the image model.
   */
  public void applySepia();

  /**
    * Apply the dithering effect to the data in the image model.
    */
  public void applyDither();
  
  /**
   * Apply the mosaic effect to the data in the image model.
   * 
   * @param seeds the number of seeds to use in the mosaic
   * @throws IllegalArgumentException if the number of seeds is not positive
   */
  public void applyMosaic(int seeds) throws IllegalArgumentException;
  
  /**
   * Convert an image to a file and convert it from a 3D array of integer values.
   * The first dimension (rows) represents the height, the second dimension
   * (columns) represents the width, and the third represents the number of
   * channels in the image: the first value is the RED channel, the second value
   * is the GREEN channel, and the third is the BLUE channel. There is only ever 3
   * channels in our representation since we do not support transparent images.
   * 
   * @return the buffered image from the current image view
   */
  public BufferedImage convertToImageFile();

}
