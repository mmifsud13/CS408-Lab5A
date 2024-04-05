package edu.jsu.mcis.cs408.lab5a;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;

public class TemperatureConverterFragment extends Fragment {

    private EditText fahrenheitEditText;
    private EditText celsiusEditText;
    private Button convertButton;
    private TextView outputField;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temperature_converter, container, false);

        fahrenheitEditText = view.findViewById(R.id.fahrenheitEditText);
        celsiusEditText = view.findViewById(R.id.celsiusEditText);
        convertButton = view.findViewById(R.id.convertButton);
        outputField = view.findViewById(R.id.outputField);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature();
            }
        });

        return view;
    }

    private void convertTemperature() {
        String fahrenheitText = fahrenheitEditText.getText().toString().trim();
        String celsiusText = celsiusEditText.getText().toString().trim();

        if (TextUtils.isEmpty(fahrenheitText) && TextUtils.isEmpty(celsiusText)) {
            Toast.makeText(getContext(), getString(R.string.error2), Toast.LENGTH_SHORT).show();
        }
        else if (!TextUtils.isEmpty(fahrenheitText) && isNumeric(fahrenheitText)) {
            double fahrenheitValue = Double.parseDouble(fahrenheitText);
            double celsiusValue = (fahrenheitValue - 32) * 5 / 9;
            celsiusEditText.setText(formatTemperature(celsiusValue));
        } else if (!TextUtils.isEmpty(celsiusText) && isNumeric(celsiusText)) {
            double celsiusValue = Double.parseDouble(celsiusText);
            double fahrenheitValue = (celsiusValue * 9 / 5) + 32;
            fahrenheitEditText.setText(formatTemperature(fahrenheitValue));
        } else {
            Toast.makeText(getContext(), getString(R.string.error2), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String formatTemperature(double value) {
        DecimalFormat format = new DecimalFormat("#.##");
        return format.format(value);
    }
}
