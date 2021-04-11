package com.example.androidtermproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ClothInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth_information);

        Constants constants = new Constants();

        Button information_favorite_register_btn = (Button)findViewById(R.id.information_favorite_register_btn);
        Button information_favorite_release_btn = (Button)findViewById(R.id.information_favorite_release_btn);
        final TextView information_favorite_star = (TextView)findViewById(R.id.information_favorite_star);

        Button close_btn = (Button)findViewById(R.id.information_close_btn);

        Intent intent = getIntent();


        Bundle bundle = intent.getExtras();

        int index = bundle.getInt("Index");


        ImageView imageView = (ImageView)findViewById(R.id.information_cloth_picture);

        imageView.setImageResource(constants.mImages[index]);

        information_favorite_register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                information_favorite_star.setText(" ★");
                Toast.makeText(getApplicationContext(),"즐겨찾기 등록 완료",Toast.LENGTH_SHORT).show();

            }
        });

        information_favorite_release_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                information_favorite_star.setText(" ☆");
                Toast.makeText(getApplicationContext(),"즐겨찾기 해제 완료",Toast.LENGTH_SHORT).show();
            }
        });


        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
