package script;

import java.io.IOException;

/**
 * Represents a view of the image model. 
 * Prompt user to enter useful information for the controller.
 */
public class ImageView {
  private Appendable out;
  
  /**
   * Constructor for the Image view.
   * @param out an appendable output
   */
  public ImageView(Appendable out) {
    this.out = out;
  }
  
  /**
   * Displays the effect choices to the user.
   */
  public void showEffectOptions() throws IOException {
    this.out.append(String.format("Please enter the effect you want to apply, "
        + "blur, sharpen, grayscale, sepia, or dither: "));
  }

  /**
   * Displays the instruction for the user.
   */
  public void showInstruction() throws IOException {
    this.out.append(String.format("Please enter load with the file name, "
        + "and enter save with the new file name: "));
  }
  
  /**
   * Show a message to the user.
   * 
   * @param msg the message to show
   */
  public void showMessage(String msg) throws IOException {
    this.out.append(msg);
  }
}
