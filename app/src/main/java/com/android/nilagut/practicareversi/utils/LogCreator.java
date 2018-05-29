package com.android.nilagut.practicareversi.utils;

import java.io.Serializable;
import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.android.nilagut.practicareversi.R;

public class LogCreator implements Serializable {

    private final boolean timeActive;
    private String log = "";
    private String time;
    private String size;

    private LogCreator(Activity ac) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ac);
        timeActive = prefs.getBoolean(ac.getString(R.string.time_key), false);
        size = prefs.getString(ac.getString(R.string.SizeOfTheGrid), "No s'ha pogut obtenir");
        time = new SimpleDateFormat("hh:mm:ss").format(new Date());
        log += "Alias: " + prefs.getString(ac.getString(R.string.user_key), "Nom invàlid") + "\n"
                + "Grid Size: " + size + "\n";
        if (timeActive) {
            log += "Time Enabled";
        } else {
            log += "Time Diasbled";

        }
    }

    private String logValues(GameBoard gameBoard, Integer position){

        if(gameBoard.getTurn()==1){
            log += "Turn 1" + "\n";
        } else if (gameBoard.getTurn()==2){
            log += "Turn 2" + "\n";
        }

        log += "Position selected: " + String.valueOf(position / Integer.valueOf(size)) + "," +
                String.valueOf(position / Integer.valueOf(size)) + "\n";

        log += "Remaining positions: " + String.valueOf(Integer.valueOf(size)* Integer.valueOf(size) -
                gameBoard.getPositionsComputer().size() - gameBoard.getPositionsUser().size())
                + "\n";

        log += "Remaining time: " + String.valueOf(gameBoard.getTime() / Variables.SEGON) +
                " secs. \n";

        return log;
    }
}




