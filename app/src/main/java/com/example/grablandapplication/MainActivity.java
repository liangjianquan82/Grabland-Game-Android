package com.example.grablandapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.text.DynamicLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TableLayout courseTable;
    //private EditText courseNameEtext;
    //private Spinner academySpinner;
    private Button academyButton;
    private Button courseNameButton;
    private String academySpinnerValue;
    private String couresName;
    //private KCInteractiveWebHelper kh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        courseTable = (TableLayout) findViewById(R.id.kccx_course_table);
        //courseTable.removeAllViewsInLayout();




        int x = 0;
        for (int i = 0; i < 10; i++) {
            TableRow tablerow = new TableRow(this);
            for (int j = 0; j < 8; j++) {
                Button btn = new Button(this);
                x += 1;
                btn.setId(x);
                final int id_ =  btn.getId();
                btn.setText(String.valueOf(id_));

                btn.setOnClickListener(getOnClickDoSomething(btn));
                tablerow.addView(btn);
            }

            courseTable.addView(tablerow);

        }

    }



    View.OnClickListener getOnClickDoSomething(final Button button1)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                adshow(button1);
                button1.setBackgroundColor(Color.RED);
                button1.setText("O");
                int id = button1.getId();
                id= id+1;
                Button button = findViewById(id);
                button.setBackgroundColor(Color.GREEN);
            }
        };
    }

    private void adshow(final Button button1){
        final AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
        ad.setMessage(String .valueOf(button1.getId()))
                .create();
        ad.setPositiveButton("Close",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });

        ad.show();
    }


}