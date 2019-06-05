package com.example.bookofrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        getSupportActionBar().hide();
        imageView = findViewById(R.id.image_view_image_activity);
        Intent intent = getIntent();
        String picName = intent.getStringExtra("image_name");
        if (picName!= null){
            Picasso.get().load(picName).into(imageView);
        } else{
            Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
        }
    }
}
