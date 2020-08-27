package com.uptc.views;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.uptc.controllers.Controller;
import com.uptc.controllers.Options;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class OptionsView extends JPanel {

    private JButton addConn;
    private JButton deleteConn;
    private JButton reset;
    private JButton dijkstra;
    private Controller ctrl;

    public OptionsView(Controller l) {
        super();
        this.ctrl = l;

        GridLayout gl = new GridLayout(1, 4);
        gl.setHgap(15);
        this.setLayout(gl);
        this.setBorder(BorderFactory.createEmptyBorder(0, 80, 10, 80));
        ui();
        init();
    }

    private void ui() {
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);
    }

    private void init() {
        addConn = new JButton("  Agregar conn");
        deleteConn = new JButton("  Eliminar conn");
        reset = new JButton("  Resetear");
        dijkstra = new JButton("  Dijkstra");

        addConn.setFocusPainted(false);
        deleteConn.setFocusPainted(false);
        reset.setFocusPainted(false);
        dijkstra.setFocusPainted(false);

        addConn.setCursor(new Cursor(Cursor. HAND_CURSOR));
        deleteConn.setCursor(new Cursor(Cursor. HAND_CURSOR));
        reset.setCursor(new Cursor(Cursor. HAND_CURSOR));
        dijkstra.setCursor(new Cursor(Cursor. HAND_CURSOR));

        addConn.setIcon(new ImageIcon(getClass().getResource("/add.png")));
        deleteConn.setIcon(new ImageIcon(getClass().getResource("/delete.png")));
        reset.setIcon(new ImageIcon(getClass().getResource("/rest.png")));
        dijkstra.setIcon(new ImageIcon(getClass().getResource("/di.png")));
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

    }
    
}
