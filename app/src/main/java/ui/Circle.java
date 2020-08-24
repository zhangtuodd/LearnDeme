package ui;

public class Circle {

    private int raduis;         // 半径
    private int color;          // 颜色

    public Circle(int raduis, int color) {
        this.raduis = raduis;
        this.color = color;
    }

    public int getRaduis() {
        return raduis;
    }

    public void setRaduis(int raduis) {
        this.raduis = raduis;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
