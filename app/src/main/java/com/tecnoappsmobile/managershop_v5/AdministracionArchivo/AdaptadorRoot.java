package com.tecnoappsmobile.managershop_v5.AdministracionArchivo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tecnoappsmobile.managershop_v5.R;

import java.util.ArrayList;

/**
 * Created by danie on 08/12/2016.
 */

public class AdaptadorRoot extends BaseAdapter {
    private Context contexto;
    private ArrayList<String> root;

    public AdaptadorRoot(Context contexto, ArrayList<String> root){
        this.contexto=contexto;
        this.root=root;
    }

    @Override
    public int getCount() {
        return root.size();
    }

    @Override
    public String getItem(int i) {
        return root.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View layout;
        if(view==null){
            LayoutInflater layoutInflater=(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.layout_explorer_cards_icono,viewGroup,false);
        }
        TextView textRoot=(TextView) view.findViewById(R.id.nomTarjeta);
        textRoot.setText(getItem(i));
        return view;
    }
}
