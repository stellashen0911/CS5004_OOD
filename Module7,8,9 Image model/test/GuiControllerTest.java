import static org.junit.Assert.assertEquals;

import images.ImageModel;
import imageview.GuiController;
import imageview.GuiView;
import org.junit.Test;

/**
 * This is a test file for the GUI controller.
 */
public class GuiControllerTest {

  /**
   * testing the open image button function of the controller.
   */
  @Test
  public void testOpenImage() {
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockGuiModel(log);
    GuiView v = new GuiView();
    GuiController gui = new GuiController(m, v);
    v.getOpenBtn().doClick();
    //choose a picture file named snow-mountain.png
    assertEquals("Load Success! snow-mountain.png", log.toString());
  }
  
  /**
   * testing the open button from the menu function of the controller.
   */
  @Test
  public void testMenuOpen() {
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockGuiModel(log);
    GuiView v = new GuiView();
    GuiController gui = new GuiController(m, v);
    v.getMenuOpen().doClick();
    //choose a picture file named snow-mountain.png
    assertEquals("Load Success! snow-mountain.png", log.toString());
  }
  
  /**
   * testing the combo box blur of the controller.
   */
  @Test
  public void testEffectBlur() {
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockGuiModel(log);
    GuiView v = new GuiView();
    GuiController gui = new GuiController(m, v);
    //first we have to open a file to add effect on it
    v.getMenuOpen().doClick();
    //make sure that we successfully opened the file
    assertEquals("Load Success! snow-mountain.png", log.toString());
    v.getEffectsCombo().setSelectedItem("blur");
    String value = v.getEffectsCombo().getSelectedItem().toString();
    assertEquals("blur", value);
    assertEquals("Load Success! snow-mountain.pngBlur Success!", log.toString());
  }
  
  /**
   * testing the combo box sharpen of the controller.
   */
  @Test
  public void testEffectSharpen() {
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockGuiModel(log);
    GuiView v = new GuiView();
    GuiController gui = new GuiController(m, v);
    //first we have to open a file to add effect on it
    v.getMenuOpen().doClick();
    //make sure that we successfully opened the file
    assertEquals("Load Success! snow-mountain.png", log.toString());
    v.getEffectsCombo().setSelectedItem("sharpen");
    String value = v.getEffectsCombo().getSelectedItem().toString();
    assertEquals("sharpen", value);
    assertEquals("Load Success! snow-mountain.pngsharpen Success!", log.toString());
  }
  
  /**
   * testing the combo box "grayscale" of the controller.
   */
  @Test
  public void testEffectGrayscale() {
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockGuiModel(log);
    GuiView v = new GuiView();
    GuiController gui = new GuiController(m, v);
    //first we have to open a file to add effect on it
    v.getMenuOpen().doClick();
    //make sure that we successfully opened the file
    assertEquals("Load Success! snow-mountain.png", log.toString());
    v.getEffectsCombo().setSelectedItem("grayscale");
    String value = v.getEffectsCombo().getSelectedItem().toString();
    assertEquals("grayscale", value);
    assertEquals("Load Success! snow-mountain.pngGrayscale Success!", log.toString());
  }
  
  /**
   * testing the combo box sepia of the controller.
   */
  @Test
  public void testEffectSepia() {
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockGuiModel(log);
    GuiView v = new GuiView();
    GuiController gui = new GuiController(m, v);
    //first we have to open a file to add effect on it
    v.getMenuOpen().doClick();
    //make sure that we successfully opened the file
    assertEquals("Load Success! snow-mountain.png", log.toString());
    v.getEffectsCombo().setSelectedItem("sepia");
    String value = v.getEffectsCombo().getSelectedItem().toString();
    assertEquals("sepia", value);
    assertEquals("Load Success! snow-mountain.pngSepia Success!", log.toString());
  }
  
  /**
   * testing the combo box dither of the controller.
   */
  @Test
  public void testEffectDither() {
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockGuiModel(log);
    GuiView v = new GuiView();
    GuiController gui = new GuiController(m, v);
    //first we have to open a file to add effect on it
    v.getMenuOpen().doClick();
    //make sure that we successfully opened the file
    assertEquals("Load Success! snow-mountain.png", log.toString());
    v.getEffectsCombo().setSelectedItem("dither");
    String value = v.getEffectsCombo().getSelectedItem().toString();
    assertEquals("dither", value);
    assertEquals("Load Success! snow-mountain.pngapplyDither Success!", log.toString());
  }
  
  /**
   * testing the combo box mosaic of the controller.
   */
  @Test
  public void testEffectMosaic() {
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockGuiModel(log);
    GuiView v = new GuiView();
    GuiController gui = new GuiController(m, v);
    //first we have to open a file to add effect on it
    v.getMenuOpen().doClick();
    //make sure that we successfully opened the file
    assertEquals("Load Success! snow-mountain.png", log.toString());
    v.getEffectsCombo().setSelectedItem("mosaic");
    String value = v.getEffectsCombo().getSelectedItem().toString();
    assertEquals("mosaic", value);
    assertEquals("Load Success! snow-mountain.pngapplyMosaic Success!", log.toString());
  }
  
  /**
   * testing the getSeedTextField function of the controller.
   */
  @Test
  public void testSeedTextField() {
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockGuiModel(log);
    GuiView v = new GuiView();
    GuiController gui = new GuiController(m, v);
    //first we have to open a file to add effect on it
    v.getMenuOpen().doClick();
    //make sure that we successfully opened the file
    assertEquals("Load Success! snow-mountain.png", log.toString());
    v.getSeedTextField().setText("800");
    String value = v.getSeedTextField().getText();
    assertEquals("800", value);
  }
  
  /**
   * testing the getSaveFileName function of the controller.
   */
  @Test
  public void testSaveFileText() {
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockGuiModel(log);
    GuiView v = new GuiView();
    GuiController gui = new GuiController(m, v);
    //first we have to open a file to add effect on it
    v.getMenuOpen().doClick();
    //make sure that we successfully opened the file
    assertEquals("Load Success! snow-mountain.png", log.toString());
    v.getSaveFileName().setText("test.png");
    String value = v.getSeedTextField().getText();
    assertEquals("test.png", value);
  }

  /**
  * testing the open button from the menu function of the controller.
  */
  @Test
  public void testMenuSave() {
    StringBuilder log = new StringBuilder(); //log for mock model
    ImageModel m = new MockGuiModel(log);
    GuiView v = new GuiView();
    GuiController gui = new GuiController(m, v);
    //first we have to open a file to add effect on it
    v.getMenuOpen().doClick();
    //set the save file name before save the file
    v.getSaveFileName().setText("test.png");
    gui.guiTestHelperSetFileNameEntered();
    v.getMenuSave().doClick();
    //choose a picture file named snow-mountain.png
    assertEquals("Load Success! snow-mountain.pngSave Success! test.png", log.toString());
  }
  
}
