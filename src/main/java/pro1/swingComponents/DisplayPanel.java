package pro1.swingComponents;
import pro1.drawingModel.Drawable;
import javax.swing.JPanel;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private Drawable drawable;
    public DisplayPanel() { setBackground(Color.WHITE); }
    public void setDrawable(Drawable d) { this.drawable = d; repaint(); }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (drawable != null) drawable.draw((Graphics2D) g);
    }
}