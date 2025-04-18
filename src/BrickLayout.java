import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BrickLayout {

    private ArrayList<Brick> bricks;
    private ArrayList<Brick> fallingBricks;
    private int[][] brickLayout;
    private int cols;
    private int[][] temporaryDisplay;

    public BrickLayout(String fileName, int cols, boolean dropAllBricks) {
        this.cols = cols;
        ArrayList<String> fileData = getFileData(fileName);
        bricks = new ArrayList<Brick>();
        fallingBricks = new ArrayList<Brick>();
        for (String line : fileData) {
            String[] points = line.split(",");
            int start = Integer.parseInt(points[0]);
            int end = Integer.parseInt(points[1]);
            Brick b = new Brick(start, end);
            bricks.add(b);
        }
        brickLayout = new int[bricks.size()][cols];
        temporaryDisplay = new int[bricks.size()][cols];
        if (dropAllBricks) {
            while (bricks.size() != 0) {
                doOneBrick();
            }
        }
    }

    public void doOneBrick() {
        Brick b = bricks.remove(0);
        int start = b.getStart();
        int end = b.getEnd();

        int row = temporaryDisplay.length - 1;
        boolean placed = false;

        while (!placed && row >= 0) {
            boolean canPlace = true;
            // checks if you can't put it above a brick
            for (int col = start; col <= end; col++) {
                if (temporaryDisplay[row][col] == 1) {
                    canPlace = false; // when you can't place it above a brick
                    break;
                }
            }

            if (canPlace) { // if you can fit it above
                boolean canFitAbove = true;
                for (int r = row - 1; r >= 0; r--) {
                    for (int col = start; col <= end; col++) {
                        if (temporaryDisplay[r][col] == 1) {
                            canFitAbove = false;
                            break;
                        }
                    }
                    if (!canFitAbove) break;
                }

                if (canFitAbove || row == 0) { // checks if you can put it down
                    for (int col = start; col <= end; col++) {
                        temporaryDisplay[row][col] = 1; // sets equal to one
                    }
                    placed = true;
                    b.setPlaced(true);
                    b.setEndingRow(row);
                    fallingBricks.add(b);
                } else {
                    row--;
                }
            } else {
                row--;
            }
        }
    }

    public void bricksFalling() {
        for (Brick brick : fallingBricks) {
            if (brick.isPlaced()) {
                for (int col = brick.getStart(); col <= brick.getEnd(); col++) {
                    brickLayout[brick.getCurrRow()][col] = 0;
                }
                brick.setCurrRow(brick.getCurrRow() + 1);

                for (int col = brick.getStart(); col <= brick.getEnd(); col++) {
                    brickLayout[brick.getCurrRow()][col] = 1;
                }
                if (brick.getCurrRow() == brick.getEndingRow()) {
                    brick.setPlaced(false);
                }
            }
        }
    }


    public int[][] getBrickLayout() {
        return brickLayout;
    }

    public int[][] getTemporaryDisplay() {
        return temporaryDisplay;
    }

    public ArrayList<String> getFileData(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }
        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        return fileData;
    }

    public void printBrickLayout() {
        for (int r = 0; r < brickLayout.length; r++) {
            for (int c = 0; c < brickLayout[0].length; c++) {
                System.out.print(brickLayout[r][c] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkBrickSpot(int r, int c) {
        if (brickLayout[r][c] == 1) {
            return true;
        }
        else {
            return false;
        }
    }
}