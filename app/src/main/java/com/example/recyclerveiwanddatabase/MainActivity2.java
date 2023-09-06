package com.example.recyclerveiwanddatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.recyclerveiwanddatabase.databinding.ActivityMain2Binding;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
ActivityMain2Binding binding;
    DatabaseReference mRef;
    FirebaseDatabase mDatabase;
    FirebaseStorage mStore;
ArrayList<ModelClass> mlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mDatabase= FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference().child("Students");
        mStore=FirebaseStorage.getInstance();

        mlist=new ArrayList<>();
       LinearLayoutManager layoutManager=new LinearLayoutManager(this);

       AdaptorClass adaptorClass=new AdaptorClass(MainActivity2.this,mlist);

       binding.R1.setLayoutManager(layoutManager);
       binding.R1.setAdapter(adaptorClass);

       mRef.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
               ModelClass modelClass=snapshot.getValue(ModelClass.class);
               mlist.add(modelClass);
               adaptorClass.notifyDataSetChanged();
           }

           @Override
           public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

           }

           @Override
           public void onChildRemoved(@NonNull DataSnapshot snapshot) {

           }

           @Override
           public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });





    }
}