package edu.washington.ischool.commoncents.commoncents.Activies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.washington.ischool.commoncents.commoncents.R;

public class SplitBySumActivity extends AppCompatActivity {

    private static final String TAG = "SplitBySumActivity";

    Button doneBtn;
    Button addPerson;
    EditText sumInput;
    EditText nameInput;
    EditText personPercentage;
    EditText personTotal;
    Switch splitEqually;
    TextView totalPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_by_sum);

        //UI elements
        doneBtn = (Button) findViewById(R.id.done_button);
        addPerson = (Button) findViewById(R.id.add_button);
        sumInput = (EditText) findViewById(R.id.sum_input);
        nameInput = (EditText) findViewById(R.id.name_input);
        personPercentage = (EditText) findViewById(R.id.person_percentage);
        personTotal = (EditText) findViewById(R.id.person_total);
        splitEqually = (Switch) findViewById(R.id.split_equally_switch);
        totalPercentage = (TextView) findViewById(R.id.total_percentage);
    }

    protected void addPerson() {

    }

    protected int calculateTotalPercentage() {
        int total = 0;
        totalPercentage.setText(String.valueOf(total));
        return total;
    }
}
