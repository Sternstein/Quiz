package com.twister_ray.quiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;


public class PlayerRegistration extends Fragment {
  private AppViewModel mAppViewModel;
  private DataLoader dataLoader;
  public PlayerRegistration() {
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View inflatedView = inflater.inflate(R.layout.fragment_player_registration, container, false);
    final Button sendName = inflatedView.findViewById(R.id.sendName);
    final EditText editTextName = inflatedView.findViewById(R.id.editTextName);
    Activity currentActivity = getActivity();
    if (currentActivity != null) {
      mAppViewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);
      dataLoader = new DataLoader(mAppViewModel);
      sendName.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          Log.d("myLog", "Start upload");
          String name = editTextName.getText().toString();
          dataLoader.registerPlayer(name);
          dataLoader.loadCategories();
          PlayMenu playMenu = new PlayMenu();
          FragmentTransaction fTransaction = getActivity().getSupportFragmentManager().beginTransaction();
          fTransaction.replace(R.id.fragmentContent, playMenu);
          fTransaction.commit();
        }
      });
    }
    return inflatedView;
  }
}