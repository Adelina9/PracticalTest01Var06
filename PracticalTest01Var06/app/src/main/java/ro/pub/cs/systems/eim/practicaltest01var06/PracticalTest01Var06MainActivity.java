package ro.pub.cs.systems.eim.practicaltest01var06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {

    Button playButton;
    int score;

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(Constants.SCORE_MAIN_ACTIVITY, score);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.get(Constants.SCORE_MAIN_ACTIVITY) != null) {
            score = savedInstanceState.getInt(Constants.SCORE_MAIN_ACTIVITY);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new ButtonClick());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        score += data.getIntExtra(Constants.SCORE_SECONDARY_ACTIVITY, 0);
        Toast.makeText(getApplication(), "Score:" + String.valueOf(score), Toast.LENGTH_LONG).show();
    }

    class ButtonClick implements View.OnClickListener {
        public void setRandomNumber(EditText editText) {
            int[] randomNumbers = new int[]{0, 1, 2, 3};
            Random r = new Random();
            int randomIndex = r.nextInt(randomNumbers.length);
            if (randomIndex == 0) {
                editText.setText("*");
            } else {
                String text = String.valueOf(randomNumbers[randomIndex]);
                editText.setText(text);
            }
        }

        @Override
        public void onClick(View view) {
            EditText starEditText = findViewById(R.id.starEditText);
            EditText zeroEditText = findViewById(R.id.zeroEditText);
            EditText oneEditText = findViewById(R.id.oneEditText);
            CheckBox checkBox1 = findViewById(R.id.checkBox);
            CheckBox checkBox2 = findViewById(R.id.checkBox2);
            CheckBox checkBox3 = findViewById(R.id.checkBox3);

            String checked1 = "true";
            String checked2 = "true";
            String checked3 = "true";
            if (view.getId() == R.id.playButton) {
                if (!checkBox1.isChecked()) {
                    setRandomNumber(starEditText);
                    checked1 = "false";
                }
                if (!checkBox2.isChecked()) {
                    setRandomNumber(zeroEditText);
                    checked2 = "false";
                }
                if (!checkBox3.isChecked()) {
                    setRandomNumber(oneEditText);
                    checked3 = "false";
                }

                String text = starEditText.getText().toString() + zeroEditText.getText().toString() + oneEditText.getText().toString();
                Toast.makeText(getApplication(), text, Toast.LENGTH_LONG).show();

                Intent intent = new Intent("ro.pub.cs.systems.eim.practicaltest01var06.intent.action.PracticalTest01Var06SecondaryActivity");
                intent.putExtra(Constants.NUMBER_KEY, text);
                intent.putExtra(Constants.CHECKBOX1_KEY, checked1);
                intent.putExtra(Constants.CHECKBOX2_KEY, checked2);
                intent.putExtra(Constants.CHECKBOX3_KEY, checked3);
                startActivityForResult(intent, 1);
            }
        }
    }
}