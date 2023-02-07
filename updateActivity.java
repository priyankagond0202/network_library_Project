package com.mainactivity2.sqliteproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class updateActivity extends AppCompatActivity {


    EditText title_input, author_input, pages_input;
    Button update_button, delete_button;
    String title, id, author, pages;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.book_title_txt2);
        author_input = findViewById(R.id.book_author_txt2);
        pages_input = findViewById(R.id.book_page_txt2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();


        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }
        update_button.setOnClickListener((View) -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(updateActivity.this);
            title = title_input.getText().toString().trim();
            author = author_input.getText().toString().trim();
            pages = pages_input.getText().toString().trim();

            myDB.updateData(id, title, author, pages);

        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            MyDatabaseHelper myDB = new MyDatabaseHelper(updateActivity.this);
              myDB.deleteOneRow(id);
                confirmDialog();
            }
        });
    }


        void getAndSetIntentData () {
            if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
                id = getIntent().hasExtra("id");
                title = getIntent().hasExtra("title");
                author = getIntent().hasExtra("author");
                pages = getIntent().hasExtra("id");

                title_input.setText(title);
                author_input.setText(author);
                title_input.setText(pages);

            } else {
                Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
            }
        }

        void confirmDialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("delete"+title+"?");
            builder.setMessage("Are you sure you want to delete "+title+"?");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(updateActivity.this);
                    myDB.deleteOneRow(id);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {

                }
            });
            builder.create().show();
        }

    }
