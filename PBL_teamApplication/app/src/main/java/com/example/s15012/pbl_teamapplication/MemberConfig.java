package com.example.s15012.pbl_teamapplication;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MemberConfig extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_config);
        //アカウント情報の呼び出し
        setTextData();

        //変更ボタン
        Button editReg_btn = (Button) findViewById(R.id.editReg_button);
        editReg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                config_Checker();
            }
        });


        //キャンセルボタン　→　　メニュー画面へ
        Button editCancel_btn = (Button) findViewById(R.id.editCancel_button);
        editCancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuViewer();
            }
        });
    }

    //登録情報のセット
    private void setTextData() {
        ProfData PDdata = new ProfData();
        PDdata = searchData(PDdata);
        TextView nameText = (TextView) findViewById(R.id.editName);
        TextView addressText = (TextView) findViewById(R.id.editAddress);
        TextView passText = (TextView) findViewById(R.id.editPass);

        nameText.setText(PDdata.name);
        addressText.setText(PDdata.address);
        passText.setText(PDdata.password);


    }
    //DataBaseから登録情報をひっぱてくる
    private ProfData searchData(ProfData data) {
        MemberData db = new MemberData(MemberConfig.this);
        SQLiteDatabase dbWrite = db.getWritableDatabase();

        SharedPreferences spdata = getSharedPreferences("LoginData", Context.MODE_PRIVATE);
        String account_id = spdata.getString("idSave",null);

        String[] memData = {MemberData.Columns.NAME, MemberData.Columns.ADDRESS, MemberData.Columns.PASSWORD};
        String where = MemberData.Columns.ID + "=?";
        String[] args = { account_id };
        Cursor cursor = dbWrite.query(
                MemberData.TABLE_NAME, memData , where, args, null, null, null);
        while(cursor.moveToNext()) {
            data.name = cursor.getString(0);
            data.address = cursor.getString(1);
            data.password = cursor.getString(2);

        }
        return data;
    }

    private class ProfData {
        String name;
        String address;
        String password;
    }
    //変更チェック
    private void config_Checker() {
        String errMsg = "";
        AlertDialog.Builder alert = new AlertDialog.Builder(MemberConfig.this);

        TextView tvName = (TextView) findViewById(R.id.editName);
        String getName = tvName.getText().toString();
        TextView tvPostal = (TextView) findViewById(R.id.editAddress);
        String getPostal = tvPostal.getText().toString();
        TextView tvPass = (TextView) findViewById(R.id.editPass);
        String getPass = tvPass.getText().toString();

        //名前変更時の適正チェック
        if (getName.length() >= 25) {
            errMsg += ("名前: 25文字以内で入力してください。\n");
        } else if (getName.matches(".*[0-9].*")) {
            errMsg += ("名前: 数字はご利用できません。\n");
        }

        //住所変更時　適正チェック
        if (getPostal.length() >= 100) {
            errMsg += ("住所: 100文字以内で入力してください。\n");
        }

        //password変更時　適正チェック
        if (getPass.length() >= 32) {
            errMsg += ("パスワード: 32文字以内で入力してください。\n");

        } else if (!getPass.matches(".*[0-9a-zA-Z].*")) {
            errMsg += ("パスワード: 英数字以外はご利用できません。\n");
        }

        //空欄チェック & エラメ
        if (getName.equals("") || getPostal.equals("") || getPass.equals("")) {
            alert.setTitle("空欄があります");
            alert.setMessage("正しく入力してください");
            alert.setPositiveButton("OK", null);
            alert.show();
        } else if (!errMsg.equals("")) {
            alert.setTitle("ご利用できません");
            alert.setMessage(errMsg);
            alert.setPositiveButton("ok", null);
            alert.show();
        } else {
            get_AccountData();
            Toast.makeText(MemberConfig.this, "会員情報の変更に成功しました", Toast.LENGTH_LONG).show();
            menuViewer();

        }
    }
    //Menu画面へ
    private void menuViewer() {
        Intent moveMenu_intent = new Intent(MemberConfig.this, Menu.class);
        startActivity(moveMenu_intent);
    }
    //DataBaseのアカウント情報アップデート
    private void get_AccountData() {
        MemberData db = new MemberData(MemberConfig.this);
        SQLiteDatabase dbWrite = db.getWritableDatabase();
        SharedPreferences spdata = getSharedPreferences("LoginData", Context.MODE_PRIVATE);
        String account_id = spdata.getString("idSave",null);

        TextView tvName = (TextView) findViewById(R.id.editName);
        String getName = tvName.getText().toString();
        TextView tvAddress = (TextView) findViewById(R.id.editAddress);
        String getAddress = tvAddress.getText().toString();
        TextView tvPass = (TextView) findViewById(R.id.editPass);
        String getPass = tvPass.getText().toString();

        ContentValues newAccount = new ContentValues();
        newAccount.put(MemberData.Columns.NAME, getName);
        newAccount.put(MemberData.Columns.ADDRESS, getAddress);
        newAccount.put(MemberData.Columns.PASSWORD,getPass);

        String where = MemberData.Columns.ID + "=?";
        String[] args = { account_id };

        dbWrite.update(MemberData.TABLE_NAME,newAccount, where,args);
        db.close();

        SharedPreferences.Editor editor = spdata.edit();
        editor.putString("passSave", getPass);
        editor.apply();

    }


}





