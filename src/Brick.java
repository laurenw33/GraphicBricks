public class Brick {
    private int start;
    private int end;
    private int height;
    private int currRow;
    private int endingRow;
    private boolean placed;

    public Brick(int start, int end) {
        this.start = start;
        this.end = end;
        currRow = 0;
        this.height = 0;
        endingRow = 0;
        this.placed = false;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String toString() {
        return start + "," + end + " --> Height: " + height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setEndingRow(int endingRow) {this.endingRow = endingRow;}

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public int getEndingRow() {
        return endingRow;
    }

    public int getCurrRow() {
        return currRow;
    }

    public void setCurrRow(int currRow) {
        this.currRow = currRow;
    }
}