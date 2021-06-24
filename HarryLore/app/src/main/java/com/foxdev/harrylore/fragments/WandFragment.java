package com.foxdev.harrylore.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foxdev.harrylore.R;
import com.foxdev.harrylore.databinding.FragmentWandBinding;
import com.foxdev.harrylore.entity.Wand;

import org.jetbrains.annotations.NotNull;

public final class WandFragment extends Fragment
{
    public WandFragment()
    {
    }

    public static WandFragment newInstance(Wand wand)
    {
        WandFragment fragment = new WandFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("wand", wand);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        FragmentWandBinding wandBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_wand,
                container,
                false);

        View view = wandBinding.getRoot();

        assert getArguments() != null;
        Wand wand = getArguments().getParcelable("wand");

        wandBinding.setWand(wand);
        wandBinding.executePendingBindings();

        return view;
    }
}