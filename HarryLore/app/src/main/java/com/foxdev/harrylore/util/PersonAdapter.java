package com.foxdev.harrylore.util;

import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.foxdev.harrylore.databinding.PersonItemBinding;
import com.foxdev.harrylore.entity.Person;

import org.jetbrains.annotations.NotNull;

public final class PersonAdapter extends Adapter<PersonAdapter.PersonViewHolder>
{
    private Person[] persons;
    private final OnClickListener onClickListener;

    public PersonAdapter(OnClickListener onClickListener)
    {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType)
    {
        PersonItemBinding binding = PersonItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,
                false);

        return new PersonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PersonAdapter.PersonViewHolder holder, int position)
    {
        Person person = persons[position];
        holder.bind(person);
        holder.itemView.setOnClickListener(onClickListener);
    }

    /**
     * Метод находит персонажа с указанным id
     * @param personId id персонажа
     * @return Персонаж
     */
    public Person personById(final int personId)
    {
        Person person = null;
        int currentIndex = 0;

        while (person == null && currentIndex < persons.length)
        {
            if (persons[currentIndex].getPersonId() == personId) person = persons[currentIndex];
            else ++currentIndex;
        }

        return person;
    }

    @Override
    public int getItemCount()
    {
        return persons.length;
    }

    public void setPersons(Person[] persons)
    {
        this.persons = persons;
        notifyDataSetChanged();
    }

    protected static final class PersonViewHolder extends ViewHolder
    {
        private final PersonItemBinding binding;

        public PersonViewHolder(@NonNull @NotNull PersonItemBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Person person)
        {
            binding.setPerson(person);
            binding.executePendingBindings();
        }
    }
}
