package pro1.swingComponents;

import pro1.drawingModel.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainFrame extends JFrame {
    private java.util.List<Point> body = new ArrayList<>();
    private int tloušťka = 3;
    private boolean texty = true;
    private DisplayPanel dp = new DisplayPanel();

    public MainFrame() {
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel lp = new JPanel();
        lp.setPreferredSize(new Dimension(250, 0));
        lp.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

        JSlider s = new JSlider(1, 20, tloušťka);
        s.addChangeListener(e -> {
            tloušťka = s.getValue();
            prekresli();
        });

        JCheckBox c = new JCheckBox("Zobrazit délky", texty);
        c.addActionListener(e -> {
            texty = c.isSelected();
            prekresli();
        });

        JButton r = new JButton("Reset");
        r.addActionListener(e -> {
            body.clear();
            prekresli();
        });
        lp.add(new JLabel("Tloušťka:"));
        lp.add(s);
        lp.add(c);
        lp.add(r);
        add(lp, BorderLayout.WEST);
        add(dp, BorderLayout.CENTER);
        dp.addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent e) {
                body.add(e.getPoint());
                prekresli();
            }
        });
        setVisible(true);
    }

    private void prekresli() {
        java.util.List<Drawable> d = new ArrayList<>();
        double len = 0;
        Point p0 = null;
        for (Point p : body) {
            if (p0 != null) {
                len += p0.distance(p);
                d.add(new Line(p0.x, p0.y, p.x, p.y, tloušťka, "#0000FF"));
            }
            if (texty)
                d.add(new Text(p.x + 5, p.y - 5, String.format("%.1f px", len)));
            d.add(new Ellipse(p.x - 3, p.y - 3, 6, 6, "#FF0000"));
            p0 = p;
        }
        dp.setDrawable(new Group(d.toArray(new Drawable[0]), 0, 0, 0, 1, 1));
    }
}