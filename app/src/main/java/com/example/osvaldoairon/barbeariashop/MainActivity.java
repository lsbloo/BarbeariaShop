package com.example.osvaldoairon.barbeariashop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btn_cabelo;
    private Button btn_barba;
    private ArrayList<String> lista_array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista_array = new ArrayList<String>();

        Intent at = getIntent();

        lista_array = (ArrayList<String>) at.getSerializableExtra("array");




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menunovo,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menu_novo){
            Intent at = new Intent(MainActivity.this,Cadcabelo.class);

            startActivity(at);
        }
        if(id == R.id.menu_list){
            Intent at = new Intent(MainActivity.this,Listdados.class);
            at.putStringArrayListExtra("array",lista_array);
            startActivity(at);
        }
        return super.onOptionsItemSelected(item);
    }
}
