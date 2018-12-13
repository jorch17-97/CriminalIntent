package com.jorgenicolas.android.criminalintent.criminalintent;

import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;


public class CrimeActivity extends SingleFragmentActivity {

    private  static  final  String
            EXTRA_CRIME_ID = "mi dominio id";


    codigo
    return Intent;

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);
        return  CrimeFragment.newInstance(crimeId);
    }
}
