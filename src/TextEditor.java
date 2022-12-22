import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //frame for Text Editor
    JFrame frame;
    //menubar for texteditor
    JMenuBar menuBar;
    //initializing menus
    JMenu file,edit;
    //initializing menuitems
    JMenuItem newFile,openFile,saveFile;
    JMenuItem cut,copy,paste,selectall,close;
    JTextArea textArea;
    //Constructor
    TextEditor(){
        //initializing frame
        frame = new JFrame();
        //initializing menubar
        menuBar = new JMenuBar();
        //initilalizg textarea
        textArea = new JTextArea();
        frame.add(textArea);
        file=new JMenu("File");
        edit=new JMenu("Edit");
        //adding menus to menubar
        menuBar.add(file);
        menuBar.add(edit);
        //initializing menuitems in menu
        newFile=new JMenuItem("New");
        openFile=new JMenuItem("Open");
        saveFile=new JMenuItem("Save");
        //adding actions to file menu
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        //initializing menuites in edit menu
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectall=new JMenuItem("SelectAll");
        close=new JMenuItem("Close");
        //adding actions to edit menu
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);
        // adding file menuitems to menubar
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        //adding menuitems to menubar
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);
        //setting menubar in frame
        frame.setJMenuBar(menuBar);


        frame.setBounds(100,100,800,500);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //adding functionality to menuitems
        // if source is cut
        if (actionEvent.getSource() == cut) {
            textArea.cut();
        }
        // if source is copy
        if (actionEvent.getSource() == copy) {
            textArea.copy();
        }
        // if source is paste
        if (actionEvent.getSource() == paste) {
            textArea.paste();
        }
        // if source is selecttall
        if (actionEvent.getSource() == selectall) {
            textArea.selectAll();
        }
        // if source is close
        if (actionEvent.getSource() == close) {
            System.exit(0);
        }
        // if source is new
        if (actionEvent.getSource() == newFile) {
            TextEditor newWindow = new TextEditor();
        }
        //if source is open
        if (actionEvent.getSource() == openFile) {
            //initialize file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from file chooser
            int chooseOption = fileChooser.showOpenDialog(null);
            //if choose option is equal to approve
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                // get selected file
                File file = fileChooser.getSelectedFile();
                //get selected file path
                String filepath = file.getPath();
                try {
                    // create buffer reader
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
                    //create string to store content from file
                    String intermediate = "", output = "";
                    //read content line by line
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output = output + intermediate + "\n";
                    }
                    //set output to textarea
                    textArea.setText(output);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        //if source is save
        if (actionEvent.getSource() == saveFile) {
            //initialize file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //if choose option is equal to approve
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                //create a file object with selected path
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try {
                    //crete bufferedwriter to write content to file
                    BufferedWriter outfile = new BufferedWriter(new FileWriter(file));
                    //get content from textarea to outfile
                    textArea.write(outfile);
                    outfile.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }


            }


        }
    }
    public static void main(String[] args) {
        //initialize text editor

        TextEditor textEditor = new TextEditor();
    }
}