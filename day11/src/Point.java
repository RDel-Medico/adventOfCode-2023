package day11.src;

public class Point {
    int x;
    int y;

    Point (int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int distance (Point p) {
        return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
    }
}
