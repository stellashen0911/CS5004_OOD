import static org.junit.Assert.assertEquals;

import images.ImageModel;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;
import script.ImageController;
import script.ImageView;

/**
 * This is a test file for the image controller.
 */
public class ImageControllerTest {

  /**
   * testing the load function of the controller.
   */
  @Test
  public void testGoLoad() throws IOException {
    Reader input = new StringReader("load test.png");
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockImageModel(log);
    ImageView v = new ImageView(out);
    ImageController controller = new ImageController(m, v, input);
    controller.go();
    assertEquals("Load Success! test.png", log.toString()); //inputs reached the model correctly
    assertEquals("Load complete.", out.toString());
  }
  
  /**
   * testing the load function of the controller with typo.
   */
  @Test
  public void testGoLoadError() throws IOException {
    Reader input = new StringReader("loadddd test.png");
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockImageModel(log);
    ImageView v = new ImageView(out);
    ImageController controller = new ImageController(m, v, input);
    controller.go();
    assertEquals("", log.toString()); //inputs does not reached the model
    assertEquals("Please enter a valid operation.Please enter a valid operation.", out.toString());
  }
  
  /**
   * testing the load function of the controller without file name.
   */
  @Test
  public void testGoLoadErrorWithoutName() throws IOException {
    Reader input = new StringReader("load");
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockImageModel(log);
    ImageView v = new ImageView(out);
    ImageController controller = new ImageController(m, v, input);
    controller.go();
    assertEquals("", log.toString()); //inputs does not reached the model
    assertEquals("File Name missing or incorrect.", out.toString());
  }

  /**
   * testing the blur function without open the file.
   */
  @Test
  public void testGoBlurError() throws IOException {
    Reader input = new StringReader("blur");
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockImageModel(log);
    ImageView v = new ImageView(out);
    ImageController controller = new ImageController(m, v, input);
    controller.go();
    assertEquals("", log.toString()); //inputs does not reached the model
    assertEquals("No Open file.", out.toString());
  }
  
  /**
   * testing the blur function.
   */
  @Test
  public void testGoBlur() throws IOException {
    Reader input = new StringReader("load test.png blur");
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockImageModel(log);
    ImageView v = new ImageView(out);
    ImageController controller = new ImageController(m, v, input);
    controller.go();
    assertEquals("Load Success! test.pngBlur Success!", log.toString()); 
    //inputs does not reached the model
  }
  
  /**
   * testing the Sharpen function.
   */
  @Test
  public void testGoSharpen() throws IOException {
    Reader input = new StringReader("load test.png sharpen");
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockImageModel(log);
    ImageView v = new ImageView(out);
    ImageController controller = new ImageController(m, v, input);
    controller.go();
    assertEquals("Load Success! test.pngSharpen Success!", log.toString());
  }
  
  /**
   * testing the Grayscale function.
   */
  @Test
  public void testGoGrayscale() throws IOException {
    Reader input = new StringReader("load test.png grayscale");
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockImageModel(log);
    ImageView v = new ImageView(out);
    ImageController controller = new ImageController(m, v, input);
    controller.go();
    assertEquals("Load Success! test.pngGrayscale Success!", log.toString());
  }
  
  /**
   * testing the Sepia function.
   */
  @Test
  public void testGoSepia() throws IOException {
    Reader input = new StringReader("load test.png sepia");
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockImageModel(log);
    ImageView v = new ImageView(out);
    ImageController controller = new ImageController(m, v, input);
    controller.go();
    assertEquals("Load Success! test.pngSepia Success!", log.toString());
  }
  
  /**
   * testing the Dither function.
   */
  @Test
  public void testGoDither() throws IOException {
    Reader input = new StringReader("load test.png dither");
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockImageModel(log);
    ImageView v = new ImageView(out);
    ImageController controller = new ImageController(m, v, input);
    controller.go();
    assertEquals("Load Success! test.pngDither Success!", log.toString());
  }
  
  
  /**
   * testing the save function without open the file.
   */
  @Test
  public void testGoSaveError() throws IOException {
    Reader input = new StringReader("save test-blur.png");
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockImageModel(log);
    ImageView v = new ImageView(out);
    ImageController controller = new ImageController(m, v, input);
    controller.go();
    assertEquals("", log.toString()); //inputs does not reached the model
    assertEquals("No Open file.Please enter a valid operation.", out.toString());
  }
  
  /**
   * testing the save function without saving file name.
   */
  @Test
  public void testGoSaveWithoutName() throws IOException {
    Reader input = new StringReader("load test.png save");
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockImageModel(log);
    ImageView v = new ImageView(out);
    ImageController controller = new ImageController(m, v, input);
    controller.go();
    assertEquals("Load Success! test.png", log.toString());
    //inputs does not reached the model
    assertEquals("Load complete.File Name missing or incorrect.", out.toString());
  }
  
  /**
   * testing the save function.
   */
  @Test
  public void testGoSave() throws IOException {
    Reader input = new StringReader("load test.png save test-save.png");
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockImageModel(log);
    ImageView v = new ImageView(out);
    ImageController controller = new ImageController(m, v, input);
    controller.go();
    assertEquals("Load Success! test.pngSave Success! test-save.png", log.toString());
    //inputs does not reached the model
    assertEquals("Load complete.Save complete.", out.toString());
  }
  
}
