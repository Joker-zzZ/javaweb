package demo1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
����˹��������
 */
public class Tetris extends JPanel {

   //������������ķ���
   private Tetromino currentOne = Tetromino.randomOne();
   //������һ����Ҫ����ķ���
   private Tetromino nextOne = Tetromino.randomOne();
   //������Ϸ��������ǽ
   private Cell[][] wall = new Cell[18][9];
   //����һ��С����ĳߴ�
   public static final int CELL_SIZE = 48;
   //������Ϸ�÷ֳ�
   int[] scores_pool = {0, 10, 30, 50, 100};
   //��ʼ���ܷ���
   private int totalScore = 0;
   //��ʼ�����������
   private int totalLine = 0;
   //��Ϸ������״̬����Ϸ�С���ͣ�����¿�ʼ
   public static final int PLAYING = 0;
   public static final int PAUSE = 1;
   public static final int GAMEOVER = 2;
   //����������ŵ�ǰ��Ϸ��״ֵ̬
   private int game_state;
   //����һ���ַ������飬������ʾ��ǰ����Ϸ״̬
   String[] show_state = {"P [��ͣ] ", "C [����]", "R [����]"};


   public static BufferedImage I;
   public static BufferedImage J;
   public static BufferedImage L;
   public static BufferedImage O;
   public static BufferedImage S;
   public static BufferedImage T;
   public static BufferedImage Z;
   public static BufferedImage backImage;

   static {
      try {
         backImage = ImageIO.read(new File("src/images/background.png"));
         I = ImageIO.read(new File("src/images/I.png"));
         J = ImageIO.read(new File("src/images/J.png"));
         L = ImageIO.read(new File("src/images/L.png"));
         O = ImageIO.read(new File("src/images/O.png"));
         S = ImageIO.read(new File("src/images/S.png"));
         T = ImageIO.read(new File("src/images/T.png"));
         Z = ImageIO.read(new File("src/images/Z.png"));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   @Override
   public void paint(Graphics g) {
      //������Ϸ����
      g.drawImage(backImage, 0, 0, null);
      //ƽ��������
      g.translate(22, 15);
      //������Ϸ������
      paintWall(g);
      //��������������ķ���
      paintCurrentOne(g);
      //���ƽ�Ҫ�������һ���ķ���
      paintNextOne(g);
      //������Ϸ�÷�
      paintScore(g);
      //���Ƶ�ǰ��Ϸ״̬
      paintState(g);
   }

   //�������̼���
   public void start() {
      game_state = PLAYING;
      KeyListener keyListener = new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            switch (code) {
               case KeyEvent.VK_DOWN:
                  sortDropAction();//����һ��
                  break;
               case KeyEvent.VK_LEFT:
                  moveLeftAction();//����һ��
                  break;
               case KeyEvent.VK_RIGHT:
                  moveRightAction();//����һ��
                  break;
               case KeyEvent.VK_UP:
                  rotateRightAction();//˳ʱ����ת
                  break;
               case KeyEvent.VK_SPACE:
                  handDropAction();//��������
                  break;
               case KeyEvent.VK_P:
                  //�жϵ�ǰ��Ϸ״̬
                  if (game_state == PLAYING) {
                     game_state = PAUSE;
                  }
                  break;
               case KeyEvent.VK_C:
                  //�жϵ�ǰ��Ϸ״̬
                  if (game_state == PAUSE) {
                     game_state = PLAYING;
                  }
                  break;
               case KeyEvent.VK_R:
                  //��ʾ��Ϸ���¿�ʼ
                  game_state = PLAYING;
                  wall = new Cell[18][9];
                  currentOne = Tetromino.randomOne();
                  nextOne = Tetromino.randomOne();
                  totalScore = 0;
                  totalLine = 0;
                  break;
            }
         }
      };
      //������˹���鴰������Ϊ����
      this.addKeyListener(keyListener);
      this.requestFocus();

      //�жϵ�ǰ��Ϸ״̬�Ƿ�����Ϸ�У�����Ϸ��ÿ��0.5���ػ滭��һ��
      while (true) {
         if (game_state == PLAYING) {
            try {
               Thread.sleep(800);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            //�ж��ܷ�����
            if (canDrop()) {
               currentOne.softDrop();
            } else {
               //Ƕ�뵽ǽ��
               landToWall();
               //�ж��ܷ�����
               destoryLine();
               //�ж���Ϸ�Ƿ����
               if (isGameOver()) {
                  game_state = GAMEOVER;
               } else {
                  currentOne = nextOne;
                  nextOne = Tetromino.randomOne();
               }
            }
         }
         repaint();
      }
   }

   //����˳ʱ����ת����
   public void rotateRightAction() {
      currentOne.rotateRight();
      //�ж��Ƿ��غϻ���Խ��
      if (outOfBounds() || coincide()) {
         currentOne.rotateLeft();
      }
   }

   //������ʱ����ת����
   public void rotateLeftAction() {
      currentOne.rotateLeft();
      if (outOfBounds() || coincide()) {
         currentOne.rotateRight();
      }
   }

   //��������
   public void handDropAction() {
      while (true) {
         //�ж��ܷ�����
         if (canDrop()) {
            //��ǰ�ķ�������һ��
            currentOne.softDrop();
         } else {
            break;
         }
      }
      //����ǰ�ķ���Ƕ�뵽ǽ��
      landToWall();
      //�ж��ܷ�����
      destoryLine();
      //�ж���Ϸ�Ƿ����
      if (isGameOver()) {
         game_state = GAMEOVER;
      } else {
         //��ǰ��Ϸû�н���������������һ���ķ���
         currentOne = nextOne;
         nextOne = Tetromino.randomOne();
      }
   }

   //����һ���ķ�������һ��
   public void sortDropAction() {
      //�ж��ܷ�����
      if (canDrop()) {
         //��ǰ�ķ�������һ��
         currentOne.softDrop();
      } else {
         //����ǰ�ķ���Ƕ�뵽ǽ��
         landToWall();
         //�ж��ܷ�����
         destoryLine();
         //�ж���Ϸ�Ƿ����
         if (isGameOver()) {
            game_state = GAMEOVER;
         } else {
            //��ǰ��Ϸû�н���������������һ���ķ���
            currentOne = nextOne;
            nextOne = Tetromino.randomOne();
         }
      }
   }

   //����ǰ�ķ���Ƕ�뵽ǽ��
   private void landToWall() {
      Cell[] cells = currentOne.cells;
      for (Cell cell : cells) {
         int row = cell.getRow();
         int col = cell.getCol();
         wall[row][col] = cell;
      }
   }

   //�ж��ķ����ܷ�����
   public boolean canDrop() {
      Cell[] cells = currentOne.cells;
      for (Cell cell : cells) {
         int row = cell.getRow();
         int col = cell.getCol();
         //�ж��Ƿ����䵽�ײ�
         if (row == wall.length - 1) {
            return false;
         } else if (wall[row + 1][col] != null) {
            return false;
         }
      }
      return true;
   }

   //�������з���
   public void destoryLine() {
      //����������ͳ�Ƶ�ǰ����������
      int line = 0;
      Cell[] cells = currentOne.cells;
      for (Cell cell : cells) {
         int row = cell.getRow();
         //�жϵ�ǰ���Ƿ�����
         if (isFullLine(row)) {
            line++;
            for (int i = row; i > 1; i--) {
               System.arraycopy(wall[i - 1], 0, wall[i], 0, wall[0].length);
            }
            //wall[0] = new Cell[9];
         }
      }
      //�ڷ������л�ȡ�������ۼӵ��ܷ�����
      totalScore += scores_pool[line];
      //ͳ������������
      totalLine += line;
   }

   //�жϵ�ǰ���Ƿ�����
   public boolean isFullLine(int row) {
      Cell[] cells = wall[row];
      for (Cell cell : cells) {
         if (cell == null) {
            return false;
         }
      }
      return true;
   }

   //�ж���Ϸ�Ƿ����
   public boolean isGameOver() {
      Cell[] cells = nextOne.cells;
      for (Cell cell : cells) {
         int row = cell.getRow();
         int col = cell.getCol();
         if (wall[row][col] != null) {
            return true;
         }
      }
      return false;
   }


   private void paintState(Graphics g) {
      if (game_state == PLAYING) {
         g.drawString(show_state[game_state] + show_state[2], 500, 660);
      } else if (game_state == PAUSE) {
         g.drawString(show_state[game_state] + show_state[2], 500, 660);
      } else if (game_state == GAMEOVER) {
         g.drawString(show_state[game_state] + show_state[2], 500, 660);
         g.setColor(Color.red);
         g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
         g.drawString("GameOver!", 55, 420);
      }
   }

   private void paintScore(Graphics g) {
      g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
      g.drawString("����:  " + totalScore, 500, 250);
      g.drawString("������:  " + totalLine, 500, 435);
   }

   private void paintNextOne(Graphics g) {
      Cell[] cells = nextOne.cells;
      for (Cell cell : cells) {
         int x = cell.getCol() * CELL_SIZE;
         int y = cell.getRow() * CELL_SIZE;
         g.drawImage(cell.getTetrisImage(), x + 370, y + 20, null);
      }
   }

   private void paintCurrentOne(Graphics g) {
      Cell[] cells = currentOne.cells;
      for (int i = 0; i < cells.length; i++) {
         int x = cells[i].getCol() * CELL_SIZE;
         int y = cells[i].getRow() * CELL_SIZE;
         g.drawImage(cells[i].getTetrisImage(), x, y, null);
      }
   }

   private void paintWall(Graphics g) {
      for (int i = 0; i < wall.length; i++) {
         for (int j = 0; j < wall[i].length; j++) {
            int x = j * CELL_SIZE;
            int y = i * CELL_SIZE;
            Cell cell = wall[i][j];
            //�жϵ�ǰ��Ԫ���Ƿ���С���飬���û������Ʒ��飬�����򽫷���Ƕ�뵽ǽ�С�
            if (cell == null) {
               g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
            } else {
               g.drawImage(cell.getTetrisImage(), x, y, null);
            }
         }
      }
   }

   //�жϷ����Ƿ�Խ��
   public boolean outOfBounds() {
      Cell[] cells = currentOne.cells;
      for (Cell cell : cells) {
         int col = cell.getCol();
         int row = cell.getRow();
         if (row < 0 || row > wall.length - 1 || col < 0 || col > wall[0].length - 1) {
            return true;
         }
      }
      return false;
   }

   //�жϷ����Ƿ��غ�
   public boolean coincide() {
      Cell[] cells = currentOne.cells;
      for (Cell cell : cells) {
         int row = cell.getRow();
         int col = cell.getCol();
         if (wall[row][col] != null) {
            return true;
         }
      }
      return false;
   }

   //����һ���ķ�������һ��
   public void moveLeftAction() {
      currentOne.moveLeft();
      //�ж��Ƿ�Խ�硢�Ƿ��غ�
      if (outOfBounds() || coincide()) {
         currentOne.moveRight();
      }
   }

   //����һ���ķ�������һ��
   public void moveRightAction() {
      currentOne.moveRight();
      //�ж��Ƿ�Խ�硢�غ�
      if (outOfBounds() || coincide()) {
         currentOne.moveLeft();
      }
   }


   public static void main(String[] args) {
      //����һ�����ڶ���
      JFrame frame = new JFrame("����˹����");
      //������Ϸ���棬Ҳ������塣
      Tetris panel = new Tetris();
      //�����Ƕ�뵽������
      frame.add(panel);

      //���ô��ڿɼ�
      frame.setVisible(true);
      //���ô��ڳߴ�
      frame.setSize(810, 940);
      //���ô���λ�þ���
      frame.setLocationRelativeTo(null);
      //���ô��ڹر�ʱ������ֹ
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      //��Ϸ��Ҫ�߼���װ�ڷ�����
      panel.start();
   }
}
