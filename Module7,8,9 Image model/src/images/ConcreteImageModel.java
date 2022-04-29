package images;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * ConcreteImageModel class that has methods to load and save image.
 * It can also apply many different filters to the image.
 */
public class ConcreteImageModel implements ImageModel {

  private int[][][] imageArray;
  private int height;
  private int width;
  
  /**
   * Constructs a ConcreteImageModel with no parameters.
   * Set the imageArray to be null at the beginning.
   */
  public ConcreteImageModel() {
    imageArray = null;
    height = 0;
    width = 0;
  }
  
  @Override
  public void loadImage(String filename) throws IllegalArgumentException {
    if (filename == null || "".equals(filename)) {
      throw new IllegalArgumentException("Invalid filename provided for reading the image file.");
    }
    try {
      imageArray = ImageUtilities.readImage(filename);
      height = ImageUtilities.getHeight(filename);
      width = ImageUtilities.getWidth(filename);
    } catch (IOException ex) {
      throw new IllegalArgumentException("Something went wrong getting"
          + " the data from the image file");
    }
  }

  @Override
  public void saveImage(String filename) throws IllegalArgumentException {
    /* if the filename is incorrect, throw exception */
    if (filename == null || "".equals(filename)) {
      throw new IllegalArgumentException("Invalid filename provided");
    }
    /* if the image array does not exist, we cannot save the image*/
    if (this.imageArray == null) {
      throw new IllegalArgumentException("Invalid image data provided");
    } 
    ImageUtilities.writeImage(imageArray, filename);
    System.out.println("model save complete");
  }

  /**
   * Helper function to decide whether a pixel is valid or not.
   * Row should be in the range [0, height]
   * Col should be in the range [0, width]
   * @param row the row position
   * @param col the col position
   * @return true or false of the pixel validity
   */
  private boolean isValidPixel(int row, int col) {
    if (row < 0 || col < 0) {
      return false;
    }
    if (row >= this.height || col >= this.width) {
      return false;
    }
    return true;
  }
  
  @Override
  public void applyBlur() {
    /* setting up the bluf filter kernel*/
    double[][] blurFilter = {
        {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
        {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
        {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}
    };
    /* 8 different directions of the center pixel*/                       
    int[][] direction = {
        {0, 0, 0, 1, 1, 1, -1, -1, -1},
        {0, 1, -1, 1, -1, 0, 1, 0, -1}
    };
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        for (int k = 0; k < 3; k++) {
          //center is currently at i, j
          double sum = 0;
          //add 9 directions pixel with its corresponding kernel value
          for (int l = 0; l < direction[0].length; l++) {
            int rowMove = direction[0][l];
            int colMove = direction[1][l];
            if (isValidPixel(i + rowMove, j + colMove)) {
              sum += imageArray[i + rowMove][j + colMove][k] 
                * blurFilter[1 + rowMove][1 + colMove];
            }
          }
          if (sum > 255.0) {
            sum = 255.0;
          } else if (sum < 0.0) {
            sum = 0.0;
          }
          imageArray[i][j][k] = (int) sum;
        }
      }
    }
    
  }

  @Override
  public void applySharpen() {
    //set up the sharpen filter data
    double[][] sharpenFilter = new double[5][5];
    for (int i = 0; i < 5; i++) {
      sharpenFilter[i][0] = - 1.0 / 8.0;
      sharpenFilter[i][4] = - 1.0 / 8.0;
      sharpenFilter[0][i] = - 1.0 / 8.0;
      sharpenFilter[4][i] = - 1.0 / 8.0;
    }
    for (int i = 1; i < 4; i++) {
      sharpenFilter[1][i] = 1.0 / 4.0;
      sharpenFilter[i][1] = 1.0 / 4.0;
      sharpenFilter[i][3] = 1.0 / 4.0;
      sharpenFilter[3][i] = 1.0 / 4.0;
    }
    sharpenFilter[2][2] = 1.0;
    int[][] direction = {
        {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 
        -1, -1, -1, -1, -1, 2, 2, 2, 2, 2,
        -2, -2, -2, -2, -2},
        {0, 1, -1, 2, -2, 0, 1, -1, 2, -2,
        0, 1, -1, 2, -2, 0, 1, -1, 2, -2,
        0, 1, -1, 2, -2}
    };
    
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        for (int k = 0; k < 3; k++) {
          //center is currently at i, j
          double sum = 0;
          for (int l = 0; l < direction[0].length; l++) {
            int rowMove = direction[0][l]; 
            int colMove = direction[1][l];
            if (isValidPixel(i + rowMove, j + colMove)) {
              sum += imageArray[i + rowMove][j + colMove][k]
                    * sharpenFilter[2 + rowMove][2 + colMove];
            }
          }
          if (sum > 255.0) {
            sum = 255.0;
          } else if (sum < 0.0) {
            sum = 0.0;
          }
          //reset the channel value of this pixel
          imageArray[i][j][k] = (int) sum;
        }
      }
    }
  }

  @Override
  public void applyGrayscale() {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int grayValue = (int) (0.2126 * imageArray[i][j][0]
                      + 0.7152 * imageArray[i][j][1]
                      + 0.0722 * imageArray[i][j][2]); 
        if (grayValue > 255) {
          grayValue = 255;
        } else if (grayValue < 0) {
          grayValue = 0;
        }
        imageArray[i][j][0] = grayValue;
        imageArray[i][j][1] = grayValue;
        imageArray[i][j][2] = grayValue;
      }
    }
  }

  @Override
  public void applySepia() {
    double[][] sepiaFilter = {
        {0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}
    };
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        int newR = (int) (imageArray[i][j][0] * sepiaFilter[0][0]
            + imageArray[i][j][1] * sepiaFilter[0][1]
            + imageArray[i][j][2] * sepiaFilter[0][2]);
        if (newR > 255) {
          newR = 255;
        } else if (newR < 0) {
          newR = 0;
        }
        int newG = (int) (imageArray[i][j][0] * sepiaFilter[1][0]
            + imageArray[i][j][1] * sepiaFilter[1][1]
            + imageArray[i][j][2] * sepiaFilter[1][2]);
        if (newG > 255) {
          newG = 255;
        } else if (newG < 0) {
          newG = 0;
        }
        int newB = (int) (imageArray[i][j][0] * sepiaFilter[2][0]
            + imageArray[i][j][1] * sepiaFilter[2][1]
            + imageArray[i][j][2] * sepiaFilter[2][2]);
        if (newB > 255) {
          newB = 255;
        } else if (newB < 0) {
          newB = 0;
        }
        imageArray[i][j][0] = newR;
        imageArray[i][j][1] = newG;
        imageArray[i][j][2] = newB;
      }
    }
  }

  @Override
  public void applyDither() {
    this.applyGrayscale();
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        for (int k = 0; k < 3; k++) {
          int oldColor = imageArray[i][j][k];
          int newColor = 0;
          if (oldColor - 128 >= 0) {
            newColor = 255;
          }
          //if the old color is closer to 255, we set it to 255
          //else set it to 0
          int error = oldColor - newColor;
          imageArray[i][j][k] = newColor;
          
          if (isValidPixel(i, j + 1)) {
            double addOnRight = error * (7 / 16); 
            imageArray[i][j + 1][k] = (int) (imageArray[i][j + 1][k] + addOnRight);
          }
          
          if (isValidPixel(i + 1, j - 1)) {
            double addOnNextLeft = error * (3 / 16); 
            imageArray[i + 1][j - 1][k] = (int) (imageArray[i + 1][j - 1][k] + addOnNextLeft);
          }
          
          if (isValidPixel(i + 1, j)) {
            double addOnBelow = error * (5 / 16); 
            imageArray[i + 1][j][k] = (int) (imageArray[i + 1][j][k] + addOnBelow);
          }
          
          if (isValidPixel(i + 1, j + 1)) {
            double addOnNextRight = error * (1 / 16); 
            imageArray[i + 1][j + 1][k] = (int) (imageArray[i + 1][j + 1][k] + addOnNextRight);
          }
        }
      }
    }  
  }
  
  /**
   * This is a helper function for the applyMosaic function to find the nearest seed.
   * from the map of existing seeds.
   * 
   * @param seedsMap is the existing seeds map, with key : row, value : col
   * @param currRow is the row of the current position
   * @param currCol is the col of the current position
   * @return the Row position of the nearest seed for the current position
   * @throws IllegalArgumentException if the filename provided does not contain an
   *                                  image.
   */
  public int nearestSeedRow(Map<Integer, Integer> seedsMap, int currRow, int currCol) {
    int min = Integer.MAX_VALUE;
    int resultSeedRow = currRow;
    
    for (Map.Entry<Integer, Integer> seed : seedsMap.entrySet()) {
      int seedRow = seed.getKey();
      int seedCol = seed.getValue();
      double distanceBefore = (currRow - seedRow) * (currRow - seedRow) 
                   + (currCol - seedCol) * (currCol - seedCol);
      int distance = (int) Math.sqrt(distanceBefore);
      if (distance < min) {
        min = distance;
        resultSeedRow = seedRow;
      }
    }
    return resultSeedRow;
  }
  
  @Override
  public void applyMosaic(int seeds) throws IllegalArgumentException {
    if (seeds < 0) {
      throw new IllegalArgumentException("seeds number must be positive integer!");
    }
    //use a map to store row and col position of the seeds
    Map<Integer, Integer> seedsMap = new HashMap<Integer, Integer>();
    
    int height = this.height;
    int width = this.width;
    Random rand = new Random();
    
    for (int i = 0; i < seeds; i++) {
      // Generate random row and col position for the seeds
      int randRow = rand.nextInt(height);
      int randCol = rand.nextInt(width);
      seedsMap.put(randRow, randCol);
    }
    
    //map every pixel in the image to a given seed
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int seedRow = nearestSeedRow(seedsMap, i, j);
        int seedCol = seedsMap.get(seedRow);
        //set the current pixel to have the same color as its seed
        for (int k = 0; k < 3; k++) {
          imageArray[i][j][k] = imageArray[seedRow][seedCol][k];
        }
      }
    }
  }
  
  @Override
  public BufferedImage convertToImageFile() {
    return ImageUtilities.convertImage(imageArray);
  }
}
