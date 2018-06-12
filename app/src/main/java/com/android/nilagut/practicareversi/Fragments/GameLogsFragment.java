package com.android.nilagut.practicareversi.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.nilagut.practicareversi.R;
import com.android.nilagut.practicareversi.utils.LogCreator;



public class GameLogsFragment extends Fragment {

    String text;

    @Override
    public void onCreate(Bundle savedInsanceState){
        super.onCreate(savedInsanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInsatanceState){
        return inflater.inflate(R.layout.game_fragment_log, container, false);
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
    }


    public void mostrarLogs(String log){
        text = log;
        ((TextView) getView().findViewById(R.id.Textlog)).setText(log);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putString("log", text);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        ((TextView) getView().findViewById(R.id.Textlog)).setText(savedInstanceState.getString("log"));

    }


}