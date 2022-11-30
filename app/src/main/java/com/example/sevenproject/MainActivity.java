package com.example.sevenproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    String etName="Sr";
    String etEmail="Sr";
    public void onClick1(View view)
    {
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra("name", etName);
        // в ключ gift пихаем текст из второго текстового поля
        intent.putExtra("gift", etEmail);
        startActivity(intent);
    }
}