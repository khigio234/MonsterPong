package com.demo01.pdkpro.bong_bay.Game;

import android.content.Context;

import com.demo01.pdkpro.bong_bay.R;

/**
 * Created by pdkpro on 01/03/2016.
 */
public class Ball extends ObjectFather{
    float speedX,speedY;
    int velocity =10;
    SoundManager mSoundManager;

    public Ball(){

    }
    public Ball(int sizeX,int sizeY,int Image, float x, float y, Context context,float speedX,float speedY){
        super(sizeX,sizeY,Image,x,y,context);
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getSpeedY() {
        return speedY;
    }

    private boolean isCollisonPaddle(float ax, float ay, float aw, float ah, float bx, float by,float bw, float bh){
        return ax < bx+bw && ay < by+bh && bx < ax+aw && by < ay+ah;
    }

    public void update(Player player, AI ai) {

        // cap nhat vi tri cua ball
        this.setX(this.getX() + speedX);
        this.setY(this.getY() + speedY);
        // kiem tra banh co ra ngoai man hinh hay k, neu co thi bat nguoc lai
        if (0 > this.getX() || this.getX() + this.getSizeX() > this.getView().getWidth()) {
            float offset = speedX < 0 ? 0 - this.getX() : this.getView().getWidth() - (this.getX() + this.getSizeX());
            this.setX(this.getX() + 2 * offset);
            speedX *= -1;
        }

        if (speedY > 0) {
            processingCollisonPaddle(player, -1f);
        } else {
            processingCollisonPaddle(ai,1f);
        }
    }

    private void processingCollisonPaddle(ObjectFather obj, float flagNavigation){
        if(isCollisonPaddle(obj.getX(),obj.getY(), obj.getSizeX(), obj.getSizeY(),
            this.getX(), this.getY(), this.getSizeX(),this.getSizeY()))
        {
            float temp = obj.getY() + flagNavigation*obj.getSizeY();
            this.setY(temp);
            float n = (this.getX() + this.getSizeX() - obj.getX())/(obj.getSizeX() + this.getSizeX());
                float phi = (float)(0.25*Math.PI*(2*n-1));

                float smash = (float)Math.abs(phi) > (float)(0.2*Math.PI) ? (float)1.5 : 1;
                this.speedX = (int)(smash*velocity*Math.sin(phi));
                this.speedY = (int)(flagNavigation*smash*velocity*Math.cos(phi));
        }
    }
 //region Code_cu
/*
    public void update(Player player,AI ai,ArrayList<Boom> ArrBoom){
        //nếu không có gì xảy ra thì
        this.setX(this.getX()+this.getSpeedX());
        this.setY(this.getY()+this.getSpeedY());
        //trường hợp chay ra khỏi màn hình 2 bên trái phải màn hình
        if(this.getX() <0 || this.getX()+this.getSizeX() > this.getView().getWidth()){
            speedX = -speedX;
            this.setX(this.getX()<0 ? this.getxMin():this.getxMax()-this.getSizeX());
            this.playSong(0);
        }
        //trường hợp vượt quá lên trên hoặc xún dưới của màn hình
        if(this.getY() <0 || this.getY() + this.getSizeY() > this.getView().getHeight()){
            if(this.getY()<0){//win
                this.playSong(2);
            }else{//lose
                this.playSong(1);
            }
            //cho trai banh chet
            this.setLife(false);
        }
        //truong hợp va chamj voi các thanh trượt
        if(this.val>0){

        }
        //banh đang bay qua bên trên tức là player 2
        if(checkCollision(ai,-10)){
            xuLyVaCham(ai, true);
        }
        //banh đang bay qua bên trên tức là player 1
        else if(checkCollision(player, 10)){
            xuLyVaCham(player,false);
        }
    }

    public void xuLyVaCham(ObjectFather obj,boolean cfAI){
        //obj.PlaySong();
        if(cfAI){
            if(collisionBT(obj)){
                speedY = -speedY;
                this.setY(obj.getY()+this.getSizeY());
            } else if(collosionLR(obj)){
                speedX = -speedX;
            }
        }else{
            if(collisionBT(obj)){
                speedY = -speedY;
                this.setY(obj.getY()+10-this.getSizeY());
                //biet vi tri va cham voi ban gat
                if(this.getX()>obj.getX()+obj.getSizeX()/2){
                    //se set cho x tang
                    if(this.getspeedX()<0){//x hien tai dang giam
                        this.setspeedX(-this.getspeedX());
                    }
                }else{
                    //se set cho x giam
                    if(this.getspeedX()>0){//x hien tai dang tang
                        this.setspeedX(-this.getspeedX());
                    }
                }
            } else if(collosionLR(obj)){
                speedX = -speedX;
            }
        }
    }

    public boolean isCollision(float a_coordinate , int a_size, float b_coordinate, int b_size){
        return (a_coordinate + a_size >= b_coordinate || a_coordinate >= b_coordinate && a_coordinate <= a_coordinate + b_size;
    }
    //va cham tren duoi
    public boolean isCollision_Bottom_Top(ObjectFather obj){
        return isCollision(this.getX(), this.getSizeX(), obj.getX(), obj.getSizeX());
    }
    //va cham trai phai
    public boolean isCollosion_Left_Right(ObjectFather obj){
        return isCollision(this.getY(), this.getSizeY(), obj.getY(), obj.getSizeY());
    }
    //kiem tra va cham
    public boolean checkCollision(ObjectFather obj,int delta){
        return this.getX()+this.getSizeX() >= obj.getX() && this.getX()  <= obj.getX()+obj.getSizeX() && this.getY()+this.getSizeY()>=obj.getY()+delta&& this.getY() <= obj.getY()+obj.getSizeY()+delta;
    }
*/
//endregion
    //taoj nhac
    public void setSong(Context context){
        mSoundManager = new SoundManager();
        mSoundManager.initSounds(context);
        mSoundManager.addSound(0, R.raw.chamtuong);
        mSoundManager.addSound(1, R.raw.lose);
        mSoundManager.addSound(2, R.raw.win);
    }
    // phát nhạc
    public void playSong(int index){
        this.mSoundManager.playSound(index);
    }
    //stop Song
    public void StopSong(){
    }
}