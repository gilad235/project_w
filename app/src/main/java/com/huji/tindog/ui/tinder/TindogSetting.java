package com.huji.tindog.ui.tinder;

import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.huji.tindog.CurrentUserDetails;
import com.huji.tindog.R;


public class TindogSetting extends AppCompatActivity {
    FilterData filterData;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tindog_settings);
        filterData = CurrentUserDetails.getInstance().userFilter;
        Button apply = findViewById(R.id.buttonFinish);
       // Spinner minAgeSpinner = findViewById(R.id.min_age_spinner);
//        Spinner maxAgeSpinner = findViewById(R.id.max_age_spinner);
        RadioGroup radioGender = (RadioGroup) findViewById(R.id.choose_gender);
        RadioGroup radioCastrated = (RadioGroup) findViewById(R.id.choose_castrated);

        apply.setEnabled(true);
        apply.setOnClickListener(v->{
            //todo edit the filters
//            finish();
//            Intent i = new Intent(v.getContext(), openFragmentTindog.class);


            finish();
//            v.getContext().startActivity(i);
        });

        NumberPicker min_picker = findViewById(R.id.min_age_picker);
        min_picker.setMaxValue(20);
        min_picker.setMinValue(0);
        min_picker.setValue(filterData.getMinAge());
//        min_picker.setValue(filterData.getMinAge());
        min_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker min_picker, int oldVal, int newVal){
                min_picker.setValue(newVal);
                filterData.setMinAge(newVal);
                //Process the changes here
            }
        });

        NumberPicker max_picker = findViewById(R.id.max_age_picker);
        max_picker.setMaxValue(20);
        max_picker.setMinValue(0);
        max_picker.setValue(filterData.getMaxAge());
//        min_picker.setValue(filterData.getMinAge());
        max_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker max_picker, int oldVal, int newVal){
                max_picker.setValue(newVal);
                filterData.setMaxAge(newVal);
                //Process the changes here
            }
        });
//        filterData.setMinAge(min_picker.getValue());



        if (filterData.getCastrated()==Choice.first){
            radioCastrated.check(R.id.castrated_button);
        }
        else if(filterData.getCastrated()==Choice.second)
        {
            radioCastrated.check(R.id.uncastrated_button);
        }
        else{
            radioCastrated.check(R.id.both_cas_button);
        }

        RadioButton male_button = findViewById(R.id.male_button);
        RadioButton female_button =findViewById(R.id.female_button);
        RadioButton both_gen_button = findViewById(R.id.both_button);
        if (filterData.getGender()==Choice.first){
            radioGender.check(R.id.male_button);
        }
        else if(filterData.getGender()==Choice.second)
        {
            radioGender.check(R.id.female_button);
        }
        else{
            radioGender.check(R.id.both_button);
        }
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
