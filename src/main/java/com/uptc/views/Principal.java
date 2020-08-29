package com.uptc.views;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;

import java.util.List;
import java.util.Set;

import com.uptc.controllers.Controller;
import com.uptc.strucs.DijkstraPoint;
import com.uptc.strucs.Vertex;

public class Principal<T, W> extends JFrame {

    private static final long serialVersionUID = 1L;

    private GraphView<T, W> graphView;

    public Principal(Controller ctrl, Set<Vertex<T, W>> graph) {
        super();
        this.setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
        this.setTitle("Dijkstra Software");
        this.setLayout(new BorderLayout());
        this.setBounds(0, 0, 800, 500);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.WHITE);
        graphView = new GraphView<>(this, graph);
        add(graphView, BorderLayout.CENTER);
        add(new OptionsView(ctrl), BorderLayout.SOUTH);
        graphView.init(ctrl);

        this.setVisible(true);
    }

    public void paintDijktra(List<DijkstraPoint<T, W>> values) {
        this.graphView.paintDijktra(values);
    }
}