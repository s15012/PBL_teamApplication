package com.example.s15012.pbl_teamapplication;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class memberRegist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_regist);

        Button regist_btn = (Button)findViewById(R.id.Registration_button);

        regist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regChecker();
            }

            public void regChecker() {
                String errMsg = "";
                AlertDialog.Builder alert = new AlertDialog.Builder(memberRegist.this);
                AlertDialog.Builder err_id = new AlertDialog.Builder(memberRegist.this);

                TextView tvName = (TextView) findViewById(R.id.regName);
                String getName = tvName.getText().toString();

                TextView tvPostal = (TextView) findViewById(R.id.regLive);
                String getAddress = tvPostal.getText().toString();

                TextView tvId = (TextView) findViewById(R.id.regLoginID);
                String getId = tvId.getText().toString();

                TextView tvPass = (TextView) findViewById(R.id.regPass);
                String getPass = tvPass.getText().toString();

                    //名前入力適正チェック
                if (getName.length() >= 25) {
                    errMsg += ("名前: 25文字以内で入力してください。\n");
                } else if (getName.matches(".*[0-9].*")) {
                    errMsg += ("名前: 数字はご利用できません。\n");
                }

                    //住所入力適正チェック
                if (getAddress.length() >= 100) {
                    errMsg += ("住所: 100文字以内で入力してください。\n");
                }

                    //ID入力適正チェック
                if (getId.length() >= 20) {
                    errMsg += ("ID: 20文字以内で入力してください。\n");
                }
                else {
                    id_checker();
                }


                    //PASWORD入力適正チェック
                if (getPass.length() >= 32) {
                    errMsg += ("パスワード: 32文字以内で入力してください。\n");

                } else if (!getPass.matches(".*[0-9a-zA-Z].*")) {
                    errMsg += ("パスワード: 英数字以外はご利用できません。\n");
                }

                if (getName.equals("") || getId.equals("") || getAddress.equals("") || getPass.equals("")) {
                    alert.setTitle("空欄があります");
                    alert.setMessage("正しく入力してください");
                    alert.setPositiveButton("OK", null);
                    alert.show();
                }
                else if (!errMsg.equals("")) {
                    alert.setTitle("ご利用できません");
                    alert.setMessage(errMsg);
                    alert.setPositiveButton("ok", null);
                    alert.show();
                }
                else if (id_checker()) {
                    err_id.setTitle("ご利用できません");
                    err_id.setMessage("お使いのIDはすでに存在します");
                    err_id.setPositiveButton("OK", null);
                    err_id.show();
                }
                else{
                    memberData mD = new memberData(memberRegist.this);
                    SQLiteDatabase mDB = mD.getWritableDatabase();

                        //列に対応する値をセットする
                    ContentValues values = new ContentValues();
                    values.put(memberData.Columns.NAME, getName);
                    values.put(memberData.Columns.ADDRESS, getAddress);
                    values.put(memberData.Columns.ID, getId);
                    values.put(memberData.Columns.PASSWORD, getPass);

                        //データベースに行を追加する
                    long ret;
                    ret = mDB.insert(mD.TABLE_NAME, null, values);
                    if (!(ret == -1)) {
                        Log.d("DataBase", "行の追加に成功したよ");

                        Viewer();
                    }
                }
            }

            public void Viewer() {
                Toast toast = Toast.makeText(memberRegist.this, "登録に成功しました", Toast.LENGTH_LONG);
                toast.show();

                Intent Login_intent = new Intent(memberRegist.this, MainActivity.class);
                startActivity(Login_intent);

            }

            public boolean id_checker() {
                memberData db = new memberData(memberRegist.this);
                SQLiteDatabase dbRead = db.getReadableDatabase();

                TextView tvId = (TextView) findViewById(R.id.regLoginID);
                String getId = tvId.getText().toString();

                String where = memberData.Columns.ID + "=?";
                String [] args = { getId };
                Cursor cursor = dbRead.query(
                        memberData.TABLE_NAME, null, where, args, null, null, null);

                if(cursor.moveToFirst()){
                    cursor.close();
                    dbRead.close();
                    return true;
                }else{
                    cursor.close();
                    dbRead.close();
                    return false;
                }

            }
        });



        Button Cancel_btn = (Button)findViewById(R.id.Cancel_button);
        Cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(memberRegist.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
