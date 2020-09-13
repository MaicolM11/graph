package com.uptc.views;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import com.uptc.controllers.Controller;
import com.uptc.strucs.Vertex;

@SuppressWarnings("serial")
public class GraphView<T, W> extends JPanel implements MouseMotionListener {

    private Principal<T, W> frameP;
    private Set<Vertex<T, W>> graph;
    private List<Vertex<T, W>> wayDijkstra;
    private Drawing<T, W> drawing;

    private Vertex<T, W> circle_move;
    private Point point_move;

    public GraphView(Principal<T, W> prin,Set<Vertex<T, W>> graph) {
        this.frameP = prin;
        this.graph = graph;
        this.drawing = new Drawing<>();
        this.point_move = new Point();
        this.wayDijkstra = new ArrayList<>();
    }

    public void init(Controller controller) {
        this.addMouseListener(controller);
        this.addMouseMotionListener(this);
        this.setDoubleBuffered(true);
    }

    public void paintDijktra(List<Vertex<T, W>> values) {
        this.wayDijkstra = values;
        frameP.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setFont(Constants.FORMAT_LETTER);

        graph.forEach(x -> x.getConnections().forEach(
                x1 -> drawing.paintLine(g, x, x1.getVertex(), x1.getWeight().toString())));
        graph.forEach(x -> drawing.paintCircle(g, x , x.getValue().toString(),
                x.isSelect() ? Constants.COLOR_SELECT : Constants.COLOR_VERTEX));
        drawing.paintDijkstra(g, wayDijkstra);
        wayDijkstra.clear();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (circle_move == null) {
            graph.stream().filter(x -> x.searchCircle(e.getPoint())).findAny().ifPresent(x -> circle_move = x);
        } else {
            circle_move.translate(e.getPoint().x - point_move.x, e.getPoint().y - point_move.y);
        }
        point_move.setLocation(e.getPoint().x, e.getPoint().y);
        frameP.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        circle_move = null;
    }

}