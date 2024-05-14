package com.company;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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

            // Check if the file is empty or does not exist
            if (!file.exists() || file.length() == 0) {
                JOptionPane.showMessageDialog(null,
                        "The input file is empty or does not exist. Please select a non-empty file.",
                        "Empty File",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            FileHandler fileHandler = new FileHandler();
            fileHandler.setFilePath(filePath);

            try {
                DataBundle dataBundle = fileHandler.GetData();
                if (dataBundle == null) {
                    // Handling the case where GetData returns null due to an error.
                    JOptionPane.showMessageDialog(null,
                            "Error processing the file. Please check the file format and contents.",
                            "Processing Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Vector<Student> students = dataBundle.getStudents();
                Subject subject = dataBundle.getSubject();

                // Build the content string to be written and displayed
                StringBuilder content = new StringBuilder();
                content.append("Subject Name: ").append(subject.getName()).append("            Max Mark: ").append(subject.getFullMark()).append("\n");
                content.append(String.format("%-40s%-20s%-10s%-10s\n", "Student name", "Student number", "GPA", "Grade"));

                for (Student student : students) {
                    content.append(String.format("%-40s%-20s%-10s%-10s\n", student.getName(), student.getCode(), student.getGPA(), student.getGrade()));
                }

                // Update the JTextArea with the content
                outputTextArea.setText(content.toString());

                // Write the content to the file
                String outputFilePath = outputFilePathField.getText();
                try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
                    writer.print(content.toString());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error writing to file: " + ex.getMessage(),
                            "File Writing Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (IllegalArgumentException ex) {
                // Catch parsing errors from FileHandler
                JOptionPane.showMessageDialog(null,
                        "Data format error: " + ex.getMessage(),
                        "Data Format Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }






    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FileHandlerGUI fileHandlerGUI = new FileHandlerGUI();
            fileHandlerGUI.setVisible(true);
        });

    }


}
