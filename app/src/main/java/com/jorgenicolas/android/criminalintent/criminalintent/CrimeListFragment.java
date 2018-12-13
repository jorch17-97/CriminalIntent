package com.jorgenicolas.android.criminalintent.criminalintent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.jorgenicolas.android.criminalintent.R;
import com.jorgenicolas.android.criminalintent.model.Crime;
import com.jorgenicolas.android.criminalintent.model.CrimeLab;

import java.util.List;
import java.util.zip.Inflater;

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list,
                container, false);
        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Use interface
        updateUI();

        return view;
    }

    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }


    private class  CrimeHolder extends RecyclerView.ViewHolder
            implements  View.OnClickListener {
        private Crime mCrime;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedChecBox;

        public CrimeHolder(View itemView) {
            super(itemView);

            //El manejador de evento es el mismo
            itemView.setOnClickListener(this);
            //Me permite encontrar cada uno de los Widgets
            //Se inflan
            mTitleTextView = itemView
                    .findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = itemView
                    .findViewById(R.id.list_item_crime_date_text_view);
            mSolvedChecBox = itemView
                    .findViewById(R.id.list_item_crime_solved_check_box);
        }

        //Se agrego un metodo bind = vincular
        public void bindCrime (Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedChecBox.setChecked(mCrime.isSolved());
        }

        @Override
        public void onClick(View show) {
            Toast.makeText(getActivity(),
                    "Se hizo clic sobre "
                        + mCrime.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            LayoutInflater layoutinflater = LayoutInflater.from(getActivity());
            View view = layoutinflater.inflate(
                    R.layout.list_item_crime, parent, false //Es un layout nuestro que estamos usando
            );
            return new CrimeHolder(view);
        }

        //Toma al modelo y lo coloca en la vista___ Lo utilizamos para usar bind
        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime);
        }

        //Cual es el tamaño y cuantos hay
        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}