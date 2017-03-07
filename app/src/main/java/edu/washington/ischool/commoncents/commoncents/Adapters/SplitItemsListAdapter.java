package edu.washington.ischool.commoncents.commoncents.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.washington.ischool.commoncents.commoncents.R;

/**
 * Created by keegomyneego on 3/4/17.
 */

public class SplitItemsListAdapter extends RecyclerView.Adapter<SplitItemsListAdapter.ViewHolder> {
    private String[] dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public ViewHolder(TextView textView) {
            super(textView);
            tv = textView;
        }
    }

    public SplitItemsListAdapter(String[] dataset) {
        this.dataSet = dataset;
    }

    @Override
    public SplitItemsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView view = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SplitItemsListAdapter.ViewHolder holder, int position) {
        holder.tv.setText(dataSet[position]);
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }
}
