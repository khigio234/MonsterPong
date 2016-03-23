package com.demo01.pdkpro.bong_bay.GamePlay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.demo01.pdkpro.bong_bay.GameSaveInform.Constants;
import com.demo01.pdkpro.bong_bay.R;
import com.demo01.pdkpro.bong_bay.ScreenControl.GamePlayScreen;

/**
 * Created by pdkpro on 01/03/2016.
 */
public class MainGame extends View {
    private Ball ball;
    private float x =50,y=700; //vi tri trai banh luc moi vao
    private float velocityX = 10,velocityY = 10;//van toc bay trai banh
    private Brick brick;
    private Player player;
    private Computer computer;
    private GamePlayScreen gamePlayScreen;
    private Impediment impediment;

    private Paint paint = new Paint();


    public MainGame(GamePlayScreen gamePlayScreen){
        super(gamePlayScreen.getBaseContext());
        this.setBackgroundResource(Constants.ARR_BACKGROUND.get(Constants.level));
        this.gamePlayScreen = gamePlayScreen;
        paint.setTextSize(30);
        paint.setColor(Color.RED);
    }
    @Override
    public void onDraw(Canvas canvas){
            if(isAiWin()){
                gamePlayScreen.resultScreen();
                Constants.isWin = false;
            }else if(isPlayerWin()) {
                gamePlayScreen.resultScreen();
                Constants.isWin = true;
                Constants.currentScore = computerCurrentScore();
                } else{
                    drawAll(canvas);
                    update();
                    try {
                        Thread.sleep(30);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                invalidate();
            }
    }

    public int computerCurrentScore(){
        int computerScore = Constants.currentScore+(Constants.stageScore*(Constants.level+1) - brick.getCountBrickRemove()*Constants.SCORE);
        return (computerScore > 0)? computerScore : 0;
    }

    public void drawScore(Canvas canvas){

    }

    private boolean isAiWin(){
        if(ball.getY() > this.getHeight())
            return true;
        return false;
    }

    private boolean isPlayerWin(){
        if(ball.getY() < 0)
            return true;
        return false;
    }

    public void update(){
        computer.update(ball,this);
        ball.update(player, computer);
        impediment.update(ball);
        //brick.update(ball);
    }

    public void drawAll(Canvas canvas){
        ball.draw(canvas);
        player.draw(canvas);
        computer.draw(canvas);

        //brick.draw(canvas);
        impediment.draw(canvas);
        //brick.draw(canvas);
        canvas.drawText("Score : "+computerCurrentScore(),10,50,paint);

    }

    public void init(int w,int h){
        // tao bong
        ball = new Ball(60,60,R.drawable.ball2,x,y,this.getContext(),velocityX,velocityY);
        ball.setView(this);
        // tao brick
        brick = new Brick(this.getContext());
        brick.init(w, h, this);
        //tao nguoi choi
        player = new Player(180, 60, Constants.ARR_MONSTER.get(Constants.monsterPlayer), w/3, (h-h/10), this.getContext());
        player.setView(this);
        //tao may
        computer = new Computer(180, 60, Constants.ARR_MONSTER.get(Constants.level), w/2,h/18, this.getContext());
        computer.setView(this);

        // tao chuong ngai vat
        impediment = new Impediment(this.getContext(), this);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        player.update(event,this);
        return true;
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
            init(w,h);
    }
}

