package edu.washington.ischool.commoncents.commoncents;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by iguest on 3/4/17.
 */

public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ViewHolder> {
    private String[] dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public ViewHolder(TextView textView) {
            super(textView);
            tv = textView;
        }
    }

    public ItemsListAdapter(String[] dataset) {
        this.dataSet = dataset;
    }

    @Override
    public ItemsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView view = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemsListAdapter.ViewHolder holder, int position) {
        holder.tv.setText(dataSet[position]);
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }
}
