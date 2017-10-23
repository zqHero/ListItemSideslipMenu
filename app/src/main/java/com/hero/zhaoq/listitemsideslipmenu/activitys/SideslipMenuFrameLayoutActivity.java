package com.hero.zhaoq.listitemsideslipmenu.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hero.zhaoq.listitemsideslipmenu.adapters.MListAdapter;
import com.hero.zhaoq.listitemsideslipmenu.R;
import com.hero.zhaoq.listitemsideslipmenu.VirtualData;

public class SideslipMenuFrameLayoutActivity extends AppCompatActivity {

    private RecyclerView recycle_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sideslip_menu_frame_layout);

        recycle_view = findViewById(R.id.recycle_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycle_view.setLayoutManager(manager);
        recycle_view.setAdapter(new MListAdapter(this, VirtualData.getData()));
    }
}
