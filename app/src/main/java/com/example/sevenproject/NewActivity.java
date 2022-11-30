package com.example.sevenproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {
    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {       // конструктор суперкласса
            super(context, "myDB", null, 1);
        }
        public void onCreate(SQLiteDatabase db) {
            // создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "time text" + ");");
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
    String[] data = {"a", "b", "c", "d", "e", "f", "g", "h",
            "i", "j", "k"};

    GridView gvMain;
    ArrayAdapter<String> adapter;
    DBHelper dbHelper = new DBHelper(this);

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //Cursor c = db.query("mytable", null, null, null, null, null, null);
        //String query="Truncate table DISPATCHES";
        //db.delete("mytable",null,null);
        //( name, String whereClause, String [ ] whereArgs)
        //cv.put("name", "suslik");       cv.put("time", "09.02.2002");
        //cv.put("name", "muslik");       cv.put("time", "23.12.2002");
        //cv.put("name", "guslik");       cv.put("time", "09.02.2002");
        //cv.put("name", "tuslik");       cv.put("time", "09.02.2002");
        //db.update("mytable",,"name",null);
        //cv.put("name", "ruslik");       cv.put("time", "09.02.2002");
        cv.put("name", "Иванов Иван Иванович");       cv.put("time", "09.02.2002");
        long rowID = db.insert("mytable", null, cv);
        String name=getIntent().getStringExtra("name");
        String email=getIntent().getStringExtra("gift");
        Cursor c = db.query("mytable", null, null, null, null, null, null);

        // ставим позицию курсора на первую строку выборки       // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            int emailColIndex = c.getColumnIndex("time");
            int i=0;

            do {
                // получаем значения по номерам столбцов и пишем все в лог
                data[i]+=
                        " ID = " + c.getInt(idColIndex) + ", name = "
                                + c.getString(nameColIndex) + ", time = "
                                + c.getString(emailColIndex);
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false -
                // выходим из цикла
                i++;
            } while (c.moveToNext());       } else
        c.close();

        //data[0]+=" "+name+" "+email;
        adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.tvText, data);
        gvMain = (GridView) findViewById(R.id.gvMain);
        gvMain.setAdapter(adapter);
        adjustGridView();
    }

    private void adjustGridView() {
    }
}
