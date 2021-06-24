package com.foxdev.harrylore.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foxdev.harrylore.R;

/**
 * Фрагмент служит для отображения сообщений
 */
public final class BlackHole extends Fragment {

    public BlackHole() {}

    public static BlackHole newInstance(String message)
    {
        BlackHole blackHole = new BlackHole();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        blackHole.setArguments(bundle);

        return blackHole;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_black_hole, container, false);

        assert getArguments() != null;
        String message = getArguments().getString("message");

        ((TextView) view.findViewById(R.id.black_hole_message)).setText(message);

        return view;
    }
}