package edu.washington.ischool.commoncents.commoncents.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.washington.ischool.commoncents.commoncents.Activies.SplitByItemActivity;
import edu.washington.ischool.commoncents.commoncents.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplitCostsFragment extends Fragment {


    public SplitCostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_split_costs, container, false);

        Button button = (Button) view.findViewById(R.id.split_by_items_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SplitByItemActivity.class));
            }
        });

        return view;
    }
}
