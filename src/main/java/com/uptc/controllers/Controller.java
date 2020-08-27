package com.uptc.controllers;

import com.uptc.strucs.Dijkstra;
import com.uptc.strucs.Vertex;
import com.uptc.views.Principal;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.swing.JOptionPane;

public class Controller implements MouseListener, ActionListener {

    private final Dijkstra<String, Double> graph;
    private final Principal<String, Double> frame;

    public Controller() {
        this.graph = new Dijkstra<>((a, b) -> a.compareToIgnoreCase(b));
        this.frame = new Principal<>(this);
    }

    private void resetGraph() {
        if (JOptionPane.showConfirmDialog(null, "¿Está seguro de resetear?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.graph.clear();
            this.frame.updateAll(true, graph.getGraph(), "");
        }
    }

    private void deleteConn() {
        try {
            boolean add = graph.deleteConnection();
            frame.updateAll(add, graph.getGraph(), "La conexión no existe");
        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, "Seleccione dos vertices");
        }
    }

    private void addConn() {
        try {
            double weight = Double.parseDouble(JOptionPane.showInputDialog(null, "Inserte el peso"));
            boolean add = graph.addConn(weight);
            frame.updateAll(add, graph.getGraph(), "La conexión ya existe");
        } catch (NullPointerException  | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Datos incorrectos o incompletos");
        } catch (NoSuchElementException ex ){
            JOptionPane.showMessageDialog(null, "Seleccione dos vertices");            
        }
    }

    private void addVertex(Point point) {
        try {
            String value = JOptionPane.showInputDialog("Valor").toString();
            boolean add = this.graph.addAlone(value, point);
            frame.updateAll(add, graph.getGraph(), "El nodo ya existe");
        } catch (NullPointerException ex) {
        }
    }

    private void evaluateClick(Point point) {
        Optional<Vertex<String, Double>> search = graph.getGraph().stream().filter(x -> x.searchCircle(point))
                .findFirst();
        if (search.isPresent()) {
            graph.addSelect(search.get());
            frame.updateAll(true, graph.getGraph(), "");
        } else {
            addVertex(point);
        }
    }

    private void deleteVertex(Point point) {
        Optional<Vertex<String, Double>> op = graph.getGraph().stream().filter(x -> x.searchCircle(point)).findAny();
        if (op.isPresent() && JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminarlo?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            graph.deleteVertex(op.get().getValue());
            frame.updateAll(true, graph.getGraph(), "");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Options.valueOf(e.getActionCommand())) {
            case ADD_CONN:
                addConn();
                break;
            case DELETE_CONN:
                deleteConn();
                break;
            case RESET:
                resetGraph();
                break;
            case DIJKSTRA:
                runDijkstra();
                break;
            default:
                break;
        }
        frame.updateAll(true, graph.getGraph(), "");
    }

    private void runDijkstra() {
        try {
            graph.resetWay();
            graph.init();
            frame.paintDijktra(graph.getWay());
        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, "Seleccione dos nodos que esten conectados");
        } 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            evaluateClick(e.getPoint());
        } else if (e.getButton() == 3) {
            deleteVertex(e.getPoint());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}