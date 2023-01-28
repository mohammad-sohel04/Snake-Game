package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements  ActionListener, KeyListener {


private int [] snake_x_length=new int [750];
private int [] snake_y_length=new int [750];
private int lenofsnake=3;
private int score=0;


private int [] xpos={25,50,75,100,125,150,175,200,225,250,275,300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650,675,700,725,750,775,800,825,850};
private int [] ypos={75,100,125,150,175,200,225,250,275,300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};

private Random random=new Random();
private int enemyx ,enemyy;
    private boolean left=false;
    private boolean right=true;
    private boolean up=true;
    private boolean down=true;

    private int move=0;
    private boolean gameover=false;

    private ImageIcon snaketitle=new ImageIcon(getClass().getResource("snaketitle.jpg"));
    private ImageIcon leftmouth=new ImageIcon(getClass().getResource("leftmouth.png"));
   private ImageIcon enemy=new ImageIcon(getClass().getResource("enemy.png"));
   private ImageIcon downmouth=new ImageIcon(getClass().getResource("downmouth.png"));
   private ImageIcon upmouth=new ImageIcon(getClass().getResource("upmouth.png"));
   private ImageIcon snakeimage=new ImageIcon(getClass().getResource("snakeimage.png"));
   private ImageIcon rightmouth=new ImageIcon(getClass().getResource("rightmouth.png"));

private Timer timer;
private int delay=100;

  
   
   GamePanel(){
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(true);
   
    timer = new Timer(delay,  this);
   timer.start();
newenemy();
//    enemyx=
  }
  
  

    @Override
    public void paint(Graphics g) {
        super.paint(g);


        g.setColor(Color.white);
        g.drawRect(24,10,851,55) ;
        g.drawRect(24,74,851,576) ;


        snaketitle.paintIcon(this, g, 25, 11);

        g.setColor(Color.BLACK);
        
        g.fillRect(25,75,850,575);


if(move==0){
    snake_x_length[0]=100;
    snake_x_length[1]=75;
    snake_x_length[2]=50;

    snake_y_length[0]=100;
    snake_y_length[1]=100;
    snake_y_length[2]=100;
    // move++;

}


if(left){
    leftmouth.paintIcon(this, g, snake_x_length[0], snake_y_length[0]);
}
if(right){
    rightmouth.paintIcon(this, g, snake_x_length[0], snake_y_length[0]);
}
if(up){
    upmouth.paintIcon(this, g,snake_x_length[0], snake_y_length[0]);
}
if(down){
    downmouth.paintIcon(this, g, snake_x_length[0], snake_y_length[0]);
}

for(int i=1;i<lenofsnake;i++){

    snakeimage.paintIcon(this, g, snake_x_length[i], snake_y_length[i]);

}

enemy.paintIcon(this, g, enemyx, enemyy);

if(gameover){
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial",Font.BOLD,80));
    g.drawString("Game Over", 300, 300);


    g.setFont(new Font("Arial",Font.PLAIN,20));
    g.drawString("Press SPACE to Restart", 320, 350);


}

g.setColor(Color.WHITE);
g.setFont(new Font("Arial",Font.PLAIN,14));
g.drawString("Score : "+score, 750, 30);
g.drawString("Length : "+lenofsnake,750,50);
g.dispose();

}
@Override
public void actionPerformed(ActionEvent e) {

    for(int i=lenofsnake;i>0;i--){
        snake_x_length[i]=snake_x_length[(i-1)];
        snake_y_length[i]=snake_y_length[(i-1)];

    }
    if(left){
        snake_x_length[0]=snake_x_length[0]-25;

    }
    if(right){
        snake_x_length[0]=snake_x_length[0]+25;

    }


    if(up){
        snake_y_length[0]=snake_y_length[0]-25;

    }
    if(down){
        snake_y_length[0]=snake_y_length[0]+25;

    }

    if(snake_x_length[0]>850){
        snake_x_length[0]=25;
    }
    if(snake_x_length[0]<25){
        snake_x_length[0]=850;
    }

    if(snake_y_length[0]>625){
        snake_y_length[0]=75;
    }
    if(snake_y_length[0]<75){
        snake_y_length[0]=625;
    }

    collideswithenemy();
    colideseithbody();
    repaint();
}  





@Override
public void keyTyped(KeyEvent e) {
    
}

@Override
public void keyPressed(KeyEvent e) {

    if(e.getKeyCode()==KeyEvent.VK_SPACE){
        restart();
    }

    if(e.getKeyCode()==KeyEvent.VK_LEFT &&(!right)){
        left=true;
        right=false;
        up=false;
        down=false;
        move++;
    }
    if(e.getKeyCode()==KeyEvent.VK_RIGHT && (!left)){
        right=true;
        left=false;
        up=false;
        down=false;move++;
    }
    if(e.getKeyCode()==KeyEvent.VK_UP &&(!down)){
        right=false;
        left=false;
        up=true;
        down=false;move++;
    }
    if(e.getKeyCode()==KeyEvent.VK_DOWN &&(!up)){
        right=false;
        left=false;
        up=false;
        down=true;move++;
    }

    
    
}





@Override
public void keyReleased(KeyEvent e) {
    
}
      private void newenemy() {
        enemyx=xpos[random.nextInt(34)];
        enemyy=ypos[random.nextInt(ypos.length)];

        for(int i=lenofsnake-1;i>=0;i--){
            if(snake_x_length[i]==enemyx && snake_y_length[i]==enemyy){
                 newenemy();
            }
        }
}

private void collideswithenemy() {

    if(snake_x_length[0]==enemyx && snake_y_length[0]==enemyy){
        newenemy();
        lenofsnake++;

    score++;
    }
}
private void colideseithbody(){
   for(int i=lenofsnake;i>0;i--){
    if(snake_x_length[i]==snake_x_length[0] && snake_y_length[i]==snake_y_length[0]){
        timer.stop();

        gameover=true;
    }
   }
}
private void restart() {
    gameover=false;
    move=0;
    score=0;
    lenofsnake=3;
    left=false;
    right=true;
    up=false;
    down=false;
    timer.start();
    repaint();
    newenemy();

}


}
    



        

        

    
    

    

