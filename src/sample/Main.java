/*Programmer:Austin Parker   Date: Sept. 17, 2020
 * Assignment: PE 17.13
 * Purpose: Allows user to choose file on computer then
 * split file into smaller files*/

package sample;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {
    /*text field to GET file name*/
    private JTextField jtxtfieldInputFile = new JTextField(20);
    /*creates txt field to GET number of files*/
    private JTextField jtxtfieldNumberOfFiles = new JTextField(2);
    /*create button to browse files*/
    private JButton jButtonBrowse = new JButton("Browse");
    /*create button for splitting*/
    private JButton jButtonStart = new JButton("Start");

    /*defines construtor to design panel*/
    public Main() {
        /*first panel*/
        JPanel panel1 = new JPanel(new BorderLayout());
        /*creates label box and adds to original panel*/
        panel1.add(new JLabel("Enter or choose file to split:"), BorderLayout.WEST);
        /*add text field into original panel*/
        panel1.add(jtxtfieldInputFile, BorderLayout.CENTER);
        /*add Browse button*/
        panel1.add(jButtonBrowse, BorderLayout.EAST);
        /*creates panel and adds to label and txt box*/
        JPanel panel2 = new JPanel(new BorderLayout());
        /*creates label and add to second panel*/
        panel2.add(new JLabel("Specify number of smaller files to create:"), BorderLayout.WEST);
        /*adds text field into second panel*/
        panel2.add(jtxtfieldNumberOfFiles, BorderLayout.CENTER);
        /*creates panel and adds all the components*/
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(panel1);
        panel.add(panel2);
        panel.add(jButtonStart);
        add(panel);

        /*event listener action for start button*/
        jButtonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*calls function and splits original file user defined number of times*/
                splitGivenFile(jtxtfieldInputFile.getText(), Integer.parseInt(jtxtfieldNumberOfFiles.getText()));
            }
        });/*end of start button event listener*/

        /*event listener action for browse button*/
        jButtonBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*object to choose file*/
                JFileChooser fileChooser = new JFileChooser();
                /*checks if file has been chosen or not with if statement*/
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    /*GET user selected file*/
                    java.io.File file = fileChooser.getSelectedFile();
                    jtxtfieldInputFile.setText(file.toString());
                }/*end if statement*/
            }/*end action performed function*/
        });/*end of browse button event listener*/
    }/*end constructor*/

    /*split function*/
    public void splitGivenFile(String fileName, int no_of_Pieces) {
        /*tries to split file with try block*/
        try {
            /*object to read input file*/
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(new File(fileName)));
            /*displays size of file*/
            System.out.println("file size: " + input.available() + " bytes");
            /*stores file size in variable*/
            long FSize = input.available();
            /*splits size of file given num of times*/
            int splitFileSize = (int) Math.ceil(1.0 * FSize / no_of_Pieces);
            /*splits file*/
            for (int i = 1; i <= no_of_Pieces; i++) {
                /*object to write output*/
                File file;
                BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(new File(fileName + "." + i)));
                int value;
                int count = 0;
                while (count++ < splitFileSize && (value = input.read()) != -1) {
                    output.write(value);
                }/*end while*/
                output.close();
            }/*end for loop*/
            input.close();
        }/*end of try block*/ catch (IOException ex) {
            ex.printStackTrace();
        }/*end of catch block*/
    }/*end of splitGivenFile*/

    /*runs the program*/
    public static void main(String[] args) {
        Main frame = new Main();
        frame.pack();
        /*set tile of frame*/
        frame.setTitle("Split files with GUI !");
        /*exits program when frame is closed by user*/
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*centers page on screen*/
        frame.setLocationRelativeTo(null);
        /*shows frame on computer screen*/
        frame.setVisible(true);
    }
}
