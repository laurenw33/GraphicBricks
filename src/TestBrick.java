import java.util.Arrays;

public class TestBrick {
    public static void main(String[] args) {
        BrickLayout b = new BrickLayout("src/bricks", 7, true);
        b.printBrickLayout();
        DrawPanel p = new DrawPanel();
        p.setGrid(b.getBrickLayout());
        MainFrame frame = new MainFrame("Not Tetris");
    }
}