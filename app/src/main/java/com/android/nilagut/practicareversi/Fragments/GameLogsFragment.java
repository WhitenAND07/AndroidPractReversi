package com.android.nilagut.practicareversi.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.nilagut.practicareversi.R;


public class GameLogsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInsanceState){
        super.onCreate(savedInsanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInsatanceState){
        return inflater.inflate(R.layout.game_fragment_log, container, false);
    }

    public void mostrarLogs(String log){
        ((TextView) getView().findViewById(R.id.Textlog)).setText(log);
    }
}
