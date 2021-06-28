package com.foxdev.hogwartslore.util.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foxdev.hogwartslore.R;
import com.foxdev.hogwartslore.databinding.PersonItemBinding;
import com.foxdev.hogwartslore.objects.Person;
import com.foxdev.hogwartslore.util.ImageLoader;

import java.util.UUID;

public final class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private Person[] personList = new Person[0];
    private final View.OnClickListener onClickListener;

    public PersonAdapter(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PersonItemBinding binding = PersonItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new PersonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = personList[position];
        holder.bind(person);
        holder.itemView.setOnClickListener(onClickListener);

        ImageLoader.loadImage(person.internetImage,
                (ImageView) holder.itemView.findViewById(R.id.person_image));
    }

    @Override
    public int getItemCount() {
        return personList.length;
    }

    public void setPersons(Person[] persons) {
        personList = persons;
        notifyDataSetChanged();
    }

    @NonNull
    public Person getPersonById(UUID personId) {
        Person person = null;
        int index = 0;

        while (person == null && index < personList.length) {
            Person currentPerson = personList[index];
            if (currentPerson.personUUID.compareTo(personId) == 0) person = currentPerson;
            else ++index;
        }

        assert person != null;
        return person;
    }

    protected static final class PersonViewHolder extends RecyclerView.ViewHolder {
        private final PersonItemBinding binding;

        public PersonViewHolder(@NonNull PersonItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(@NonNull Person person) {
            binding.setPerson(person);
            binding.executePendingBindings();
        }
    }
}
