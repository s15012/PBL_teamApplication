package com.example.s15012.pbl_teamapplication;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class registration_config extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_config);
        final Intent moveMenu_intent = new Intent(registration_config.this, Menu.class);

        /*

            TODO
            読み込み時に、データベースに保存されている情報を呼び出し

         */

        //変更ボタン
        Button editReg_btn = (Button) findViewById(R.id.editReg_button);
        editReg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                config_Checker();
            }

            public void config_Checker() {
                String errMsg = "";
                AlertDialog.Builder alert = new AlertDialog.Builder(registration_config.this);

                TextView tvName = (TextView) findViewById(R.id.editName);
                String getName = tvName.getText().toString();
                TextView tvPostal = (TextView) findViewById(R.id.editLive);
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
                    /*
                            TODO
                             変更時のデータベースの書き換え
                    */
//                    config_data();
                    menu_Viewer();
                }
            }

            public void menu_Viewer(){
                Toast toast = Toast.makeText(registration_config.this, "会員情報の変更に成功しました", Toast.LENGTH_LONG);
                toast.show();

                startActivity(moveMenu_intent);  //メニュー画面へ移動
            }

//            public void config_data() {
//                memberData db = new memberData(registration_config.this);
//                SQLiteDatabase DB = db.getWritableDatabase();
//
//                TextView tvName = (TextView) findViewById(R.id.regName);
//                String getName = tvName.getText().toString();
//
//                TextView tvPostal = (TextView) findViewById(R.id.regLive);
//                String getAddress = tvPostal.getText().toString();
//
//                TextView tvId = (TextView) findViewById(R.id.regLoginID);
//                String getId = tvId.getText().toString();
//
//                TextView tvPass = (TextView) findViewById(R.id.regPass);
//                String getPass = tvPass.getText().toString();
//
//                // 2. 更新する値をセット
//                ContentValues values = new ContentValues();
//
//                values.put(memberData.Columns.NAME, getName);
//                values.put(memberData.Columns.ADDRESS, getAddress);
//                values.put(memberData.Columns.ID, getId);
//                values.put(memberData.Columns.PASSWORD, getPass);
//
//        /*Log.d("updateProduct",
//                "ID = " + id_str + "\n" +
//                "NAME = " + name_str + "\n" +
//                "PRICE = " + price_str + "\n" +
//                "STOCK = " + stock_str);*/
//
//                // 3. 更新する行をWHEREで指定
//                String where = memberData.Columns._ID + "=?";
//                String [] args = { String.valueOf(memberData.Columns._ID)};
//
//                int count = DB.update(memberData.TABLE_NAME, values, where, args);
//                if(count == 0){
//                    Log.d("Edit", "Failed to update");
//                }
//
//                // 4. データベースを閉じる
//                db.close();
//            }
        });


        //キャンセルボタン　→　　メニュー画面へ
        Button editCancel_btn = (Button) findViewById(R.id.editCancel_button);
        editCancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(moveMenu_intent);  //メニュー画面へ移動
            }
        });
    }
}





