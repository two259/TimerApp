package com.example.timerapp;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class ControlFragment extends Fragment implements View.OnClickListener{

    Button start;
    Button lap;
    Button reset;
    Button viewLaps;
    TextView timer;
    //declare interaction listener
    private OnFragmentInteractionListener mListener;

    public ControlFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_control, container, false);

        //initialize buttons and textview
        start = (Button) view.findViewById(R.id.startButton);
        lap = (Button) view.findViewById(R.id.lapButton);
        reset = (Button) view.findViewById(R.id.resetButton);
        timer = (TextView) view.findViewById(R.id.timerView);
        viewLaps = (Button) view.findViewById(R.id.viewLapsButton);
        int orientation = getResources().getConfiguration().orientation;
        // Only show the view laps button if in portrait mode
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            viewLaps.setVisibility(Button.VISIBLE);
        }
        else {
            viewLaps.setVisibility(Button.INVISIBLE);
        }

        //add listeners
        start.setOnClickListener(this);
        lap.setOnClickListener(this);
        reset.setOnClickListener(this);
        viewLaps.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof OnFragmentInteractionListener){
            this.mListener= (OnFragmentInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString()+" must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void onClick(View view){
        if(view.getId() == start.getId()){ // Send id's to the main method.

            mListener.onButtonClicked(0);
            // Change the stop/start to the opposite when clicked.
            if(start.getText().equals("Start")){
                start.setText("Stop");
                start.setBackgroundColor(Color.RED);
            }
            else if(start.getText().equals("Stop")){
                start.setText("Start");
                start.setBackgroundColor(Color.GREEN);
            }

        }else if (view.getId() == lap.getId()){

            mListener.onButtonClicked(1);

        } else if(view.getId() == reset.getId() ){

            mListener.onButtonClicked(2);
        }
        else if(view.getId() == viewLaps.getId() ){

            mListener.onButtonClicked(3);
        }
    }

    public interface OnFragmentInteractionListener{
        void onButtonClicked(int infoID);
    }

}
