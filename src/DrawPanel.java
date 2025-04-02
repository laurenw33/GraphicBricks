import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseListener{

    private int[][] grid;

    public DrawPanel() {
        this.addMouseListener(this);
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
        repaint();
    }

    public int[][] getGrid() {
        return grid;
    }
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        int x;
//        int y = 10;
//        for (int c = 0; c < 30; c ++) {
//            x = 10;
//            for (int r = 0; r < 40; r++) {
//                g.drawRect(x, y, 20, 20);
//                x += 25;
//            }
//            y += 25;
//        }
//
//        Graphics2D g2 = (Graphics2D) g;
//
//        int x1;
//        int y1 = 10;
//        for (int r = 0; r < 30; r ++) {
//            x1 = 10;
//            for (int c = 0; c < 40; c++) {
//                if (grid[r][c] == 1) {
//                    g2.setColor(Color.red);
//                    g2.fillRect(x1, y1, 20, 20);
//                    g2.setColor(Color.black);
//                }
//                x1 += 25;
//            }
//            y1 += 25;
//        }
//    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {

    }
}