package com.example.s15012.pbl_teamapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



            //会員情報の変更画面
        Button editReg_btn = (Button) findViewById(R.id.m_editRegist);
        editReg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editRegist_intent = new Intent(Menu.this, registration_config.class);
                startActivity(editRegist_intent);
            }
        });




            //退会画面
        Button Withdrawal_btn = (Button) findViewById(R.id.m_Withdrawal);
        Withdrawal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder wd_alert = new AlertDialog.Builder(Menu.this);
                wd_alert.setTitle("本当に退会してもよろしいですか？");
                wd_alert.setMessage("アカウントは削除されます。");
                wd_alert.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                /*



                                    TODO
                                    データベースのアカウント情報の削除



                                 */



                                Intent main_intent = new Intent(Menu.this, MainActivity.class);
                                startActivity(main_intent);

                            }
                        });

                wd_alert.setNegativeButton("キャンセル", null);
                wd_alert.show();
            }
        });





        //商品一覧ページへ移動
        Button goodsList_btn = (Button) findViewById(R.id.m_goodsList);
        goodsList_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                /*
                    TODO
                        商品一覧のページヘ移動
                */




            }
        });




        //注文状況確認のページヘ移動
        Button statusCheck_btn = (Button) findViewById(R.id.m_statusCheck);
        statusCheck_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                /*


                    TODO
                        注文状況確認ページヘ移動


                 */


            }
        });


        //ログアウト
        Button Logout_btn = (Button) findViewById(R.id.m_Logout);
        Logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                /*


                    TODO
                        ログアウト
                            プリファレンスが保持してるIDの削除・。・・？



                 */

            }
        });
    }
}
