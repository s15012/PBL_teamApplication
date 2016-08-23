package com.example.s15012.pbl_teamapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MemberData helper = new MemberData(this);
        final SQLiteDatabase db = helper.getWritableDatabase();
        final TextView idText = (TextView) findViewById(R.id.editText);
        final TextView psText = (TextView) findViewById(R.id.editText2);

        //新規登録へ移動するボタンの処理
        Button entryButton = (Button) findViewById(R.id.reg_member_btn);
        entryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                regViewer();
            }
        });

        //ログイン処理
        Button login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Login_checker()) {
                    spData_set();
                    menuViewer();
                    Toast toast = Toast.makeText(MainActivity.this, "ログインに成功しました", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    err_View();
                }
            }




        });

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Button btn = (Button) findViewById(R.id.button);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, MemberRegist.class);
//                startActivity(intent);
//            }
//        });
//
//
//        Button menu_btn = (Button) findViewById(R.id.Menu_button);
//        menu_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, Menu.class);
//                startActivity(intent);
//            }
//        });
//    }

    }

    public boolean Login_checker() {
        MemberData db = new MemberData(MainActivity.this);
        SQLiteDatabase dbRead = db.getReadableDatabase();

        TextView idText = (EditText) findViewById(R.id.editText);
        TextView psText = (EditText) findViewById(R.id.editText2);
        String id = idText.getText().toString();
        String pass = psText.getText().toString();

        String where = MemberData.Columns.ID + "=? and " + MemberData.Columns.PASSWORD + "=?";
        String[] args = { id, pass };
        Cursor cursor = dbRead.query(
                MemberData.TABLE_NAME, null, where, args, null, null, null);

        if (cursor.moveToFirst()) {
            cursor.close();
            dbRead.close();
            return true;
        } else {
            cursor.close();
            dbRead.close();
            return false;
        }


    }

    //SharedPreferenceにデータをセット
    public void spData_set() {
        TextView idText = (TextView) findViewById(R.id.editText);
        TextView psText = (TextView) findViewById(R.id.editText2);
        String idData = idText.getText().toString();
        String passData = psText.getText().toString();
        SharedPreferences spData = getSharedPreferences("LoginData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spData.edit();
        editor.putString("idSave", idData);
        editor.putString("passSave", passData);
        editor.apply();

    }
    //メニュー画面へ遷移
    public void menuViewer() {
        Intent move_menuIntent = new Intent(MainActivity.this, Menu.class);
        startActivity(move_menuIntent);
    }
    //エラー表示
    public void err_View() {
        AlertDialog.Builder err_Login = new AlertDialog.Builder(MainActivity.this);
        err_Login.setTitle("ログインに失敗しました");
        err_Login.setMessage("IDかパスワードが間違っています。");
        err_Login.setPositiveButton("OK", null);
        err_Login.show();
    }
    //新規登録画面へ
    public void regViewer() {
        Intent moveEntry_Intent = new Intent(MainActivity.this, MemberRegist.class);
        startActivity(moveEntry_Intent);
    }

}

