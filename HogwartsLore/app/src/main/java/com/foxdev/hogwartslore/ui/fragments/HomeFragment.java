package com.foxdev.hogwartslore.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.foxdev.hogwartslore.R;
import com.foxdev.hogwartslore.ui.activities.CoreActivity;
import com.foxdev.hogwartslore.objects.constants.Houses;

import dagger.hilt.android.AndroidEntryPoint;

public final class HomeFragment extends Fragment {

    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setButtonClickListener(view.findViewById(R.id.gryffindor_button), Houses.Gryffindor);
        setButtonClickListener(view.findViewById(R.id.ravenclaw_button), Houses.Ravenclaw);
        setButtonClickListener(view.findViewById(R.id.slitherin_button), Houses.Slytherin);
        setButtonClickListener(view.findViewById(R.id.hufflepuff_button), Houses.Hufflepuff);

        return view;
    }

    private void setButtonClickListener(@NonNull Button button, @NonNull String house) {
        button.setOnClickListener(v -> {
            ((CoreActivity) requireActivity()).loadPersons(house);
        });
    }
}