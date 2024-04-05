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

public class DistanceConverterFragment extends Fragment {

    private EditText milesEditText;
    private EditText kilometersEditText;
    private Button convertButton;
    private TextView outputField;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_distance_converter, container, false);

        milesEditText = view.findViewById(R.id.milesEditText);
        kilometersEditText = view.findViewById(R.id.kilometersEditText);
        convertButton = view.findViewById(R.id.convertButton);
        outputField = view.findViewById(R.id.outputField);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertDistance();
            }
        });

        return view;
    }

    private void convertDistance() {
        String milesText = milesEditText.getText().toString().trim();
        String kilometersText = kilometersEditText.getText().toString().trim();

        if (TextUtils.isEmpty(milesText) && TextUtils.isEmpty(kilometersText)) {
            Toast.makeText(getContext(), "Please enter a distance.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!TextUtils.isEmpty(milesText) && !TextUtils.isEmpty(kilometersText)) {
            if (isNumeric(milesText) && isNumeric(kilometersText)) {
                double milesValue = Double.parseDouble(milesText);
                double kilometersValue = milesValue * 1.609;
                kilometersEditText.setText(formatDistance(kilometersValue));
            } else {
                Toast.makeText(getContext(), "Please enter a valid distance in only one field.", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        if (!TextUtils.isEmpty(milesText)) {
            if (isNumeric(milesText)) {
                double milesValue = Double.parseDouble(milesText);
                double kilometersValue = milesValue * 1.609;
                kilometersEditText.setText(formatDistance(kilometersValue));
            } else {
                Toast.makeText(getContext(), getString(R.string.error3), Toast.LENGTH_SHORT).show();
            }
        }

        if (!TextUtils.isEmpty(kilometersText)) {
            if (isNumeric(kilometersText)) {
                double kilometersValue = Double.parseDouble(kilometersText);
                double milesValue = kilometersValue / 1.609;
                milesEditText.setText(formatDistance(milesValue));
            } else {
                Toast.makeText(getContext(), "Please enter a valid distance in kilometers.", Toast.LENGTH_SHORT).show();
            }
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

    private String formatDistance(double value) {
        DecimalFormat format = new DecimalFormat("#.##");
        return format.format(value);
    }
}