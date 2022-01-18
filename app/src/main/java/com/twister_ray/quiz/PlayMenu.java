package com.twister_ray.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlayMenu extends Fragment {


  public PlayMenu() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View inflatedView = inflater.inflate(R.layout.fragment_play_menu, container, false);
    final Button startGame = inflatedView.findViewById(R.id.startGame);
    final Button exit = inflatedView.findViewById(R.id.exit);
    Activity currentActivity = getActivity();
    startGame.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currentActivity != null) {
          Intent intent = new Intent(currentActivity, CategoryActivity.class);
          startActivity(intent);
        }
      }
    });
    exit.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (currentActivity != null){
          currentActivity.finishAffinity();
        }
      }
    });
    return inflatedView;
  }
}