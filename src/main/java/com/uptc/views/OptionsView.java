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
    private String[][] titles;

    public OptionsView(Controller l) {
        super();
        this.ctrl = l;
        GridLayout gl = new GridLayout(1, 6);
        gl.setHgap(10);
        this.setLayout(gl);
        this.setBorder(BorderFactory.createEmptyBorder(0, 40, 10, 40));
        initArrays();
    }

    private void initArrays() {
        this.titles = new String[][] { 
                { "  Abrir archivo", "/open.png", Options.OPEN_FILE.name() },
                { "  Guardar archivo", "/save.png", Options.SAVE_FILE.name() },
                { "  Agregar conn", "/add.png", Options.ADD_CONN.name() },
                { "  Eliminar conn", "/delete.png", Options.DELETE_CONN.name() },
                { "  Dijkstra", "/di.png", Options.DIJKSTRA.name() },
                { "  Resetear", "/rest.png", Options.RESET.name() } };
        init();
    }

    private void init() {
        for (String[] t : titles) {
            JButton bttn = new JButton(t[0]);
            bttn.setFocusPainted(false);
            bttn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            bttn.setIcon(new ImageIcon(getClass().getResource(t[1])));
            bttn.addActionListener(ctrl);
            bttn.setActionCommand(t[2]);
            add(bttn);
        }
    }

}
