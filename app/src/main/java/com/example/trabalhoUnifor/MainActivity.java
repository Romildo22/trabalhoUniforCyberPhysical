package com.example.trabalhoUnifor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

     private FirebaseDatabase firebaseDatabase;
     private DatabaseReference databaseReference;

     TextView textConInd, textCodigo, txtConsumoCond, txtNomeCond, txtEndCond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textConInd = findViewById(R.id.idTxtIndResult);
        textCodigo = findViewById(R.id.idTxtIndMedioResult);
        txtConsumoCond = findViewById(R.id.idTxtGeralResult);
        txtEndCond = findViewById(R.id.txtEndCond);
        txtNomeCond = findViewById(R.id.txtNomeCond);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoadingActivity.class);
                startActivity(intent);
            }
        });

        inicializarFirebase();
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        downloadDados();
    }

    private void downloadDados(){

        DatabaseReference codigoInico = databaseReference.child("apartamentos").child("identificacao");
        DatabaseReference consumo = databaseReference.child("apartamentos").child("consumo");
        DatabaseReference condominioConsumo = databaseReference.child("gestao_de_agua_condomnio");
        DatabaseReference nomeCond = databaseReference.child("nome_condominio");
        DatabaseReference endCond = databaseReference.child("endereco_condominio");

        consumo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){

                textConInd.setText(Objects.requireNonNull(snapshot.getValue()).toString() + " litros");

                //Log.i("FIREBASE", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        codigoInico.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){

                textCodigo.setText(Objects.requireNonNull(snapshot.getValue()).toString());

                //Log.i("FIREBASE", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        condominioConsumo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                txtConsumoCond.setText(Objects.requireNonNull(snapshot.getValue()).toString() + " litros");

                //Log.i("FIREBASE", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        nomeCond.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                txtNomeCond.setText("Nome do condomínio : " + Objects.requireNonNull(snapshot.getValue()).toString());

                //Log.i("FIREBASE", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        endCond.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                txtEndCond.setText("Endereço do condomínio : " + Objects.requireNonNull(snapshot.getValue()).toString());

                //Log.i("FIREBASE", snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}