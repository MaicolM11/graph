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
        this.frame = new Principal<>(this, graph.getGraph());
    }

    private void resetGraph() {
        if (JOptionPane.showConfirmDialog(null, "¿Está seguro de resetear?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.graph.clear();
        }
    }

    private void deleteConn() {
        try {
            if(!graph.deleteConnection()){
                JOptionPane.showMessageDialog(null, "La conexión no existe");
            }
        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, "Seleccione dos vertices");
        }
    }

    private void addConn() {
        try {
            double weight = Double.parseDouble(JOptionPane.showInputDialog(null, "Inserte el peso"));
            if(!graph.addConn(weight)){
                JOptionPane.showMessageDialog(null, "La conexión ya existe");
            }
        } catch (NullPointerException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Datos incorrectos o incompletos");
        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, "Seleccione dos vertices");
        }
    }

    private void addVertex(Point point) {
        try {
            String value = JOptionPane.showInputDialog("Valor").toString();
            if (!graph.addAlone(value, point)) {
                JOptionPane.showMessageDialog(null, "El nodo ya existe");
            }
        } catch (NullPointerException ex) {
        }
    }

    private void evaluateClick(Point point) {
        Optional<Vertex<String, Double>> search = graph.getGraph().stream().filter(x -> x.searchCircle(point))
                .findAny();
        if (search.isPresent()) {
            graph.addSelect(search.get());
        } else {
            addVertex(point);
        }
    }

    private void deleteVertex(Point point) {
        Optional<Vertex<String, Double>> op = graph.getGraph().stream().filter(x -> x.searchCircle(point)).findAny();
        if (op.isPresent() && JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminarlo?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            graph.deleteVertex(op.get().getValue());
        }
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
        frame.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            evaluateClick(e.getPoint());
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            deleteVertex(e.getPoint());
        }
        frame.repaint();
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