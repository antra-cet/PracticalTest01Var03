package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var03SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_secondary);

        // Text
        TextView text = (TextView) findViewById(R.id.text);

        // Buttons
        Button correct = (Button) findViewById(R.id.correct);
        Button incorrect = (Button) findViewById(R.id.incorrect);

        // Get the intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String textValue = extras.getString("result");
            text.setText(textValue);
        }

        // Correct button listener
        correct.setOnClickListener(v -> {
            setResult(RESULT_OK, new Intent());
            finish();
        });

        // Incorrect button listener
        incorrect.setOnClickListener(v -> {
            setResult(RESULT_CANCELED, new Intent());
            finish();
        });
    }
}