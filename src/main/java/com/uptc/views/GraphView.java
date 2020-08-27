package com.uptc.views;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.uptc.controllers.Controller;
import com.uptc.strucs.DijkstraPoint;
import com.uptc.strucs.Vertex;

@SuppressWarnings("serial")
public class GraphView<T, W> extends JPanel implements MouseMotionListener {

    private Principal<T, W> frameP;
    private Set<Vertex<T, W>> graph;
    private List<DijkstraPoint<T, W>> wayDijkstra;
    private Vertex<T, W> circle_move;
    private Point point_move;
    private Drawing<T, W> drawing;

    public GraphView(Principal<T, W> prin) {
        this.frameP = prin;
        this.graph = new TreeSet<>();
        this.drawing = new Drawing<>();
        this.point_move = new Point();
        this.wayDijkstra = new ArrayList<>();
    }

    public void init(Controller controller) {
        this.addMouseListener(controller);
        this.addMouseMotionListener(this);
        this.setDoubleBuffered(true);
    }

    public void updateAll(boolean success, Set<Vertex<T, W>> graph, String message) {
        if (success) {
            this.graph = graph;
            frameP.repaint();
        } else {
            JOptionPane.showMessageDialog(null, message);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setFont(Constants.FORMAT_LETTER);

        graph.forEach(x -> x.getConnections().forEach(
                x1 -> drawing.paintLine(g, x.getPoint(), x1.getVertex().getPoint(), x1.getWeight().toString())));
        graph.forEach(x -> drawing.paintCircle(g, x.getPoint(), x.getValue().toString(),
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

    public void paintDijktra(List<DijkstraPoint<T, W>> values) {
        this.wayDijkstra = values;
        frameP.repaint();
    }

}