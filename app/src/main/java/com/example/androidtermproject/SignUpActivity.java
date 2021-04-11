package com.example.androidtermproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    Button duplication_btn;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_sign_up);

        final EditText userName = (EditText)findViewById(R.id.name_input);
        final EditText userID = (EditText)findViewById(R.id.id_input);
        final EditText userPassword = (EditText)findViewById(R.id.password_input);
        final EditText userEmail = (EditText)findViewById(R.id.email_input);
        duplication_btn = (Button)findViewById(R.id.duplication_btn);
        Button register = (Button)findViewById(R.id.register);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        duplication_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readUser(userID.getText().toString());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUserName = userName.getText().toString();
                String getUserID = userID.getText().toString();
                String getUserPassword = userPassword.getText().toString();
                String getUserEmail = userEmail.getText().toString();

                HashMap result = new HashMap<>();
                result.put("name", getUserName);
                result.put("ID", getUserID);
                result.put("password", getUserPassword);
                result.put("email", getUserEmail);
                writeNewUser(getUserName, getUserID, getUserPassword, getUserEmail);
                //finish();
                Toast.makeText(getApplicationContext(),"회원가입완료",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

    private void writeNewUser(String userName, String userID, String userPassword, String userEmail) {
        User user = new User(userName, userID, userPassword, userEmail);

        mDatabase.child("users").child(userID).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(SignUpActivity.this, "데이터베이스에 저장완료", Toast.LENGTH_SHORT).show();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUpActivity.this, "데이터베이스에 저장실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void readUser(final String ID) {
        mDatabase.child("users").child(ID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue(User.class) != null){
                    User post = dataSnapshot.getValue(User.class);
                    if(ID.equals(post.userID)) {
                        Toast.makeText(getApplicationContext(), "<중복된 아이디입니다>", Toast.LENGTH_SHORT).show();
                    }
                } else {
                        Toast.makeText(getApplicationContext(), "<사용가능한 아이디입니다>", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
