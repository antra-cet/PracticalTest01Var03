package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practial_test01_var03_main);

        // Buttons
        Button plus = (Button) findViewById(R.id.plus);
        Button minus = (Button) findViewById(R.id.minus);
        Button navigateToSecondaryActivityButton = (Button) findViewById(R.id.navigate_to_secondary_activity);

        // Edit texts
        EditText text1 = (EditText) findViewById(R.id.text1);
        EditText text2 = (EditText) findViewById(R.id.text2);
        TextView text3 = (TextView) findViewById(R.id.sum);

        // Stringbuilder result
        StringBuilder result = new StringBuilder();

        // If buttons plus or minus are pressed then the sum is calculated but verify if edit text contain integer numbers, if not display a toast message
        plus.setOnClickListener(v -> {
            // put into result the sum of the two numbers
            result.append(text1.getText().toString()).append(" + ").append(text2.getText().toString()).append(" = ").append(Integer.parseInt(text1.getText().toString()) + Integer.parseInt(text2.getText().toString()));

            if (text1.getText().toString().matches("\\d+") && text2.getText().toString().matches("\\d+")) {
                text3.setText(result.toString());
            } else {
                Toast.makeText(this, "Text fields must contain integer numbers", Toast.LENGTH_SHORT).show();
            }
        });

        minus.setOnClickListener(v -> {
            result.append(text1.getText().toString()).append(" - ").append(text2.getText().toString()).append(" = ").append(Integer.parseInt(text1.getText().toString()) - Integer.parseInt(text2.getText().toString()));

            if (text1.getText().toString().matches("\\d+") && text2.getText().toString().matches("\\d+")) {
                text3.setText(result.toString());
            } else {
                Toast.makeText(this, "Text fields must contain integer numbers", Toast.LENGTH_SHORT).show();
            }
        });

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                res -> {
                    if (res.getResultCode() == RESULT_OK) {
                        Toast.makeText(this, "All results were Ok", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Some results were Canceled", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        navigateToSecondaryActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03SecondaryActivity.class);
            intent.putExtra("result", result.toString());
            // Start the secondary activity
//             startActivity(intent);
//            startActivityForResult(intent, RESULT_OK);

            activityResultLauncher.launch(intent);
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        EditText text1 = (EditText) findViewById(R.id.text1);
        EditText text2 = (EditText) findViewById(R.id.text2);

        outState.putString("text1", ((EditText) findViewById(R.id.text1)).getText().toString());
        outState.putString("text2", ((EditText) findViewById(R.id.text2)).getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        EditText text1 = (EditText) findViewById(R.id.text1);
        EditText text2 = (EditText) findViewById(R.id.text2);

        if(savedInstanceState.containsKey("text1")) {
            text1.setText(
                    savedInstanceState.getString("text1")
            );
        } else {
            text1.setText("1");
        }

        if(savedInstanceState.containsKey("text2")) {
            text2.setText(
                    savedInstanceState.getString("text2")
            );
        } else {
            text2.setText("1");
        }

//        text1.setText("1");
//        text2.setText("1");
    }
}