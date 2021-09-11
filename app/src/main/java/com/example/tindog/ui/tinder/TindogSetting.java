package com.example.tindog.ui.tinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tindog.CurrentUserDetails;
import com.example.tindog.R;


public class TindogSetting extends AppCompatActivity {
    FilterData filterData;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tindog_settings);
        filterData = CurrentUserDetails.getInstance().userFilter;
        Button apply = findViewById(R.id.buttonFinish);
        Spinner minAgeSpinner = findViewById(R.id.min_age_spinner);
        Spinner maxAgeSpinner = findViewById(R.id.max_age_spinner);
        RadioGroup radioGender = (RadioGroup) findViewById(R.id.choose_gender);
        RadioGroup radioCastrated = (RadioGroup) findViewById(R.id.choose_castrated);

        apply.setEnabled(true);
        apply.setOnClickListener(v->{
            //todo edit the filters
//            finish();
            Intent i = new Intent(v.getContext(), openFragmentTindog.class);
            finish();
            v.getContext().startActivity(i);
        });
        radioGender.check(R.id.both_button);
        radioCastrated.check(R.id.both_cas_button);

        RadioButton male_button = findViewById(R.id.male_button);
        RadioButton female_button =findViewById(R.id.female_button);
        RadioButton both_gen_button = findViewById(R.id.both_button);
        //todo edit default button to be sync with filterdata
        male_button.setOnClickListener(v->{
            filterData.setGender(Choice.first);

        });
        female_button.setOnClickListener(v->{
            filterData.setGender(Choice.second);

        });
        both_gen_button.setOnClickListener(v->{
            filterData.setGender(Choice.both);
        });
        RadioButton castrated_button= findViewById(R.id.castrated_button);
        RadioButton uncastrated_button =findViewById(R.id.uncastrated_button);
        RadioButton both_cast_button =findViewById(R.id.both_cas_button);
        castrated_button.setOnClickListener(v->{
            filterData.setCastrated(Choice.first);

        });
        uncastrated_button.setOnClickListener(v->{
            filterData.setCastrated(Choice.second);
        });
        both_cast_button.setOnClickListener(v->{
            filterData.setCastrated(Choice.both);
        });

//        Integer[] items = new Integer[20];
//        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
//                android.R.layout.simple_spinner_item,items);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        minAgeSpinner.setAdapter(adapter);
//        filterData.setMinAge((Integer) minAgeSpinner.getSelectedItem());
        //todo check if this works and add a spinner for the max age
//        minAgeSpinner.setOnItemSelectedListener(this);




    }
}
