package com.uptc.views;

import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class FileChooserManager extends JDialog {

    private final JFileChooser fileChooser;

    public FileChooserManager() {
        super();
        this.fileChooser = new JFileChooser();
        this.setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
        fileChooser.setFileFilter(new FileNameExtensionFilter("Txt files", "txt"));
    }    

    public String getPathSave() throws FileNotFoundException {
        int selection = fileChooser.showSaveDialog(this);
        if (selection == JFileChooser.APPROVE_OPTION)
            return fileChooser.getSelectedFile().getAbsolutePath();
        throw new FileNotFoundException();
    }

    public String getPathOpen() throws FileNotFoundException {
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION)
            return fileChooser.getSelectedFile().getAbsolutePath();
        throw new FileNotFoundException();
    }

}
