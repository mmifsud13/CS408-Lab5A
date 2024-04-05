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

import java.text.NumberFormat;

public class TipCalculatorFragment extends Fragment {

    private EditText totalBillEditText;
    private EditText tipPercentageEditText;
    private EditText numPeopleEditText;
    private Button calculateButton;
    private TextView outputField;

    public static final String ARG_ID = "id";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip_calculator, container, false);

        totalBillEditText = view.findViewById(R.id.totalBillEditText);
        tipPercentageEditText = view.findViewById(R.id.tipPercentageEditText);
        numPeopleEditText = view.findViewById(R.id.numPeopleEditText);
        calculateButton = view.findViewById(R.id.calculateButton);
        outputField = view.findViewById(R.id.outputField);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotalPerPerson();
            }
        });

        return view;
    }

    private void calculateTotalPerPerson() {
        if (TextUtils.isEmpty(totalBillEditText.getText()) ||
                TextUtils.isEmpty(tipPercentageEditText.getText()) ||
                TextUtils.isEmpty(numPeopleEditText.getText())) {
            Toast.makeText(getContext(), getString(R.string.error1), Toast.LENGTH_SHORT).show();
            return;
        }

        double totalBill = Double.parseDouble(totalBillEditText.getText().toString());
        double tipPercentage = Double.parseDouble(tipPercentageEditText.getText().toString());
        int numPeople = Integer.parseInt(numPeopleEditText.getText().toString());

        if (totalBill == 0 || tipPercentage == 0 || numPeople == 0) {
            return;
        }

        double totalPerPerson = (totalBill * (1 + tipPercentage / 100)) / numPeople;

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        String result = currencyFormat.format(totalPerPerson);

        outputField.setText(String.format("%s%s", getString(R.string.totalPerPerson), result));
    }
}

