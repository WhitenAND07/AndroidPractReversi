package com.android.nilagut.practicareversi.Fragments;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.nilagut.practicareversi.R;
import com.android.nilagut.practicareversi.utils.SQLite;

public class ListFragment extends Fragment {
    private GameListener listener;

    @Override
    public void onAttach(Activity ac) {
        super.onAttach(ac);
        try {
            listener = (GameListener) ac;
        } catch (ClassCastException e) {
            throw new ClassCastException(ac.toString() + " must implement GameListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.listado_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        SQLite database = SQLite.getInstance(getContext());
        ListView lstListado = (ListView) getView().findViewById(R.id.LstListado);
        lstListado.setAdapter(new GameAdapter(this, database));
        lstListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener != null) {
                    listener.onGameSelected(pos);
                }
            }
        });
    }

    public void setGameListener(GameListener listener) {
        this.listener = listener;
    }

    public interface GameListener {
        void onGameSelected(int pos);
    }


    private class GameAdapter extends BaseAdapter {

        Activity context;
        SQLite database;

        GameAdapter(ListFragment fragmentList, SQLite database) {
            this.context = fragmentList.getActivity();
            this.database = database;
        }

        @Override
        public int getCount() {
            return database.getDataFromDB().getCount();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_fragment, null);
            Cursor cursor = database.getDataFromDB();
            cursor.moveToPosition(position);
            TextView lblUser = (TextView) item.findViewById(R.id.DBUsername);
            lblUser.setText(cursor.getString(1));

            TextView lblTime = (TextView) item.findViewById(R.id.DBTime);
            lblTime.setText(cursor.getString(2));

            TextView lblPosition = (TextView) item.findViewById(R.id.DBPosition);
            lblPosition.setText(getString(Integer.valueOf(cursor.getString(9))));

            return (item);
        }
    }
}
