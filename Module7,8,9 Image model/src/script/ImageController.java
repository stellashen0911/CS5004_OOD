package script;

import images.ImageModel;
import java.io.IOException;
import java.util.Scanner;


/**
 * Represents a Controller for Image model.
 * Handle user input for action for the image, like load, save and 
 * other image effects to the image.
 */
public class ImageController {
  private Scanner in;
  private ImageView view;
  private ImageModel model;
  
  /**
   * Constructor for an ImageController with specified model and view.
   *
   * @param m a non-null Image model
   * @param v a non-null Image view 
   * @param in a string file with input instructions 
   */
  public ImageController(ImageModel m, ImageView v, Readable in) {
    this.model = m;
    this.view = v;
    this.in = new Scanner(in);
  }
  
  /**
   * Execute a the image effect instruction given an image Model. 
   * When the image is saved, the go method ends.
   * @throws IOException throw exception
   */
  public void go() throws IOException {
    boolean isOpen = false;
    //while the input file is not end
    while (in.hasNextLine()) {
      String effect = in.next();
      switch (effect) {
        case "blur":
          if (isOpen) {
            this.model.applyBlur();
          } else {
            this.view.showMessage("No Open file.");
          }
          break;
        case "sharpen":
          if (isOpen) {
            this.model.applySharpen();
          } else {
            this.view.showMessage("No Open file.");
          }
          break;
        case "grayscale":
          if (isOpen) {
            this.model.applyGrayscale();
          } else {
            this.view.showMessage("No Open file.");
          }
          break;
        case "sepia":
          if (isOpen) {
            this.model.applySepia();
          } else {
            this.view.showMessage("No Open file.");
          }
          break;
        case "dither":
          if (isOpen) {
            this.model.applyDither();
          } else {
            this.view.showMessage("No Open file.");
          }
          break;
        case "save":
          if (isOpen) {
            if (in.hasNextLine()) {
              String fileSaveName = in.next();
              this.model.saveImage(fileSaveName);
              this.view.showMessage("Save complete.");
            } else {
              this.view.showMessage("File Name missing or incorrect.");
            }
          } else {
            this.view.showMessage("No Open file.");
          }
          break;
        case "load":
          if (in.hasNextLine()) {
            String fileNameOpen = in.next();
            this.model.loadImage(fileNameOpen);
            this.view.showMessage("Load complete.");
            isOpen = true;
          } else {
            this.view.showMessage("File Name missing or incorrect.");
          }
          break;
        default:
          this.view.showMessage("Please enter a valid operation.");
          break;
      }
    }
  }
  
}
