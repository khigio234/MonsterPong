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
    public static int monsterComputer = 0;
    public static int monsterPlayer = 0;
    public static int lever = 2;

    public static ArrayList<Integer> arrMonster = new ArrayList<Integer>();
    public static ArrayList<Integer> arrMonsterLose = new ArrayList<Integer>();
    public static ArrayList<Integer> arrBackGround = new ArrayList<Integer>();
    public static ArrayList<Integer> arrMap = new ArrayList<Integer>();

    public static void init(){
        arrMonster.add(R.drawable.frog);
        arrMonster.add(R.drawable.wood);
        arrMonster.add(R.drawable.pink);
        arrMonster.add(R.drawable.star);
        arrMonster.add(R.drawable.robo);

        arrMonsterLose.add(R.drawable.sad_frog);
        arrMonsterLose.add(R.drawable.sad_wood);
        arrMonsterLose.add(R.drawable.sad_pink);
        arrMonsterLose.add(R.drawable.sad_star);
        arrMonsterLose.add(R.drawable.sad_robo);

        arrBackGround.add(R.drawable.map_00);
        arrBackGround.add(R.drawable.map_01);
        arrBackGround.add(R.drawable.map_02);
        arrBackGround.add(R.drawable.map_03);
        arrBackGround.add(R.drawable.map_04);

        arrMap.add(R.drawable.world_map0);
        arrMap.add(R.drawable.world_map1);
        arrMap.add(R.drawable.world_map2);
        arrMap.add(R.drawable.world_map3);
        arrMap.add(R.drawable.world_map4);
    }
}
