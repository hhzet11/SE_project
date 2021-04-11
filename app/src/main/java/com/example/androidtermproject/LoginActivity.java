package com.example.androidtermproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    EditText edit_id;
    EditText edit_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final Button btn_signUp = (Button)findViewById(R.id.btn_register);
        final Button btn_login = (Button)findViewById(R.id.btn_login);
        final Button btn_find_id = (Button)findViewById(R.id.find_id);
        final Button btn_find_password = (Button)findViewById(R.id.find_password);

        edit_id = (EditText)findViewById(R.id.edit_id);
        edit_password = (EditText)findViewById(R.id.edit_password);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btn_signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });

        btn_find_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FindIdActivity.class);
                startActivity(intent);
            }
        });

        btn_find_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FindPasswordActivity.class);
                startActivity(intent);
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readUser(edit_id.getText().toString());
            }
        });


    }

    private void readUser(final String ID) {
        mDatabase.child("users").child(ID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue(User.class) != null){
                    User post = dataSnapshot.getValue(User.class);
                    if(edit_id.getText().toString().equals(post.userID)) {
                        if(edit_password.getText().toString().equals(post.userPassword)) {
                            Intent intent = new Intent(getApplicationContext(),ChoiceActivity.class);
                            Bundle bundle = new Bundle();
                            String text_id = edit_id.getText().toString();
                            bundle.putString("id",text_id);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "<잘못된 패스워드입니다>", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "<등록되지않은 아이디입니다>", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
}
