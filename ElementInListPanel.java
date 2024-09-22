import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public abstract class ElementInListPanel extends JPanel {

    JLabel place;
    JLabel address;

    public ElementInListPanel() {
        super(new GridLayout());
        place = new JLabel("Place: ");
        address = new JLabel("Address: ");

        add(place);
        add(address);

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);
        g2.setColor(Color.gray);
        g2.setStroke(new BasicStroke(5));
        g2.draw(new Line2D.Float(0, 0, 500, 0));
    }

    protected abstract void addElements();

}