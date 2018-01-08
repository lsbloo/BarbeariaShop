package com.example.osvaldoairon.barbeariashop;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.example.osvaldoairon.barbeariashop.Model.Cliente;
import com.example.osvaldoairon.barbeariashop.Model.MaskEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.widget.Button;

import java.io.Serializable;
import java.util.UUID;
import java.util.ArrayList;
public class Cadcabelo extends AppCompatActivity{



    private EditText edt_nome;
    private EditText edt_cabelo;
    private EditText edt_barba;
    private ListView list;
    private Button btn_salvar;
    private ArrayAdapter adapter;
    public FirebaseDatabase fireBaseDatabase;
    public DatabaseReference databaseReference;
    private ArrayList<Cliente> list_client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadcabelo);

        edt_nome=(EditText)findViewById(R.id.edt_nome);
        edt_cabelo=(EditText)findViewById(R.id.edt_valor_cabelo);
        edt_barba=(EditText)findViewById(R.id.edt_valorB);
        list_client = new ArrayList<Cliente>();

        iniciarFirebase();
        eventSnapShot();
        btn_salvar = (Button)findViewById(R.id.btn_salvar);

        edt_cabelo.addTextChangedListener(MaskEditText.mask(edt_cabelo,MaskEditText.FORMATO_DOUBLE));
        edt_barba.addTextChangedListener(MaskEditText.mask(edt_barba,MaskEditText.FORMATO_DOUBLE));

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edt_nome.getText().toString().isEmpty() || edt_cabelo.getText().toString().isEmpty() || edt_barba.getText().toString().isEmpty()){
                    Toast.makeText(Cadcabelo.this, "Preencha todos os campos !", Toast.LENGTH_SHORT).show();
                }
                else{
                    Cliente cliente = new Cliente();

                    cliente.setNome(edt_nome.getText().toString());
                    cliente.setValor_barba(Double.parseDouble(edt_barba.getText().toString()));
                    cliente.setValor_cabelo(Double.parseDouble(edt_cabelo.getText().toString()));
                    cliente.setId(UUID.randomUUID().toString());
                    databaseReference.child("Clientes").child(cliente.getId()).setValue(cliente);
                    limparCampos();
                    Toast.makeText(Cadcabelo.this, "Armazendado !", Toast.LENGTH_SHORT).show();

                    Intent at = new Intent(Cadcabelo.this,MainActivity.class);
                    at.putExtra("array", list_client);
                    startActivity(at);
                }
            }
        });


    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    public void eventSnapShot() {

        databaseReference.child("Clientes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list_client.clear();


                for (DataSnapshot obj : dataSnapshot.getChildren()) {

                    Cliente jogador = (Cliente) obj.getValue(Cliente.class);
                    list_client.add(jogador);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void iniciarFirebase(){
        FirebaseApp.initializeApp(this);
        fireBaseDatabase = fireBaseDatabase.getInstance();
        //fireBaseDatabase.setPersistenceEnabled(true);
        databaseReference = fireBaseDatabase.getReference();


    }



    public boolean limparCampos(){
        edt_nome.setText("");
        edt_cabelo.setText("");
        edt_barba.setText("");

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menudel,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_del){

            new AlertDialog.Builder(this).setTitle("Deletar Clientes").
                    setMessage("Tem certeza que deseja deletar todos os clientes? ").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(Cadcabelo.this, "Removidos !", Toast.LENGTH_SHORT).show();
                    databaseReference.child("Clientes").removeValue();
                }
            }).setNegativeButton("Nao", null).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
