package com.hero.zhaoq.listitemsideslipmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hero.zhaoq.listitemsideslipmenu.activitys.SideslipMenuFrameLayoutActivity;
import com.hero.zhaoq.listitemsideslipmenu.activitys.SideslipMenuLinearLayoutActivity;


/**
 * 侧滑  菜单   Demo
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.SideslipMenuFrameLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SideslipMenuFrameLayoutActivity.class));
            }
        });

        findViewById(R.id.SideslipMenuLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SideslipMenuLinearLayoutActivity.class));
            }
        });
    }

}
