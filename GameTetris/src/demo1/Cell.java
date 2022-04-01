package demo1;

import java.awt.image.BufferedImage;
import java.util.Objects;

/*
��дС�����ࣺ
    ���ԣ��С��С�ÿ��С�����ͼƬ
    ����������һ������һ������һ��
 */
public class Cell {
    private int row;
    private int col;
    private BufferedImage tetrisImage;

    //С�������Ʒ���
    public void left(){
        col--;
    }
    //С�������Ʒ���
    public void right(){
        col++;
    }
    //С�������䷽��
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
