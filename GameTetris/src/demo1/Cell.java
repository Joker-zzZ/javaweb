package demo1;

import java.awt.image.BufferedImage;
import java.util.Objects;

/*
编写小方块类：
    属性：行、列、每个小方块的图片
    方法：左移一格、右移一格、下移一格
 */
public class Cell {
    private int row;
    private int col;
    private BufferedImage tetrisImage;

    //小方格左移方法
    public void left(){
        col--;
    }
    //小方格右移方法
    public void right(){
        col++;
    }
    //小方格下落方法
    public void drop(){
        row++;
    }

    public Cell(int row, int col, BufferedImage tetrisImage) {
        this.row = row;
        this.col = col;
        this.tetrisImage = tetrisImage;
    }

    public Cell() {
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public BufferedImage getTetrisImage() {
        return tetrisImage;
    }

    public void setTetrisImage(BufferedImage tetrisImage) {
        this.tetrisImage = tetrisImage;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", col=" + col +
                ", tetrisImage=" + tetrisImage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row && col == cell.col && Objects.equals(tetrisImage, cell.tetrisImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, tetrisImage);
    }
}
