package com.example.bloodbankapplicationf_2017065116.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bloodbankapplicationf_2017065116.DataModels.Member;
import com.example.bloodbankapplicationf_2017065116.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RegisterActivity extends AppCompatActivity {
    private EditText nameEt, cityEt, bloodGroupEt, ageEt, mobileEt;
    private Button submitButton;
    DatabaseReference reff;
    Member member;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameEt = findViewById(R.id.name);
        cityEt = findViewById(R.id.city);
        bloodGroupEt = findViewById(R.id.blood_group);
        ageEt = findViewById(R.id.age);
        mobileEt = findViewById(R.id.number);
        submitButton = findViewById(R.id.submit_button);
        member=new Member();
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists())
                    maxid=(datasnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, city, blood_group, password, number;
                name = nameEt.getText().toString();
                city = cityEt.getText().toString();
                blood_group = bloodGroupEt.getText().toString();
                password = ageEt.getText().toString();
                number = mobileEt.getText().toString();
                member.setName(name);
                member.setCity(city);
                member.setBloodGroup(blood_group);
                member.setPassword(password);
                member.setMobileNumber(number);
                //reff.child("member1").setValue(member);
                reff.child(String.valueOf(maxid+1)).setValue(member);

                showMessage(name+"\n"+city+"\n"+blood_group+"\n"+password+"\n"+number+"\n data inserted successfully ");

                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }
    private void showMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
