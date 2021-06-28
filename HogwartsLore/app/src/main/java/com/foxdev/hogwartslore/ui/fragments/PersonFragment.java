package com.foxdev.hogwartslore.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.foxdev.hogwartslore.R;
import com.foxdev.hogwartslore.objects.Person;
import com.foxdev.hogwartslore.objects.Wand;
import com.foxdev.hogwartslore.ui.activities.CoreActivity;
import com.foxdev.hogwartslore.util.adapters.PersonAdapter;
import com.foxdev.hogwartslore.util.viewmodels.PersonViewModel;

import java.util.UUID;


public final class PersonFragment extends Fragment {

    private final PersonAdapter personAdapter = new PersonAdapter(this::onClick);


    public PersonFragment() {
    }

    @NonNull
    public static PersonFragment newInstance(@NonNull Person[] persons) {
        Bundle args = new Bundle();
        args.putParcelableArray("persons", persons);

        PersonFragment fragment = new PersonFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_persons, container, false);

        assert getArguments() != null;
        personAdapter.setPersons((Person[]) getArguments().getParcelableArray("persons"));

        ((RecyclerView) view.findViewById(R.id.person_list)).setAdapter(personAdapter);

        return view;
    }

    public void onClick(View v) {
        final UUID personId = UUID.fromString((String)((TextView) v.findViewById(R.id.person_id)).getText());

        Wand wand = personAdapter.getPersonById(personId).wand;
        CoreActivity coreActivity = (CoreActivity) requireActivity();

        if (wand == null || wand.isEmpty()) {
            coreActivity.showMessage("У этого персонажа нет волшебной палочки");
        } else {
            coreActivity.showWand(wand);
        }
    }
}