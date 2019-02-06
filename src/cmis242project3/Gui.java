/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmis242project3;

/**
 * File: Gui.java
 * Date: 4/19/2019
 * Author: Dillan Cobb
 * Purpose: Create the gui and display it to the user. Allows the user to select
 * which type of calculation they want to use and commits the calculation, while
 * displaying the results. Finally, on close of the application a file is saved
 * that shows results from 0-10 n-th of the calculation.
 */

// Imports
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Main class displays gui
public class Gui {
    public static void main(String[] args) {
        GuiFrame app = new GuiFrame();
        app.display();
    }
}

// Class for creating the window frame for the gui
class GuiFrame extends JFrame {
    // Static variables for the frame of the gui
    static final int WIDTH = 300, HEIGHT = 250;
    static final String FRAMETITLE = "Project 3";
    
    // Default constructor
    public GuiFrame() {
        super(FRAMETITLE);
        setFrame(WIDTH, HEIGHT);
        add(new GuiPanel());
        // handles the creating of the file on close of the application
        addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent e) {
               BufferedWriter outputStream = null;
               int resultIterative;
               int resultRecursive;
               int effIterative;
               int effRecursive;
               
               try {
                   outputStream = new BufferedWriter(new FileWriter("ClosingFil"
                           + "e.csv"));
                   // for loop that prints all the data into the file line
                   // by line
                   for(int i = 0; i < 11; i++) {
                       resultIterative = Sequence.computeIterative(i);
                       effIterative = Sequence.getEfficiency();
                       resultRecursive = Sequence.computeRecursive(i, i, 0, 0);
                       effRecursive = Sequence.getEfficiency();
                       
                       outputStream.write("Value of N: " + i + ",Iterative Resu"
                               + "lt: " + resultIterative + ",Iterative Efficie"
                               + "ncy: " + effIterative + ",Recursive Result: " 
                               + resultRecursive + ",Recursive Efficiency: " 
                               + effRecursive + ",\n");
                   }
               }
               catch (IOException io) {
                   System.out.println("File IO Exception: " + io.getMessage());
               }
               finally {
                   try {
                       if (outputStream != null)
                            outputStream.close();
                   }
                   catch (IOException io) {
                        System.out.println("File IO Exception: " + io.getMessage());
                   }
               }
           } 
        });
    }
    
    // display method shows the frame on the screen
    public void display() {
        setVisible(true);
    }
    
    // setFrame method allows for adjusting the frame and centeralizing its 
    //location on the screen
    public void setFrame(int width, int height) {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

// Class for creating the gui and its functions
class GuiPanel extends JPanel implements ActionListener {
    // All the labels for the gui
    private JLabel nLbl = new JLabel("Enter n:");
    private JLabel resultLbl = new JLabel("Result:");
    private JLabel efficiencyLbl = new JLabel("Efficency:");
    
    // All the titled boarders for the gui
    private TitledBorder calcBorder = new TitledBorder("Calculation");
    private TitledBorder resultBorder = new TitledBorder("Results");
    
    // All the radio buttons for the gui
    private JRadioButton iterativeRadioBtn = new JRadioButton("Iterative", 
            true);
    private JRadioButton recursiveRadioBtn = new JRadioButton("Recursive");
    
    // All the text fields for the gui
    private JTextField nTxtField = new JTextField();
    private JTextField resultTxtField = new JTextField();
    private JTextField efficiencyTxtField = new JTextField();
    
    // All the buttons for the gui
    private JButton computeBtn = new JButton("Compute");
    
    // Action listener used for the computeBtn to get the results and display
    // them in the correct text boxes
    public void actionPerformed(ActionEvent e) {
        int nNum = getN();
        int resultInt = 0;
        int effInt = 0;

        if (iterativeRadioBtn.isSelected()) {
            resultInt = Sequence.computeIterative(nNum);
            effInt = Sequence.getEfficiency();
        }
        else if (recursiveRadioBtn.isSelected()) {
            resultInt = Sequence.computeRecursive(nNum, nNum, 0, 0);
            effInt = Sequence.getEfficiency();
        }
        
        resultTxtField.setText(Integer.toString(resultInt));
        efficiencyTxtField.setText(Integer.toString(effInt));
    }
    
    public GuiPanel() {
        // All the panels that will be used in the gui
        JPanel mainPanel = new JPanel();
        JPanel calcPanel = new JPanel();
        JPanel resultPanel = new JPanel();
        
        // Set up each panel and add the objects into it
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        calcPanel.setLayout(new GridLayout(3, 2, 5, 5));
        calcPanel.setBorder(calcBorder);
        calcPanel.add(iterativeRadioBtn);
        calcPanel.add(recursiveRadioBtn);
        calcPanel.add(nLbl);
        calcPanel.add(nTxtField);
        calcPanel.add(computeBtn);
        
        resultPanel.setLayout(new GridLayout(2, 2, 5, 5));
        resultPanel.setBorder(resultBorder);
        resultPanel.add(resultLbl);
        resultPanel.add(resultTxtField);
        resultPanel.add(efficiencyLbl);
        resultPanel.add(efficiencyTxtField);
        
        // Add the panels to the frame
        mainPanel.add(calcPanel);
        mainPanel.add(resultPanel);
        add(mainPanel);
        
        // Group the radio buttons
        ButtonGroup radioBtnGroup = new ButtonGroup();
        radioBtnGroup.add(iterativeRadioBtn);
        radioBtnGroup.add(recursiveRadioBtn);
        
        // ActionListener for the compute button
        computeBtn.addActionListener(this);
    }
    
    // getN method that just turns the number in the text field into a int
    int getN() {
        int number = Integer.parseInt(nTxtField.getText());
        return number;
    }
}
