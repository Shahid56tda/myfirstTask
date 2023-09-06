package com.example.recyclerveiwanddatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.recyclerveiwanddatabase.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
  DatabaseReference mRef;



FirebaseDatabase mDatabase;
FirebaseStorage mStore;
Uri imageUri;
ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mDatabase= FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference().child("Students");
        mStore=FirebaseStorage.getInstance();

        binding.addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,45);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==45 && resultCode==RESULT_OK){
            imageUri=data.getData();
            binding.addImg.setImageURI(imageUri);
        }
        binding.uploadData.setOnClickListener(v -> {
            String fn=binding.addName.getText().toString();
            String ln=binding.addNumber.getText().toString();

            if(!(fn.isEmpty() && ln.isEmpty() && imageUri!=null))

            {
                progressDialog = new ProgressDialog(this);

                progressDialog.setTitle("Data is uploading");
                progressDialog.show();

                StorageReference filepath=mStore.getReference().child("myFold").child(imageUri.getLastPathSegment());
                filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> downloadUrl=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                String t=task.getResult().toString();

                                DatabaseReference newpost=mRef.push();

                                newpost.child("name").setValue(fn);
                                newpost.child("number").setValue(ln);
                                newpost.child("image").setValue(task.getResult().toString());

                                progressDialog.dismiss();

                                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                                startActivity(intent);


                            }
                        });
                    }
                });


            }


        });


    }
}