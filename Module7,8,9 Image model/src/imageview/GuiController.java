package imageview;

import images.ImageModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import script.ImageController;
import script.ImageView;

/**
 * Represents a Controller for GUI.
 * Handle user input for action for the image, like load, save and 
 * other image effects to the image.
 */
public class GuiController {
  private boolean isOpen = false;
  private String fileName;
  private int seed = 0;
  private boolean isFileNameEntered = false;
  private String saveFileName;
  private ImageModel m;
  private GuiView v;

  /**
   * Constructor for the GUI Controller to set up the UI.
   * 
   * @param model the image model for this GUI
   * @param view the GUI view for this GUI
   */
  public GuiController(ImageModel model, GuiView view) {
    //initialize the model and view components in the constructor
    m = model;
    v = view;
    
    //add action listener to the JComboBox
    v.getEffectsCombo().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String value = (String) v.getEffectsCombo().getSelectedItem();
        //to make sure that the image is open
        if (isOpen) {
          switch (value) {
            case "blur":
              m.applyBlur();
              ImageIcon imageBlur = new ImageIcon(m.convertToImageFile());
              v.getImageLabel().removeAll();
              v.getImageLabel().setIcon(imageBlur);
              break;
            case "sharpen":
              m.applySharpen();
              ImageIcon imageSharpen = new ImageIcon(m.convertToImageFile());
              v.getImageLabel().removeAll();
              v.getImageLabel().setIcon(imageSharpen);
              break;
            case "grayscale":
              m.applyGrayscale();
              ImageIcon imageGrayscale = new ImageIcon(m.convertToImageFile());
              v.getImageLabel().removeAll();
              v.getImageLabel().setIcon(imageGrayscale);
              break;
            case "sepia":
              m.applySepia();
              ImageIcon imageSepia = new ImageIcon(m.convertToImageFile());
              v.getImageLabel().removeAll();
              v.getImageLabel().setIcon(imageSepia);
              break;
            case "dither":
              m.applyDither();
              ImageIcon imageDither = new ImageIcon(m.convertToImageFile());
              v.getImageLabel().removeAll();
              v.getImageLabel().setIcon(imageDither);
              break;
            case "mosaic":
              if (seed != 0) {
                m.applyMosaic(seed);
                ImageIcon imageMosaic = new ImageIcon(m.convertToImageFile());
                v.getImageLabel().removeAll();
                v.getImageLabel().setIcon(imageMosaic);
              }
              break;
            default:
              break;
          }
        }
      }
    });
    
    //set the action listener for the text field
    v.getSeedTextField().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String text = v.getSeedTextField().getText();
        seed = Integer.parseInt(text);
      }
    });
    
    //set the action listener for the text field
    v.getSaveFileName().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String text = v.getSaveFileName().getText();
        saveFileName = text;
        isFileNameEntered = true;
      }
    });
    
    //set the action listener for the menu item when open
    v.getMenuOpen().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filtering files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int res = file.showSaveDialog(null);
        //if the user clicks on save in Jfilechooser
        if (res == JFileChooser.APPROVE_OPTION) {
          File selFile = file.getSelectedFile();
          String path = selFile.getAbsolutePath();
          ImageIcon image = new ImageIcon(path);
          fileName = selFile.getName();
          m.loadImage(fileName);
          v.getImageLabel().setIcon(image);
          isOpen = true;
        }
      }
    });
    
    //set the action listener for the menu item for saving the file
    v.getMenuSave().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (isFileNameEntered == true) {
          m.saveImage(saveFileName);
        }
      }
    });
    
    //set the scriptBtn action to open a script file
    v.getSciptBtn().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filtering files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        file.addChoosableFileFilter(filter);
        int res = file.showSaveDialog(null);
        //if the user clicks on save in Jfilechooser
        if (res == JFileChooser.APPROVE_OPTION) {
          File selFile = file.getSelectedFile();
          ImageView view = new ImageView(System.out);
          try {
            Readable fileReader;
            fileReader = new FileReader(selFile);
            ImageController controller = new ImageController(m, view, fileReader);
            controller.go();
          } catch (IOException e1) {
            System.out.println(e1);
          }
        }
      }
    });
    
    //set the action listener for button to open a file
    v.getOpenBtn().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("here");
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filtering files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int res = file.showSaveDialog(null);
        //if the user clicks on save in Jfilechooser
        if (res == JFileChooser.APPROVE_OPTION) {
          File selFile = file.getSelectedFile();
          String path = selFile.getAbsolutePath();
          ImageIcon image = new ImageIcon(path);
          fileName = selFile.getName();
          m.loadImage(fileName);
          v.getImageLabel().setIcon(image);
          isOpen = true;
        }
      }
    });
  
  }
  
  /**
  * This is a helper method to set the file name entered to be true.
  * For the Gui Controller Test
  */
  public void guiTestHelperSetFileNameEntered() {
    this.isFileNameEntered = true;
    return;
  }
  
}
