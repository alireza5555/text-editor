package editor;

import javax.swing.*;
import java.io.*;



public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            BufferedReader buffer = null;
            try {
                buffer = new BufferedReader(new FileReader(file));
                String line;
                while((line = buffer.readLine()) != null){
                    textArea.append(line + "\n");
                }

            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }

        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        if (textEditor.currentFile == null) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                    writer.write(textArea.getText());
                    textEditor.currentFile = selectedFile;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(textEditor.currentFile))) {
                writer.write(textArea.getText());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static void newFile(TextEditor textEditor, JTextArea textArea) {
            textArea.setText("");
            textEditor.currentFile = null;
    }
}
