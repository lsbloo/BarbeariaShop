package com.example.osvaldoairon.barbeariashop;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import com.example.osvaldoairon.barbeariashop.Model.Cliente;
import com.example.osvaldoairon.barbeariashop.Model.ClienteAdaptado;

public class Listdados extends AppCompatActivity {

    private ArrayList<Cliente> list_cliente;
    private Double somatorio_cabelo;
    private Double somatorio_barba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdados);
        somatorio_cabelo=0.0;
        somatorio_barba=0.0;


        list_cliente = new ArrayList<Cliente>();


        Intent at = getIntent();

        list_cliente = (ArrayList<Cliente>) at.getSerializableExtra("array");

        final int PADDING = 8;

        TextView txtHeader = new TextView(this);
        TextView txtFooter = new TextView(this);
        TextView txtFooter2 = new TextView(this);
        TextView txtFooter3 = new TextView(this);




        if(list_cliente != null) {
            somatorioK();
            final ClienteAdaptado n1 = new ClienteAdaptado(Listdados.this, list_cliente);
            ListView list = new ListView(Listdados.this);
            setContentView(list);
            list.setAdapter(n1);

            txtHeader.setBackgroundColor(Color.GRAY);
            txtHeader.setTextColor(Color.WHITE);
            txtHeader.setText(R.string.text_cabecalho);
            txtHeader.setPadding(PADDING,PADDING,0,PADDING);

            list.addHeaderView(txtHeader);

            txtFooter.setBackgroundColor(Color.GRAY);
            txtFooter.setTextColor(Color.WHITE);
            txtFooter.setText(R.string.text_cabecalho);
            txtFooter.setPadding(PADDING,PADDING,0,PADDING);

            txtFooter.setText(getResources().getQuantityString(R.plurals.texto_rodape,n1.getCount(),n1.getCount()));
            list.addFooterView(txtFooter);

            txtFooter2.setBackgroundColor(Color.GRAY);
            txtFooter2.setTextColor(Color.WHITE);
            txtFooter2.setText(String.valueOf("Total Lucro Corte de Cabelo R$: "+somatorio_cabelo));
            txtFooter2.setPadding(PADDING,PADDING,0,PADDING);
            list.addFooterView(txtFooter2);

            txtFooter3.setBackgroundColor(Color.GRAY);
            txtFooter3.setTextColor(Color.WHITE);
            txtFooter3.setText(String.valueOf("Total Lucro Barba R$: "+somatorio_barba));
            txtFooter3.setPadding(PADDING,PADDING,0,PADDING);

            list.addFooterView(txtFooter3);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Cliente cliente = (Cliente) parent.getItemAtPosition(position);
                    list_cliente.remove(cliente);

                    n1.notifyDataSetChanged();
                }
            });
        }
        else{

            Toast.makeText(this, "Cadastre pelo menos um cliente para atualizacao dos dados"  , Toast.LENGTH_SHORT).show();
        }
    }

    public void somatorioK(){
        for(int i = 0 ; i<list_cliente.size();i++){
            somatorio_cabelo += list_cliente.get(i).getValor_cabelo();
            somatorio_barba += list_cliente.get(i).getValor_barba();
        }
    }
}
