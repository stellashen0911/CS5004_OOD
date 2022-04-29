package imageview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 * Represents a view of the GUI model. 
 * Prompt user to enter useful information for the controller.
 * Present user with a GUI window.
 */
public class GuiView {
  private JFrame frame;
  private JPanel choosePanel;
  private JPanel imagePanel;
  private JPanel savePanel;
  private JPanel effectPanel;
  private JPanel batchScriptPanel;
  private JPanel saveEnterPanel;
  private JLabel label;
  private JLabel mosaicLabel;
  private JLabel labelTextMessage;
  private JLabel scripText;
  private JMenu menu;
  private JMenuBar menuBar;
  private JMenuItem menuItemOpen;
  private JMenuItem menuItemSave;
  private JComboBox<String> choices;
  private JTextField seedText;
  private JTextField saveFileNameText;
  private JComponent effectComponent;
  private JComponent saveComponent;
  private JButton scriptBtn;
  private JButton btn;
  
  /**
   * Constructor for the GUI view.
   */
  public GuiView() {
    effectComponent = new JPanel(new BorderLayout());
    saveComponent = new JPanel(new BorderLayout());
    effectPanel = new JPanel(new FlowLayout());
    batchScriptPanel = new JPanel(new FlowLayout());
    saveEnterPanel = new JPanel(new FlowLayout());
    scripText = new JLabel("Please click here to choose your script file, " 
        + "After enter the file name you want to save, " 
        + "click the 'Save' button from the Menu");
    scriptBtn = new JButton("Choose Script");
    frame = new JFrame();
    choosePanel = new JPanel(new FlowLayout());
    imagePanel = new JPanel(new FlowLayout());
    savePanel = new JPanel(new FlowLayout());
    btn = new JButton("Choose Image");
    label = new JLabel();
    labelTextMessage = new JLabel("<- Choose a image file to load,"
        + " and then choose a effect you want! ->");
    menu = new JMenu("Menu");
    menuBar = new JMenuBar();
    menuItemOpen = new JMenuItem("Open image file",
        KeyEvent.VK_T);
    menuItemSave = new JMenuItem("Save image file",
        KeyEvent.VK_T);
    seedText = new JTextField(10);
    saveFileNameText = new JTextField(40);
    mosaicLabel = new JLabel("Enter the seeds number for mosaic effect:");
    
    //set up the menu and menuBar
    menu.setMnemonic(KeyEvent.VK_A);
    menu.add(menuItemOpen);
    menu.add(menuItemSave);
    menuBar.add(menu);
    
    //set up the choosePanel
    choosePanel.setBorder(BorderFactory.createBevelBorder(6));
    choosePanel.setLayout(new FlowLayout());
    
    //set up the combo box choices
    String[] imageEffect = { "blur", "sharpen", "grayscale", "sepia", "dither", "mosaic" };
    choices = new JComboBox<String>(imageEffect);
    choices.setSelectedIndex(-1);
    
    //add all components into the panel
    choosePanel.add(btn);
    choosePanel.add(labelTextMessage);
    choosePanel.add(new JSeparator(), BorderLayout.PAGE_END);
    effectPanel.add(choices);
    effectPanel.add(mosaicLabel);
    effectPanel.add(seedText);
    effectPanel.add(new JSeparator(), BorderLayout.PAGE_END);
    batchScriptPanel.add(scripText);
    batchScriptPanel.add(scriptBtn);
    effectComponent.add(choosePanel, BorderLayout.PAGE_START);
    effectComponent.add(effectPanel, BorderLayout.CENTER);
    effectComponent.add(batchScriptPanel, BorderLayout.PAGE_END);
    imagePanel.add(label);
    savePanel.add(scripText);
    saveEnterPanel.add(saveFileNameText);
    saveComponent.add(savePanel, BorderLayout.PAGE_START);
    saveComponent.add(saveEnterPanel, BorderLayout.CENTER);
    
    //add all components into the panel
    frame.add(effectComponent, BorderLayout.PAGE_START);
    frame.add(imagePanel, BorderLayout.CENTER);
    frame.add(saveComponent, BorderLayout.PAGE_END);
    frame.setJMenuBar(menuBar);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Image Effect View Program");
    frame.setPreferredSize(new Dimension(1200, 700));
    
    frame.pack();
    frame.setVisible(true);
  }
  
  /**
   * Getter for the seedTextField.
   * 
   * @return seedText
   */
  public JTextField getSeedTextField() {
    return this.seedText;
  }

  /**
   * Getter for the getEffectsCombo.
   * 
   * @return choices
   */
  public JComboBox<String> getEffectsCombo() {
    return this.choices;
  }
  
  /**
   * Getter for the getImageLabel.
   * 
   * @return label
   */
  public JLabel getImageLabel() {
    return this.label;
  }
  
  /**
   * Getter for the getSaveFileName.
   * 
   * @return saveFileNameText
   */
  public JTextField getSaveFileName() {
    return this.saveFileNameText;
  }
  
  /**
   * Getter for the getMenuOpen.
   * 
   * @return menuItemOpen
   */
  public JMenuItem getMenuOpen() {
    return this.menuItemOpen;
  }
  
  /**
   * Getter for the getMenuSave.
   * 
   * @return menuItemSave
   */
  public JMenuItem getMenuSave() {
    return this.menuItemSave;
  }
  
  /**
   * Getter for the getSciptBtn.
   * 
   * @return scriptBtn
   */
  public JButton getSciptBtn() {
    return this.scriptBtn;
  }
  
  /**
   * Getter for the getOpenBtn.
   * 
   * @return btn
   */
  public JButton getOpenBtn() {
    return this.btn;
  }
}
