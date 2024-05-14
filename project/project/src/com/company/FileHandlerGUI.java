package com.company;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;


public class FileHandlerGUI extends JFrame{
    JTextField filePathField;
    JTextField outputFilePathField;
    private JTextArea outputTextArea;
    private String filePath;


    public FileHandlerGUI() {
        this.filePath = filePath;
        setTitle("File Handler");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel filePathLabel = new JLabel("Input File Path:");
        topPanel.add(filePathLabel, gbc);

        gbc.gridx = 1;
        filePathField = new JTextField(20);
        topPanel.add(filePathField, gbc);

        gbc.gridx = 2;
        JButton browseButton = new JButton("Browse");
        browseButton.addActionListener(new BrowseButtonListener());
        topPanel.add(browseButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel outputFilePathLabel = new JLabel("Output File Path:");
        topPanel.add(outputFilePathLabel, gbc);

        gbc.gridx = 1;
        outputFilePathField = new JTextField(20);
        topPanel.add(outputFilePathField, gbc);

        gbc.gridx = 2;
        JButton outputBrowseButton = new JButton("Browse");
        outputBrowseButton.addActionListener(new OutputBrowseButtonListener());
        topPanel.add(outputBrowseButton, gbc);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        outputTextArea = new JTextArea(10, 40);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        centerPanel.add(scrollPane);
        add(centerPanel, BorderLayout.CENTER);

        JButton processButton = new JButton("Process File");
        processButton.addActionListener(new ProcessButtonListener());
        add(processButton, BorderLayout.SOUTH);
    }

    private class BrowseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            fileChooser.setFileFilter(filter);
            fileChooser.setAcceptAllFileFilterUsed(false);
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filePathField.setText(selectedFile.getAbsolutePath());
            }
        }
    }


    private class OutputBrowseButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Output File");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedDirectory = fileChooser.getSelectedFile();
                String outputPath = selectedDirectory.getAbsolutePath() + File.separator + "output.txt";
                outputFilePathField.setText(outputPath);
            }
        }
    }

    class ProcessButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String filePath = filePathField.getText();
            File file = new File(filePath);

            // Check if the file is empty
            if (file.length() == 0) {
                JOptionPane.showMessageDialog(null,
                        "The input file is empty. Please select a non-empty file.",
                        "Empty File",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            FileHandler fileHandler = new FileHandler();
            fileHandler.setFilePath(filePath);
            DataBundle dataBundle = fileHandler.GetData();

            Vector<Student> students = dataBundle.getStudents();
            Subject subject = dataBundle.getSubject();

            // Print students info from file
            System.out.println("Students info from file:");
            for (Student st : students) {
                System.out.println(st.printStudent());
            }

            String outputFilePath = outputFilePathField.getText();
            OutputFileHandler output = new OutputFileHandler();
            output.printFile(students, subject, outputFilePath);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FileHandlerGUI fileHandlerGUI = new FileHandlerGUI();
            fileHandlerGUI.setVisible(true);
        });

    }


}
