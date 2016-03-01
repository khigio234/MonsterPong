package com.demo01.pdkpro.bong_bay;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by CO on 3/1/2016.
 */
public class Panddle {
    private float panddleX;
    private float panddleY;
    private int Image;
    private View view;
    private int panddleSizeX;
    private int panddleSizeY;

    private Bitmap panddleModify;
    private Bitmap panddleResize;

    public Panddle(int x, int y,int image){
        this.panddleSizeX = x;
        this.panddleSizeY = y;
        this.Image = image;
    }
    public float getX(){
        return panddleX;
    }

    public float getY(){
        return panddleY;
    }

    public int getPanddleSizeX(){
        return panddleSizeX;
    }

    public int getPanddleSizeY(){
        return panddleSizeY;
    }
    public void setX(float x){
        this.panddleX = x;
    }
    public void setY(float y){
        this.panddleY = y;
    }

    public void setPanddleX(int height) {
        this.panddleSizeX = height;
    }

    public void setPanddleY(int width) {
        this.panddleSizeY = width;
    }

    public void setImage(int image) {
        Image = image;
    }

    public int getImage() {
        return Image;
    }

    public void setView(View view) {
        setBitmapFactory(view.getResources());
        setScaledBitmap(this.panddleSizeX,panddleSizeY,false);
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public void setBitmapFactory(Resources resources){
        this.panddleModify = BitmapFactory.decodeResource(resources, this.getImage());
    }

    public void setScaledBitmap(int width,int height,boolean fitler){
        this.panddleSizeX = width;
        this.panddleSizeY = height;
        this.panddleResize = Bitmap.createScaledBitmap(this.panddleModify, width, height, fitler);
    }

    public void setPanddleModify(Bitmap panddleModify) {
        this.panddleModify = panddleModify;
    }

    public Bitmap getPanddleModify() {
        return panddleModify;
    }
    public void DrawPanddle(Canvas canvas){
//        Toast.makeText(this.view.getContext(),this.x+"- "+this.y,Toast.LENGTH_SHORT).show();
        canvas.drawBitmap(this.panddleResize,this.panddleX,this.panddleY,null);
    }

    private void draw(Canvas canvas){
        //canvas.drawBitmap();
    }

    private void update(){

    }
}
