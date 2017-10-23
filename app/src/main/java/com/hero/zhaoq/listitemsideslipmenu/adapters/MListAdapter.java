package com.hero.zhaoq.listitemsideslipmenu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hero.zhaoq.listitemsideslipmenu.R;

import java.util.List;

/**
 * author: zhaoqiang
 * date:2017/10/18 / 17:03
 * zhaoqiang:zhaoq_hero@163.com
 */
public class MListAdapter extends RecyclerView.Adapter<MListAdapter.ViewHolder> {

    private final List<Integer> data;
    private Context context;

    public MListAdapter(Context context, List<Integer> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.framelayout_list_item, null, false);
        itemView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    //--------------
    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt;

        public ViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.txt);
        }

        public void setData(int position) {
            txt.setText(position + "=========");
        }
    }
}