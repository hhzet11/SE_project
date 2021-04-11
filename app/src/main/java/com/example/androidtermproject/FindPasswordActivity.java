package com.example.androidtermproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

public class FindPasswordActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    EditText name, email, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_find_password);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        name = (EditText)findViewById(R.id.name_input);
        email = (EditText)findViewById(R.id.email_input);
        id = (EditText)findViewById(R.id.id_input);
        Button btn_find_password = (Button)findViewById(R.id.btn_find_password);
        Button btn_cancel = (Button)findViewById(R.id.btn_cancel);

        btn_find_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readUser();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void readUser() {
        mDatabase.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int state = 0;

                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    if(name.getText().toString().equalsIgnoreCase(ds.getValue(User.class).userName) &&
                            email.getText().toString().equals(ds.getValue(User.class).userEmail) &&
                                id.getText().toString().equals(ds.getValue(User.class).userID))
                    {
                        Toast.makeText(getApplicationContext(), "비밀번호:"+ds.getValue(User.class).userPassword, Toast.LENGTH_SHORT).show();
                        state = 1;
                        finish();
                    }
                }

                if(state == 0){
                    Toast.makeText(getApplicationContext(), "<이름, 이메일, 아이디를 확인해주세요>", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
