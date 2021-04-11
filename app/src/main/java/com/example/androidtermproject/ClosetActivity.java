package com.example.androidtermproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

public class ClosetActivity extends AppCompatActivity {

    LinearLayout page_top;
    LinearLayout page_pants;
    LinearLayout page_outer;
    LinearLayout page_shoes;

    String using_category;
    String using_detail;
    String using_color;
    String using_style;

    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_closet);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getString("id");

        page_top = (LinearLayout)findViewById(R.id.page_top);
        page_pants = (LinearLayout)findViewById(R.id.page_pants);
        page_outer = (LinearLayout)findViewById(R.id.page_outer);
        page_shoes = (LinearLayout)findViewById(R.id.page_shoes);

        Button btn_add_cloth = (Button)findViewById(R.id.btn_register_cloth);

        Button btn_top = (Button)findViewById(R.id.btn_top);
        Button btn_pants = (Button)findViewById(R.id.btn_pants);
        Button btn_outer = (Button)findViewById(R.id.btn_outer);
        Button btn_shoes = findViewById(R.id.btn_shoes);
        Button btn_favorite = (Button)findViewById(R.id.btn_favorite);

        Button check_top = (Button)findViewById(R.id.check_top);
        Button check_pants = (Button)findViewById(R.id.check_pants);
        Button check_outer = (Button)findViewById(R.id.check_outer);
        Button check_shoes = (Button)findViewById(R.id.check_shoes);

        final Spinner spinner_color_top = (Spinner)findViewById(R.id.spinner_color_top);
        final Spinner spinner_type_top = (Spinner)findViewById(R.id.spinner_type_top);
        final Spinner spinner_style_top = (Spinner)findViewById(R.id.spinner_style_top);
        final Spinner spinner_color_pants = (Spinner)findViewById(R.id.spinner_color_pants);
        final Spinner spinner_type_pants = (Spinner)findViewById(R.id.spinner_type_pants);
        final Spinner spinner_style_pants = (Spinner)findViewById(R.id.spinner_style_pants);
        final Spinner spinner_color_outer = (Spinner)findViewById(R.id.spinner_color_outer);
        final Spinner spinner_type_outer = (Spinner)findViewById(R.id.spinner_type_outer);
        final Spinner spinner_style_outer = (Spinner)findViewById(R.id.spinner_style_outer);
        final Spinner spinner_color_shoes = (Spinner)findViewById(R.id.spinner_color_shoes);
        final Spinner spinner_type_shoes = (Spinner)findViewById(R.id.spinner_type_shoes);
        final Spinner spinner_style_shoes = (Spinner)findViewById(R.id.spinner_style_shoes);


        final String[] cloth_top = getResources().getStringArray(R.array.cloth_top_top);
        final String[] outer = getResources().getStringArray(R.array.cloth_top_outer);
        final String[] cloth_pants = getResources().getStringArray(R.array.cloth_pants);
        final String[] shoes = getResources().getStringArray(R.array.shoes);
        final String[] colors = getResources().getStringArray(R.array.color);
        final String[] styles = getResources().getStringArray(R.array.style);
        final String[] every = getResources().getStringArray(R.array.all);


        GridView cloth_grid = findViewById(R.id.cloth_grid);

        cloth_grid.setAdapter(new ClothAdapter(this));

        cloth_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(),"clicked position : " + i,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),ClothInformation.class);

                intent.putExtra("Index",i);

                startActivity(intent);

            }
        });

        ArrayAdapter<String> color_adapter = new ArrayAdapter<String>(getBaseContext(),R.layout.support_simple_spinner_dropdown_item,colors);
        color_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_color_top.setAdapter(color_adapter);

        spinner_color_top.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String color;
                color = spinner_color_top.getSelectedItem().toString();
                using_color = color;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<String> cloth_top_adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, cloth_top);
        cloth_top_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_type_top.setAdapter(cloth_top_adapter);

        spinner_type_top.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String detail;
                detail = spinner_type_top.getSelectedItem().toString();
                using_detail = detail;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<String> style_adapter = new ArrayAdapter<String>(getBaseContext(),R.layout.support_simple_spinner_dropdown_item,styles);
        style_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_style_top.setAdapter(style_adapter);

        spinner_style_top.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String detail;
                detail = spinner_style_top.getSelectedItem().toString();
                using_style = detail;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //////////////////////////////////////////////////////////////////

        spinner_color_pants.setAdapter(color_adapter);

        spinner_color_pants.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String color;
                color = spinner_color_pants.getSelectedItem().toString();
                using_color = color;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<String> cloth_pants_adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, cloth_pants);
        cloth_pants_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_type_pants.setAdapter(cloth_pants_adapter);

        spinner_type_pants.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String detail;
                detail = spinner_type_pants.getSelectedItem().toString();
                using_detail = detail;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_style_pants.setAdapter(style_adapter);

        spinner_style_pants.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String detail;
                detail = spinner_style_pants.getSelectedItem().toString();
                using_style = detail;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /////////////////////////////////////////////////////////////////////

        spinner_color_outer.setAdapter(color_adapter);

        spinner_color_outer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String color;
                color = spinner_color_outer.getSelectedItem().toString();
                using_color = color;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<String> outer_adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, outer);
        outer_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_type_outer.setAdapter(outer_adapter);

        spinner_type_outer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String detail;
                detail = spinner_type_outer.getSelectedItem().toString();
                using_detail = detail;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_style_outer.setAdapter(style_adapter);

        spinner_style_outer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String detail;
                detail = spinner_style_outer.getSelectedItem().toString();
                using_style = detail;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /////////////////////////////////////////////////////////////////////

        spinner_color_shoes.setAdapter(color_adapter);

        spinner_color_shoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String color;
                color = spinner_color_shoes.getSelectedItem().toString();
                using_color = color;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<String> shoes_adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, shoes);
        shoes_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_type_shoes.setAdapter(shoes_adapter);

        spinner_type_shoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String detail;
                detail = spinner_type_shoes.getSelectedItem().toString();
                using_detail = detail;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_style_shoes.setAdapter(style_adapter);

        spinner_style_shoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String detail;
                detail = spinner_style_shoes.getSelectedItem().toString();
                using_style = detail;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /////////////////////////////////////////////////////////////////////


        btn_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_visibility(0);
                using_category = "상의";
            }
        });
        check_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_visibility(4);
                Toast.makeText(getApplicationContext(), ""+using_category+"/"+using_detail+"/"+using_color+"/"+using_style,Toast.LENGTH_SHORT).show();
            }
        });

        btn_pants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_visibility(1);
                using_category = "하의";
            }
        });
        check_pants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_visibility(4);
            }
        });

        btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_visibility(4);
            }
        });

        btn_outer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_visibility(2);
                using_category = "아우터";
            }
        });
        check_outer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_visibility(4);
            }
        });

        btn_shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_visibility(3);
                using_category="신발";
            }
        });
        check_shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_visibility(4);
            }
        });


        btn_add_cloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterPictureActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    protected void change_visibility(int index){

        if(index==0){
            page_top.setVisibility(View.VISIBLE);
            page_pants.setVisibility(View.INVISIBLE);
            page_outer.setVisibility(View.INVISIBLE);
            page_shoes.setVisibility(View.INVISIBLE);
            page_top.bringToFront();
        }
        else if(index==1){
            page_top.setVisibility(View.INVISIBLE);
            page_pants.setVisibility(View.VISIBLE);
            page_outer.setVisibility(View.INVISIBLE);
            page_shoes.setVisibility(View.INVISIBLE);
            page_pants.bringToFront();
        }
        else if(index==2){
            page_top.setVisibility(View.INVISIBLE);
            page_pants.setVisibility(View.INVISIBLE);
            page_outer.setVisibility(View.VISIBLE);
            page_shoes.setVisibility(View.INVISIBLE);
            page_outer.bringToFront();
        }
        else if(index==3){
            page_top.setVisibility(View.INVISIBLE);
            page_pants.setVisibility(View.INVISIBLE);
            page_outer.setVisibility(View.INVISIBLE);
            page_shoes.setVisibility(View.VISIBLE);
            page_shoes.bringToFront();
        }

        else if(index==4){
            page_top.setVisibility(View.INVISIBLE);
            page_pants.setVisibility(View.INVISIBLE);
            page_outer.setVisibility(View.INVISIBLE);
            page_shoes.setVisibility(View.INVISIBLE);
        }

    }



}