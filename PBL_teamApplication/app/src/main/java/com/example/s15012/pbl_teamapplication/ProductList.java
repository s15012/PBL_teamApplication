package com.example.s15012.pbl_teamapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProductList extends AppCompatActivity {

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item);
//        // アイテムを追加します
//        adapter.add("red");
//        adapter.add("green");
//        adapter.add("blue");
//        ListView listView = (ListView) findViewById(R.id.listView);
//        // アダプターを設定します
//        listView.setAdapter(adapter);
    }
}
