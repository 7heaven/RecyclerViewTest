package com.heaven.application.recyclerviewtest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        layoutManager = new SnakeLayoutManager(this);

        adapter = new RecyclerView.Adapter<ListViewHolder>(){

            @Override
            public void onBindViewHolder(ListViewHolder vh, final int i){
                vh.content.setText("test:" + (i < 10 ? "00" : "") + (i >= 10 && i < 100 ? "0" : "") + i);
                vh.itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Toast.makeText(MainActivity.this, "position:" + i, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public ListViewHolder onCreateViewHolder(ViewGroup parent, final int i){

                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview, null, false);

                itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Toast.makeText(MainActivity.this, "position:" + i, Toast.LENGTH_LONG).show();
                    }
                });

                return new ListViewHolder(itemView);
            }

            @Override
            public int getItemCount(){
                return 60;
            }

        };

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this));

    }

    private class ListViewHolder extends RecyclerView.ViewHolder{

        TextView content;

        public ListViewHolder(View itemView){
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.tv);
        }

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
