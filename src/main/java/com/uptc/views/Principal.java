package com.uptc.views;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import com.uptc.controllers.Controller;
import com.uptc.strucs.Vertex;

@SuppressWarnings("serial")
public class Principal<T, W> extends JFrame {

    private GraphView<T, W> graphView;
    private FileChooserManager fileChooser;

    public Principal(Controller ctrl, Set<Vertex<T, W>> graph) {
        super();
        initUI();
        this.setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
        this.setTitle("Dijkstra Software");
        this.setLayout(new BorderLayout());
        this.setBounds(0, 0, 1000, 600);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.WHITE);
        graphView = new GraphView<>(this, graph);
        fileChooser = new FileChooserManager();
        add(graphView, BorderLayout.CENTER);
        add(new OptionsView(ctrl), BorderLayout.SOUTH);
        graphView.init(ctrl);
        this.setVisible(true);
    }

    public void paintDijktra(List<Vertex<T, W>> values) {
        this.graphView.paintDijktra(values);
    }

    private void initUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
        }
    }

    public String getPathOpen() throws FileNotFoundException {
        return fileChooser.getPathOpen();
    }

    public String getPathSave() throws FileNotFoundException {
        return fileChooser.getPathSave();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public boolean confirm(String message) {
        return JOptionPane.showConfirmDialog(null, message, "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    public String getInput(String message) {
        return JOptionPane.showInputDialog(this, message);
    }

}