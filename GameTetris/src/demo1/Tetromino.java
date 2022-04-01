package demo1;

import java.util.Objects;

/*
С������
    ���ԣ�ÿһ�����͵ķ������ĸ�С����ƴ��
    ����������һ������һ������һ�񡢣������ݲ����ǣ�
 */
public class Tetromino {
    //���ԣ�ÿһ�����͵ķ������ĸ�С����ƴ��
    protected Cell[] cells = new Cell[4];
    //��д��ת״̬
    protected State[] states;
    //������ת����
    private int spinCount  = 100;


    //�ķ���˳ʱ����ת����
    public void rotateRight(){
        if (states.length == 0){
            return;
        }
        //��ת����+1
        spinCount++;
        State s = states[spinCount % states.length];
        Cell cell = cells[0];
        int row = cell.getRow();
        int col = cell.getCol();
        cells[1].setRow(row + s.row1);
        cells[1].setCol(col + s.col1);
        cells[2].setRow(row + s.row2);
        cells[2].setCol(col + s.col2);
        cells[3].setRow(row + s.row3);
        cells[3].setCol(col + s.col3);
    }

    //�ķ�����ʱ����ת����
    public void rotateLeft(){
        if (states.length == 0){
            return;
        }
        //��ת����-1
        spinCount--;
        State s = states[spinCount % states.length];
        Cell cell = cells[0];
        int row = cell.getRow();
        int col = cell.getCol();
        cells[1].setRow(row + s.row1);
        cells[1].setCol(col + s.col1);
        cells[2].setRow(row + s.row2);
        cells[2].setCol(col + s.col2);
        cells[3].setRow(row + s.row3);
        cells[3].setCol(col + s.col3);
    }

    //��д�ķ�����ת״̬���ڲ���
    class State{
        //��д���ԣ��洢�ķ����Ԫ�ص����λ��
        int row0,col0,row1,col1,row2,col2,row3,col3;

        public State(int row0, int col0, int row1, int col1, int row2, int col2, int row3, int col3) {
            this.row0 = row0;
            this.col0 = col0;
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
            this.row3 = row3;
            this.col3 = col3;
        }

        public State() {
        }

        public int getRow0() {
            return row0;
        }

        public void setRow0(int row0) {
            this.row0 = row0;
        }

        public int getCol0() {
            return col0;
        }

        public void setCol0(int col0) {
            this.col0 = col0;
        }

        public int getRow1() {
            return row1;
        }

        public void setRow1(int row1) {
            this.row1 = row1;
        }

        public int getCol1() {
            return col1;
        }

        public void setCol1(int col1) {
            this.col1 = col1;
        }

        public int getRow2() {
            return row2;
        }

        public void setRow2(int row2) {
            this.row2 = row2;
        }

        public int getCol2() {
            return col2;
        }

        public void setCol2(int col2) {
            this.col2 = col2;
        }

        public int getRow3() {
            return row3;
        }

        public void setRow3(int row3) {
            this.row3 = row3;
        }

        public int getCol3() {
            return col3;
        }

        public void setCol3(int col3) {
            this.col3 = col3;
        }

        @Override
        public String toString() {
            return "State{" +
                    "row0=" + row0 +
                    ", col0=" + col0 +
                    ", row1=" + row1 +
                    ", col1=" + col1 +
                    ", row2=" + row2 +
                    ", col2=" + col2 +
                    ", row3=" + row3 +
                    ", col3=" + col3 +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return row0 == state.row0 && col0 == state.col0 && row1 == state.row1 && col1 == state.col1 && row2 == state.row2 && col2 == state.col2 && row3 == state.row3 && col3 == state.col3;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row0, col0, row1, col1, row2, col2, row3, col3);
        }
    }

    //����һ�񷽷�
    public void moveLeft(){
        for (Cell cell : cells) {
            cell.left();
        }
    }
    //����һ�񷽷�
    public void moveRight(){
        for (Cell cell : cells) {
            cell.right();
        }
    }
    //��������һ�񷽷�
    public void softDrop(){
        for (Cell cell : cells) {
            cell.drop();
        }
    }
    //��������ķ���ľ�̬����
    public static Tetromino randomOne(){
        int num = (int)(Math.random() * 7);
        Tetromino tetromino = null;
        switch(num){
            case 0:
                tetromino = new I();
                break;
            case 1:
                tetromino = new J();
                break;
            case 2:
                tetromino = new L();
                break;
            case 3:
                tetromino = new O();
                break;
            case 4:
                tetromino = new S();
                break;
            case 5:
                tetromino = new T();
                break;
            case 6:
                tetromino = new Z();
                break;
        }
        return tetromino;
    }
}
