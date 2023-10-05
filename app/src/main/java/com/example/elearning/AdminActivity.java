package com.example.elearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    CardView kelas, mapel, bab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        kelas = findViewById(R.id.kelas);
        mapel = findViewById(R.id.mapel);
        bab = findViewById(R.id.bab);

        kelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ke = new Intent(AdminActivity.this, AkelasActivity.class);
                startActivity(ke);
            }
        });

        mapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ke = new Intent(AdminActivity.this, AmapelActivity.class);
                startActivity(ke);
            }
        });

        bab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ke = new Intent(AdminActivity.this, AbabActivity.class);
                startActivity(ke);
            }
        });
    }
}