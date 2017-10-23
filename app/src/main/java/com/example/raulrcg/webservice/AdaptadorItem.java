package com.example.raulrcg.webservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by raulrcg on 20/10/17.
 */

public class AdaptadorItem extends BaseAdapter{

    String nombres[];
    String direccion[];
    LayoutInflater inflater;
    Context context;

    public AdaptadorItem(Context context, String[] nombres,String[] dire){
        this.context=context;
        this.direccion=dire;
        this.nombres=nombres;
    }

    @Override
    public int getCount() {
        return nombres.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Declare Variables
        TextView nom;
        TextView dir;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.item, viewGroup, false);

        // Locate the TextViews in listview_item.xml
        nom = (TextView) itemView.findViewById(R.id.Nombre);
        dir = (TextView) itemView.findViewById(R.id.nosirve);

        // Capture position and set to the TextViews
        nom.append(nombres[i]);
        dir.append(direccion[i]);

        return itemView;
    }
}
