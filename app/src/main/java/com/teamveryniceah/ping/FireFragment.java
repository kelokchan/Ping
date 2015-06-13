package com.teamveryniceah.ping;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skyfishjy.library.RippleBackground;


/**
 * A simple {@link Fragment} subclass.
 */
public class FireFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {


    public FireFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_fire, container, false);
        // Inflate the layout for this fragment
        rootView.findViewById(R.id.fireButton).setOnClickListener(this);
        rootView.findViewById(R.id.fireButton).setOnLongClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        final RippleBackground rippleBackground = (RippleBackground) getActivity().findViewById(R.id.fireContent);
        showRipple(rippleBackground);
        showRipple(rippleBackground);
        final MainActivity activity = (MainActivity) getActivity();
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                activity.showNotification();
            }

        }, 10000L);
    }

    public void showRipple(RippleBackground rippleBackground) {
        if (rippleBackground.isRippleAnimationRunning()) {
            rippleBackground.stopRippleAnimation();
        } else {
            rippleBackground.startRippleAnimation();
            notifyUser();
        }
    }

    public void notifyUser() {
        Toast.makeText(getActivity(), "Notifying nearest department", Toast.LENGTH_LONG).show();
        Toast.makeText(getActivity(), "Request acknowledged", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onLongClick(View v) {
        final RippleBackground rippleBackground = (RippleBackground) getActivity().findViewById(R.id.fireContent);
        rippleBackground.stopRippleAnimation();
        return true;
    }

}
