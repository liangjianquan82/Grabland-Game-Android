package com.example.grablandapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.text.DynamicLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TableLayout courseTable;
    //private EditText courseNameEtext;
    //private Spinner academySpinner;
    private Button resetbt;
    private Button startbt;

    private TextView redtext;
    private TextView greentext;

    private RadioButton redradio;
    private RadioButton greenradio;

    private int bt_rows;
    private int bt_column;
    private int bt_totle;
    private int [] arraylist;
    private int nowcolor;
    private int setnb;
    //private KCInteractiveWebHelper kh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        resetbt = (Button) findViewById(R.id.resetbt);
        startbt = (Button) findViewById(R.id.startbt);

        redradio = (RadioButton) findViewById(R.id.redradio);
        greenradio = (RadioButton) findViewById(R.id.greenradio);

        //View view;
       // Spinner splitSpinner = (Spinner) view.findViewById(R.id.splitSpinner);
        //courseTable.removeAllViewsInLayout();
        resetbt.setOnClickListener(this);
        startbt.setOnClickListener(this);

    }

    public void CreateView(int rows,int column){
        courseTable = (TableLayout) findViewById(R.id.kccx_course_table);
        int x = 0;
        for (int i = 0; i < rows; i++) {
            TableRow tablerow = new TableRow(this);
            for (int j = 0; j < column; j++) {
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
    View.OnClickListener getOnClickDoSomething(Button button1)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                if(getbtcolor(button1)) {
                    adshow();
                }
                else {
                    if(redradio.isChecked())
                    {
                        nowcolor = Color.RED;
                        setnb = 1;
                        redradio.setChecked(false);
                        greenradio.setChecked(true);
                    }
                    else if(greenradio.isChecked()){
                        nowcolor = Color.GREEN;
                        setnb = 2;
                        redradio.setChecked(true);
                        greenradio.setChecked(false);
                    }
                    int id = button1.getId();
                    addcolor(id);
                    countRedandGreenNB();
                }
            }
        };
    }
    private void addinarraylist(int index,int setnb){
        arraylist[index-1] = setnb;
    }
    private boolean getbtcolor( final Button button1){
        boolean state = false;
        int index = button1.getId();
        int nub = arraylist[index-1];

        if(nub==1||nub==2){
            state = true;
        }
        else {
            state = false;
        }
        return state;
    }
    private void addcolor(int index){
        colorred(index);
        if(index<=bt_column){
             if(index==1){
                //右
                colorred(index+1);
                //下
                colorred(index+bt_column);
            }
            else if(index==bt_column){
                //下
                colorred(index+bt_column);
                //左
                colorred(index-1);

            }
            else{//左
                 colorred(index-1);
                 //下
                 colorred(index+bt_column);
                 //右
                 colorred(index+1);
            }

        }
        else if((bt_totle-bt_column)<=index||index==bt_totle){
            if((index+bt_column-1)==bt_totle){
                //上
                colorred(index-bt_column);
                //右
                colorred(index+1);
            }
            else if(index==bt_totle){
                //上
                colorred(index-bt_column);
                //左
                colorred(index-1);
            }
            else {
                //上
                colorred(index-bt_column);
                //左
                colorred(index-1);
                //右
                colorred(index+1);
            }
        }
        else{
            int x = index % bt_column;
            if (x == 0) {
                //上
                colorred(index-bt_column);
                //下
                colorred(index+bt_column);
                //左
                colorred(index-1);
            } else if (x == 1) {
                //上
                colorred(index-bt_column);
                //下
                colorred(index+bt_column);
                //右
                colorred(index+1);
            }else{
                //上
                colorred(index-bt_column);
                //下
                colorred(index+bt_column);
                //左
                colorred(index-1);
                //右
                colorred(index+1);
            }
        }
    }
    private void colorred(int index){
        Button button = findViewById(index);
        button.setBackgroundColor(nowcolor);
        button.setText("O");
        addinarraylist(index,setnb);
    }
    private void adshow(){
        final AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
        ad.setMessage("already colored").create();
        ad.setPositiveButton("Close",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });

        ad.show();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.resetbt:
                courseTable.removeAllViews();
                break;
            case R.id.startbt:
                bt_rows = 10;
                bt_column = 10;
                bt_totle = bt_rows*bt_column;
                arraylist = new int[bt_totle];
                CreateView(bt_rows, bt_column);
                break;

        }
    }
    private void countRedandGreenNB(){
        int rednb = 0;
        int greennb = 0;
        for(int i=0;i<arraylist.length;i++){
            if(arraylist[i]==1){
                rednb = rednb+1;
            }
            else if(arraylist[i]==2){
                greennb= greennb+1;
            }
        }
        redtext = (TextView) findViewById(R.id.redtext);
        greentext = (TextView) findViewById(R.id.greentext);
        redtext.setText(rednb);
        greentext.setText(greennb);
    }
}