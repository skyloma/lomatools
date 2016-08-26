package com.example.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lomasky.ma.xui.ArrayView;
import lomasky.ma.xui.Spinner;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private lomasky.ma.xui.Spinner spinner;
    private FloatingActionButton fab;
    private lomasky.ma.xui.ArrayView recycleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.recycleView = (ArrayView) findViewById(R.id.recycleView);

        recycleView.getProgressView().setVisibility(View.GONE);
        recycleView.getMoreProgressView().setVisibility(View.VISIBLE);
        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        this.spinner = (Spinner) findViewById(R.id.spinner);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        List<String> arr = new ArrayList<String>();
        arr.add("埼暮云春树 ");
        arr.add("埼暮云春树 ");
        arr.add("埼暮云春树埼暮云春树埼暮云春树埼暮云春树埼暮云春树 ");
        spinner.setList(arr);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
