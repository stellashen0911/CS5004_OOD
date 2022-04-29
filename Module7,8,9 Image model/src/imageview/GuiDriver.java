package imageview;

import images.ConcreteImageModel;
import images.ImageModel;

/**
 * Driver class is a class that test the model view controller for the GUI Controller.
 */
public class GuiDriver {
  
  /**
   * This is the main function of the GUI Driver Class.
   * 
   * @param args not used
   */
  public static void main(String[] args) {
    ImageModel model = new ConcreteImageModel();
    GuiView view = new GuiView();
    GuiController gui = new GuiController(model, view);
  }
}