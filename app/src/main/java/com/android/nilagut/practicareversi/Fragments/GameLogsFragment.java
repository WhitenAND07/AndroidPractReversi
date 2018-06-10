package com.android.nilagut.practicareversi.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.nilagut.practicareversi.R;


public class GameLogsFragment extends Fragment {

    TextView text;

    @Override
    public void onCreate(Bundle savedInsanceState){
        super.onCreate(savedInsanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInsatanceState){
        return inflater.inflate(R.layout.game_fragment_log, container, false);
    }

    public void mostrarLogs(String log){
        text = (TextView) getView().findViewById(R.id.Textlog);
        text.setText(log);
    }


}
