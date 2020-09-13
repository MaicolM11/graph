package com.uptc.views;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


import com.uptc.controllers.Controller;
import com.uptc.controllers.Options;


import java.awt.Cursor;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class OptionsView extends JPanel {

    private Controller ctrl;

    private JButton addConn;
    private JButton deleteConn;
    private JButton reset;
    private JButton dijkstra;
    private JButton openFile;
    private JButton saveFile;
    

    public OptionsView(Controller l) {
        super();
        this.ctrl = l;

        GridLayout gl = new GridLayout(1, 6);
        gl.setHgap(10);
        this.setLayout(gl);
        this.setBorder(BorderFactory.createEmptyBorder(0, 40, 10, 40));
        init();
    }

    private void init() {
        addConn = new JButton("  Agregar conn");
        deleteConn = new JButton("  Eliminar conn");
        reset = new JButton("  Resetear");
        dijkstra = new JButton("  Dijkstra");
        openFile = new JButton("  Abrir archivo");
        saveFile = new JButton("  Guardar archivo");

        addConn.setFocusPainted(false);
        deleteConn.setFocusPainted(false);
        reset.setFocusPainted(false);
        dijkstra.setFocusPainted(false);
        openFile.setFocusPainted(false);
        saveFile.setFocusPainted(false);

        addConn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteConn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        reset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dijkstra.setCursor(new Cursor(Cursor.HAND_CURSOR));
        openFile.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveFile.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addConn.setIcon(new ImageIcon(getClass().getResource("/add.png")));
        deleteConn.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
        reset.setIcon(new ImageIcon(getClass().getResource("/rest.png")));
        dijkstra.setIcon(new ImageIcon(getClass().getResource("/di.png")));
        openFile.setIcon(new ImageIcon(getClass().getResource("/open.png")));
        saveFile.setIcon(new ImageIcon(getClass().getResource("/save.png")));


        this.add(openFile);
        this.add(saveFile);
        this.add(addConn);
        this.add(deleteConn);
        this.add(reset);
        this.add(dijkstra);
        
        
        listener();

    }

    private void listener() {
        this.addConn.addActionListener(ctrl);
        this.addConn.setActionCommand(Options.ADD_CONN.name());

        this.deleteConn.addActionListener(ctrl);
        this.deleteConn.setActionCommand(Options.DELETE_CONN.name());

        this.reset.addActionListener(ctrl);
        this.reset.setActionCommand(Options.RESET.name());

        this.dijkstra.addActionListener(ctrl);
        this.dijkstra.setActionCommand(Options.DIJKSTRA.name());

        this.openFile.addActionListener(ctrl);
        this.openFile.setActionCommand(Options.OPEN_FILE.name());

        this.saveFile.addActionListener(ctrl);
        this.saveFile.setActionCommand(Options.SAVE_FILE.name());

    }

}
