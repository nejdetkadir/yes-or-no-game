package com.nejdetkadirr.yesorno.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nejdetkadirr.yesorno.R;
import com.nejdetkadirr.yesorno.view.MainActivity;

public class MainScreenFragment extends Fragment {
    ImageView play;
    ImageView info;
    Animation topAnim, bottomAnim;
    TextView playText,infoText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_screen,container,false);
        topAnim = AnimationUtils.loadAnimation(getActivity(),R.anim.play_button);
        bottomAnim = AnimationUtils.loadAnimation(getActivity(),R.anim.info_button);
        play = rootView.findViewById(R.id.playButton);
        info = rootView.findViewById(R.id.infobutton);
        playText = rootView.findViewById(R.id.playTextView);
        infoText = rootView.findViewById(R.id.infoTextView);
        playText.setAnimation(topAnim);
        play.setAnimation(topAnim);
        infoText.setAnimation(bottomAnim);
        info.setAnimation(bottomAnim);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                InfoFragment infoFragment = new InfoFragment();
                fragmentTransaction.replace(R.id.mainFrameLayout,infoFragment).commit();
            }
        });



        return rootView;
    }
}
