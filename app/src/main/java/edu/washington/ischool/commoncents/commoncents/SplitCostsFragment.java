package edu.washington.ischool.commoncents.commoncents;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by iguest on 3/3/17.
 */

public class SplitCostsFragment extends android.support.v4.app.Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.split_costs_fragment, container, false);

        Button button = (Button) view.findViewById(R.id.split_by_items_button);
        final Intent intent = new Intent(getContext(), SplitByItem.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        return view;
    }
}
