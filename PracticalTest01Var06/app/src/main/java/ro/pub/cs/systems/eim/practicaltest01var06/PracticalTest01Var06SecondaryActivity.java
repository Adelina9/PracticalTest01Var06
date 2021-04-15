package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var06SecondaryActivity extends AppCompatActivity {
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_secondary);

        TextView textView = findViewById(R.id.textView);
        Button okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(new ButtonClick());

        Intent intent = getIntent();
        if (intent != null) {
            String text = intent.getStringExtra(Constants.NUMBER_KEY);

            Character prev = '-';
            Boolean flag = true;
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) != '*') {
                    if (prev == '-') {
                        prev = text.charAt(i);
                    } else {
                        if (text.charAt(i) != prev) {
                            flag = false;
                        } else {
                            prev = text.charAt(i);
                        }
                    }
                }
            }
            if (flag) {
                String checked1 = intent.getStringExtra(Constants.CHECKBOX1_KEY);
                String checked2 = intent.getStringExtra(Constants.CHECKBOX2_KEY);
                String checked3 = intent.getStringExtra(Constants.CHECKBOX3_KEY);
                if (checked1.equals("true") && checked2.equals("true") && checked3.equals("true")) {
                    score = 100;
                } else if ((checked1.equals("true") && checked2.equals("true")) ||
                        (checked2.equals("true") && checked3.equals("true")) || (checked3.equals("true") && checked1.equals("true"))) {
                    score = 50;
                } else if (checked1.equals("true") || checked2.equals("true") || checked3.equals("true")) {
                    score = 10;
                }
                textView.setText("Gained " + Integer.toString(score));
            }
        }
    }

    class ButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.putExtra(Constants.SCORE_SECONDARY_ACTIVITY, score);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}