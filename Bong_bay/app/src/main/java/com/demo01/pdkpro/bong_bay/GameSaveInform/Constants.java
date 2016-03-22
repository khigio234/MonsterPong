package com.demo01.pdkpro.bong_bay.GameSaveInform;

import com.demo01.pdkpro.bong_bay.R;

import java.util.ArrayList;

/**
 * Created by pdkpro on 17/03/2016.
 */
public class Constants {
    public static boolean isWin = true;
    public static boolean isSound = true;
    public static boolean isMusic = true;
    public static int monsterPlayer = 0;
    public static int level = 0;

    public static ArrayList<Integer> ARR_MONSTER = new ArrayList<Integer>();
    public static ArrayList<Integer> ARR_MONSTER_LOSE = new ArrayList<Integer>();
    public static ArrayList<Integer> ARR_BACKGROUND = new ArrayList<Integer>();
    public static ArrayList<Integer> ARR_MAP = new ArrayList<Integer>();

    public static void addImage(ArrayList<Integer> arr, int a, int b, int c, int d, int e){
        arr.add(a);
        arr.add(b);
        arr.add(c);
        arr.add(d);
        arr.add(e);
    }

    public static void init(){

        addImage(ARR_MONSTER, R.drawable.frog, R.drawable.wood, R.drawable.pink, R.drawable.star, R.drawable.robo);
        addImage(ARR_MONSTER_LOSE, R.drawable.sad_frog, R.drawable.sad_wood, R.drawable.sad_pink, R.drawable.sad_star, R.drawable.sad_robo);
        addImage(ARR_BACKGROUND, R.drawable.map_00, R.drawable.map_01, R.drawable.map_02, R.drawable.map_03, R.drawable.map_04);
        addImage(ARR_MAP, R.drawable.world_map0, R.drawable.world_map1, R.drawable.world_map2, R.drawable.world_map3, R.drawable.world_map4);

    }
}
