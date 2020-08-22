package com.nejdetkadirr.yesorno.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nejdetkadirr.yesorno.R;

public class InfoFragment extends Fragment {
    ImageView back;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info,container,false);
        back = rootView.findViewById(R.id.backImageView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MainScreenFragment mainScreenFragment = new MainScreenFragment();
                fragmentTransaction.replace(R.id.mainFrameLayout,mainScreenFragment).commit();
            }
        });

        return rootView;
    }
}
