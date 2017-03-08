package edu.washington.ischool.commoncents.commoncents.activites1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.washington.ischool.commoncents.commoncents.DataRepository;
import edu.washington.ischool.commoncents.commoncents.R;

public class DeveloperSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_settings);

        initializeViews();
    }

    private void initializeViews() {
        findViewById(R.id.add_mock_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataRepository.getInstance().addMockData();
            }
        });

        findViewById(R.id.delete_mock_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataRepository.getInstance().deleteMockData();
            }
        });

        findViewById(R.id.clear_db).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataRepository.getInstance().clearDB();
            }
        });
    }
}
