package com.example.gymapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    TextView adminTitle;
    EditText enterClassType, enterClassDescription, enterDeleteUser;
    Button addClassBTN, deleteClassBTN, viewClassesBTN, deleteUserBTN, updateClassBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper DBHelper = new DBHelper(AdminActivity.this);

        adminTitle = (TextView) findViewById(R.id.adminTitle);

        enterClassType = (EditText) findViewById(R.id.enterClassTypeText);
        enterClassDescription = (EditText) findViewById(R.id.enterClassDescriptionText);
        enterDeleteUser = (EditText) findViewById(R.id.enterDeleteUser);

        addClassBTN = (Button) findViewById(R.id.addClassBTN);
        deleteClassBTN = (Button) findViewById(R.id.deleteClassBTN);
        updateClassBTN = (Button) findViewById(R.id.deleteUserBTN);
        viewClassesBTN = (Button) findViewById(R.id.viewClassesBTN);
        deleteUserBTN = (Button) findViewById(R.id.deleteUserBTN);

        addClass();
        viewClass();
        deleteClass();
        updateClass();
        deleteUser();

    }
    public void deleteUser() {

        deleteUserBTN.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                DBHelper gymDB = new DBHelper(AdminActivity.this);

                Boolean deleteRows = gymDB.deleteUser(enterDeleteUser.getText().toString());
                if (deleteRows = true){
                    Toast.makeText(AdminActivity.this, "User was deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdminActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addClass() {

        addClassBTN.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                DBHelper gymDB = new DBHelper(AdminActivity.this);

                boolean isAdded = gymDB.addClass((enterClassType.getText().toString()), (enterClassDescription.getText().toString()));

                if (isAdded = true)
                    Toast.makeText(AdminActivity.this, "New Class Type is Added!", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(AdminActivity.this, "New Class Has Not Been Added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteClass() {

        deleteClassBTN.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                DBHelper gymDB = new DBHelper(AdminActivity.this);

                Integer deleteRows = gymDB.deleteClass(enterClassDescription.getText().toString());
            }
        });
    }

    public void updateClass() {

        updateClassBTN.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {

                DBHelper gymDB = new DBHelper(AdminActivity.this);
                boolean updateResult = gymDB.updateData((enterClassType.getText().toString()), (enterClassDescription.getText().toString()));


                if (updateResult = true)
                    Toast.makeText(AdminActivity.this, "New Class is Updated!", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(AdminActivity.this, "New Class Did Not Update!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void viewClass() {

        viewClassesBTN.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                DBHelper gymDB = new DBHelper(AdminActivity.this);
                Cursor results = gymDB.getViewClasses();

                if (results.getCount() == 0) {
                    Toast.makeText(AdminActivity.this, "Error! Nothing Found", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while (results.moveToNext()) {
                    buffer.append("Class Type: " + results.getString(0) + "\n");
                    buffer.append("Class Description: " + results.getString(1) + "\n");
                }

                showResults("Here Are The Class Types", buffer.toString());
            }
        });
    }

    public void showResults(String title, String Message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}


