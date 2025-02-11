package edu.sjsu.android.temperatureconverterbyagrikagupta;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Toast;
import static edu.sjsu.android.temperatureconverterbyagrikagupta.ConverterUtil.*;
import androidx.appcompat.app.AppCompatActivity;
import edu.sjsu.android.temperatureconverterbyagrikagupta.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivityMainBinding binding;
    private int item=0;
    private final String[] units = {
            "°C",
            "°F",
            "K"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.button.setOnClickListener(this::calculate);
        binding.spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> ad=new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                units
        );
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(ad);
        binding.spinner.setSelection(item);
        radiobuttontext(item);
    }

    @SuppressLint("SetTextI18n")
    private void calculate(View view) {
        String temp=binding.editTextNumberDecimal.getText().toString().trim();
        float tempp;
        if (!temp.isEmpty()) {
            tempp = Float.parseFloat(temp);
            int sel = binding.radioGroup.getCheckedRadioButtonId();
            RadioButton seltext = findViewById(sel);
            String finalunit = seltext.getText().toString();
            float result = calc(binding.spinner.getSelectedItem().toString(), finalunit, tempp);
            binding.textView.setText(getString(R.string.result,result) + finalunit);
            binding.textView.setVisibility(View.VISIBLE);
        } else {
            binding.textView.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Please enter a value!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item=position;
        radiobuttontext(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void radiobuttontext(int position) {
        switch (position) {
            case 0: // "°C" selected
                binding.radioButton.setText(units[1]); // "°F"
                binding.radioButton2.setText(units[2]); // "K"
                break;
            case 1: // "°F" selected
                binding.radioButton.setText(units[0]); // "°C"
                binding.radioButton2.setText(units[2]); // "K"
                break;
            case 2: // "K" selected
                binding.radioButton.setText(units[0]); // "°C"
                binding.radioButton2.setText(units[1]); // "°F"
                break;
        }
    }

    private float calc(String initialUnit, String finalUnit, float arg) {
        float tempInCelsius;

        // Step 1: Convert initial unit to Celsius
        switch (initialUnit) {
            case "°C":
                tempInCelsius = arg; // Already in Celsius
                break;
            case "°F":
                tempInCelsius = convertFahrenheitToCelsius(arg); // Convert Fahrenheit to Celsius
                break;
            case "K":
                tempInCelsius = convertKelvinToCelsius(arg); // Convert Kelvin to Celsius
                break;
            default:
                return arg; // Fallback (shouldn't happen)
        }

        // Step 2: Convert Celsius to final unit
        switch (finalUnit) {
            case "°C":
                return tempInCelsius; // Already in Celsius
            case "°F":
                return convertCelsiusToFahrenheit(tempInCelsius); // Convert Celsius to Fahrenheit
            case "K":
                return convertCelsiusToKelvin(tempInCelsius); // Convert Celsius to Kelvin
            default:
                return arg; // Fallback (shouldn't happen)
        }
    }

}