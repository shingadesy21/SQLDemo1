package com.example.sqldemo1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btn_add,btn_delete,btn_update,btn_read;
EditText edt_name,edt_desc,edt_price;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_add=(Button)findViewById(R.id.button);
        btn_delete=(Button)findViewById(R.id.button2);
        btn_update=(Button)findViewById(R.id.button3);
        btn_read=(Button)findViewById(R.id.button4);
        edt_name=(EditText)findViewById(R.id.editText);
        edt_desc=(EditText)findViewById(R.id.editText2);
        edt_price=(EditText)findViewById(R.id.editText3);
        helper =new Helper(this);
        Adddata();
        getAll();
        updatedata();
        deletedata();

    }
    public  void Adddata()
    {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted=helper.insert(edt_name.getText().toString(),edt_desc.getText().toString(),edt_price.getText().toString());
                if (isInserted=true)
                    Toast.makeText(getApplicationContext(),"Data inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Data not inserted",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void getAll()
    {
        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=helper.getAllData();
                if(cursor.getCount()==0)
                {
                    showmsg("Error","No data found");
                    return;
                }
                StringBuffer stringBuffer=new StringBuffer();
                while (cursor.moveToNext())
                {
                    stringBuffer.append("Id :- "+cursor.getString(0)+"\n");
                    stringBuffer.append("Name :- "+cursor.getString(1)+"\n");
                    stringBuffer.append("Desc :- "+cursor.getString(2)+"\n");
                    stringBuffer.append("Price :- "+cursor.getString(3)+"\n");

                    /*String name=cursor.getString(0);
                    String desc=cursor.getString(1);
                    Double price=cursor.getDouble(2);
                    stringBuffer.append("Name:- "+name+"\nDescrption:- "+desc+"\nPrice:- "+price);*/
                    showmsg("Data",stringBuffer.toString());

                }
            }
        });
    }
    public void updatedata()
    {
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate=helper.updatedata(edt_name.getText().toString(),edt_desc.getText().toString(),edt_price.getText().toString());
                if (isUpdate==true)
                    Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Data not Updated",Toast.LENGTH_LONG).show();

            }
        });

    }
    public void deletedata()
    {
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleterow=helper.deletedata(edt_name.getText().toString());
                if(deleterow>0)
                    Toast.makeText(getApplicationContext(),"Record Delete",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Record Not Delete",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void showmsg(String titile,String msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titile);
        builder.setMessage(msg);
        builder.show();
    }

}
