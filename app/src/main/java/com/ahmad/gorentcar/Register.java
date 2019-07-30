package com.ahmad.gorentcar;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Register extends AppCompatActivity {
    ImageView imgUserPhoto;
    ImageView imgUserPhot;
    static int  PReqCode =1;
    static int  REQUESTCODE =1;
    Uri PickImage;

    private EditText userEmail,userPassword,userPassword2,userName,userAlamat;
    private ProgressBar LoadingProgress;
    private Button regBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //inu view
        userName = findViewById(R.id.editTextNamaLengkap);
        userEmail = findViewById(R.id.editTextEmail);
        userPassword = findViewById(R.id.edittextpassword);
        userPassword2 = findViewById(R.id.edittextconfirmpassword);
        LoadingProgress = findViewById(R.id.ProgressLoadingBar);
        userAlamat = findViewById(R.id.editTextAlamat);
        regBtn = findViewById(R.id.regbutton);
        LoadingProgress.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                regBtn.setVisibility(View.INVISIBLE);
                LoadingProgress.setVisibility(View.INVISIBLE);
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPassword2.getText().toString();
                final String name = userName.getText().toString();
                final String alamat = userAlamat.getText().toString();

                if (email.isEmpty() || name.isEmpty() || password.isEmpty() || alamat.isEmpty() || !password2.equals(password)) {

                    showMessage("Tolong Lengkapi Data Di Atas!");
                    regBtn.setVisibility(View.VISIBLE);
                    LoadingProgress.setVisibility(View.INVISIBLE);

                }
                else {

                    CreateUserAccount(email,name,password,alamat);
                }









            }
        });

        imgUserPhoto = findViewById(R.id.imgUserPhoto);
        imgUserPhot = findViewById(R.id.imgUserPhot);
        imgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >= 22){
                    checkAndRequestForPermission();
                }
                else
                {
                    openGallery();
                }

            }
        });
    }

    private void CreateUserAccount(String email, final String name, String password, final String alamat) {
        // method email dan password

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            //user berhasil register

                            showMessage("Akun Berhasil Dibuat");
                            updateUserInfo(name,PickImage,alamat, mAuth.getCurrentUser());

                        }
                        else {
                            //akun tidak berhasil di buat
                            showMessage("Membuat Akun Gagal!" + task.getException().getMessage());
                            regBtn.setVisibility(View.VISIBLE);
                            LoadingProgress.setVisibility(View.INVISIBLE);
                        }
                    }
                });


    }

    //update user photo and name
    private void updateUserInfo(final String name, Uri pickImage, String alamat, final FirebaseUser currentUser) {

        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("Foto_User");
        final StorageReference imageFilePath = mStorage.child(PickImage.getLastPathSegment());
        imageFilePath.putFile(pickImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                //Foto Upload Sukses

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();

                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            //info user upload sukses
                                            showMessage("Register Berhasil");
                                            updateUI();
                                        }
                                    }
                                });
                    }
                });
            }
        });

    }

    private void updateUI() {

        Intent MainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(MainActivity);
        finish();
    }

    //method tampil pesan
    private void showMessage(String s) {

        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

    }

    public void daftardua(View view) {
        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);


    }

    private void openGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESTCODE);
    }

    private void checkAndRequestForPermission() {
        if(ContextCompat.checkSelfPermission(Register.this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(Register.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(Register.this, "Please Accept",Toast.LENGTH_LONG).show();
            }

            else
            {
                ActivityCompat.requestPermissions(Register.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }

        }
       else
        {
            openGallery();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESTCODE && data != null){

            PickImage = data.getData();
            imgUserPhot.setImageURI(PickImage);

        }

    }
}
