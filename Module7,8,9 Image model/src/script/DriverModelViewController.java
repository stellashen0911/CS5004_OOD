package script;

import images.ConcreteImageModel;
import images.ImageModel;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Driver class is a class that test the model view controller for Image.
 */
public class DriverModelViewController {
  
  /**
   * This is the main function of the Driver Class.
   * 
   * @param args not used
   * @throws IOException throw exception
   */
  public static void main(String[] args) throws IOException {
    ImageModel model = new ConcreteImageModel();
    ImageView view = new ImageView(System.out);
    File file = new File("input.txt");
    Readable fileReader = new FileReader(file);
    ImageController controller = new ImageController(model, view, fileReader);
    controller.go();
  }
}