package com.example.osvaldoairon.barbeariashop.Model;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import com.example.osvaldoairon.barbeariashop.R;
/**
 * Created by osvaldoairon on 07/01/2018.
 */

public class ClienteAdaptado extends BaseAdapter {


    private Context ctx;
    private ArrayList<Cliente> clientes;
    public ClienteAdaptado(Context ctx, ArrayList<Cliente> cliente) {
        this.ctx=ctx;
        this.clientes=cliente;
    }

    @Override
    public int getCount() {
        return clientes.size();
    }

    @Override
    public Object getItem(int position) {
       return clientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cliente c1 = clientes.get(position);


        View linha = LayoutInflater.from(ctx).inflate(R.layout.activity_listdados,parent,false);



        TextView nomeCliente = (TextView)linha.findViewById(R.id.nomeCliente);
        TextView valorCabelo = (TextView)linha.findViewById(R.id.valorCabelo);
        TextView valorBarba = (TextView)linha.findViewById(R.id.valorBarba);

        Resources res = ctx.getResources();

        nomeCliente.setText("Nome: "+c1.getNome());
        valorCabelo.setText("Corte Cabelo: " + String.valueOf(c1.getValor_cabelo())+ " R$ ");
        valorBarba.setText("Barba: "+String.valueOf(c1.getValor_barba()) + " R$ ");
        return linha;
    }
}
