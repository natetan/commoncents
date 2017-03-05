package edu.washington.ischool.commoncents.commoncents;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by iguest on 3/4/17.
 */

public class SplitByItem extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    String[] dataset = {"one", "two", "third", "fourth"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.split_by_item);
        mRecyclerView = (RecyclerView) findViewById(R.id.item_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new ItemsListAdapter(dataset);
        mRecyclerView.setAdapter(adapter);

    }
}
