import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BrickLayout {

    private ArrayList<Brick> bricks;
    private int[][] brickLayout;
    private int cols;
    private int[][] temporaryDisplay;

    public BrickLayout(String fileName, int cols, boolean dropAllBricks) {
        this.cols = cols;
        ArrayList<String> fileData = getFileData(fileName);
        bricks = new ArrayList<Brick>();
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
        if (bricks.size() != 0) {
            Brick b = bricks.remove(0);
            int start = b.getStart();
            int end = b.getEnd();

            int row = brickLayout.length - 1;
            boolean placed = false;

            while (!placed && row >= 0) {
                boolean canPlace = true;
                for (int col = start; col <= end; col++) {
                    if (brickLayout[row][col] == 1) {
                        canPlace = false;
                        break;
                    }
                }

                if (canPlace) {
                    boolean canFitAbove = true;
                    for (int r = row - 1; r >= 0; r--) {
                        for (int col = start; col <= end; col++) {
                            if (brickLayout[r][col] == 1) {
                                canFitAbove = false;
                                break;
                            }
                        }
                        if (!canFitAbove) break;
                    }

                    if (canFitAbove || row == 0) {
                        for (int col = start; col <= end; col++) {
                            brickLayout[row][col] = 1;
                        }
                        placed = true;
                    } else {
                        row--;
                    }
                } else {
                    row--;
                }
            }
        }
    }

    public void bricksFalling() {
        long startTime = System.currentTimeMillis();
        long timeInterval = 1000;
        int row = 0;
        while (true) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - startTime >= timeInterval) {
                startTime = currentTime;

                if (row > 0) {
                    for (Brick b : bricks) {
                        for (int c = b.getStart(); c < b.getEnd(); c++) {
                            temporaryDisplay[row + 1][c] = 0;
                        }
                    }
                }

                for (Brick b : bricks) {
                    for (int c = b.getStart(); c < b.getEnd(); c++) {
                        temporaryDisplay[row][c] = 1;
                    }
                }

                row++;

                if (row >= brickLayout.length) {
                    break;
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