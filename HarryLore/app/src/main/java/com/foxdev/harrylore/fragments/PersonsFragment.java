package com.foxdev.harrylore.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.foxdev.harrylore.R;
import com.foxdev.harrylore.activities.CoreActivity;
import com.foxdev.harrylore.entity.Person;
import com.foxdev.harrylore.entity.Wand;
import com.foxdev.harrylore.util.PersonAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public final class PersonsFragment extends Fragment
{
    public PersonsFragment() {}

    public static PersonsFragment newInstance(List<Person> personList)
    {
        Bundle bundle = new Bundle();

        Person[] personArray = personList.toArray(new Person[0]);

        bundle.putParcelableArray("persons", personArray);

        PersonsFragment personsFragment = new PersonsFragment();
        personsFragment.setArguments(bundle);

        return personsFragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_persons, container, false);
        assert getArguments() != null;

        FragmentTransaction transaction = requireActivity()
                .getSupportFragmentManager()
                .beginTransaction();

        PersonAdapter adapter = new PersonAdapter(this::onClick);
        adapter.setPersons((Person[]) getArguments().getParcelableArray("persons"));

        ((RecyclerView) view.findViewById(R.id.persons_list)).setAdapter(adapter);

        transaction.commit();

        return view;
    }

    private void onClick(View view)
    {
        Wand wand = ((PersonAdapter) Objects.requireNonNull(((RecyclerView) this.requireView()
                .findViewById(R.id.persons_list))
                .getAdapter()))
                .personById(Integer.parseInt((String) ((TextView)view.findViewById(R.id.person_id))
                        .getText()))
                .getWand();

        if (wand == null) ((CoreActivity) requireActivity())
                .createBlackHole("У этого персонажа нет волшебной палочки");
        else ((CoreActivity) requireActivity()).showMagicWand(wand);

    }
}