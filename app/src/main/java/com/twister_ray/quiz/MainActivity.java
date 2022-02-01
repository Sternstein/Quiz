package com.twister_ray.quiz;

import android.Manifest;
import android.Manifest.permission;
import android.app.Activity;
import android.content.Intent;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

  private AppViewModel mAppViewModel;
  public static final int PERMISSION_WRITE = 0;
  FragmentTransaction fTransaction;
  PlayMenu playMenu;
  PlayerRegistration playerRegistration;
  ActivityResultLauncher<Intent> activityResultLauncher;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    playerRegistration = new PlayerRegistration();
    playMenu = new PlayMenu();
    mAppViewModel = new ViewModelProvider(this).get(AppViewModel.class);
    checkPermission();
    initGoogleClientAndSignin();
    mAppViewModel.getPlayerSettings().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new DisposableSingleObserver<Player>() {
          @Override
          public void onSuccess(@NonNull Player player) {
            Log.d("myLog", "Settings are found " + player.getUid());
            fTransaction = getSupportFragmentManager().beginTransaction();
            fTransaction.replace(R.id.fragmentContent, playMenu);
            fTransaction.commit();
          }

          @Override
          public void onError(@NonNull Throwable e) {
            Log.d("myLog", e.getMessage());
            Log.d("myLog", "got error");
            fTransaction = getSupportFragmentManager().beginTransaction();
            fTransaction.replace(R.id.fragmentContent, playerRegistration);
            fTransaction.commit();
          }
        });
  }

  private void signInSilently() {
    GoogleSignInOptions signInOptions = GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN;
    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    if (GoogleSignIn.hasPermissions(account, signInOptions.getScopeArray())) {
      // Already signed in.
      // The signed in account is stored in the 'account' variable.
      GoogleSignInAccount signedInAccount = account;
    } else {
      // Haven't been signed-in before. Try the silent sign-in first.
      GoogleSignInClient signInClient = GoogleSignIn.getClient(this, signInOptions);
      signInClient
          .silentSignIn()
          .addOnCompleteListener(
              this,
              new OnCompleteListener<GoogleSignInAccount>() {
                @Override
                public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                  if (task.isSuccessful()) {
                    // The signed in account is stored in the task's result.
                    GoogleSignInAccount signedInAccount = task.getResult();
                  } else {
                    // Player will need to sign-in explicitly using via UI.
                    // See [sign-in best practices](http://developers.google.com/games/services/checklist) for guidance on how and when to implement Interactive Sign-in,
                    // and [Performing Interactive Sign-in](http://developers.google.com/games/services/android/signin#performing_interactive_sign-in) for details on how to implement
                    // Interactive Sign-in.
                  }
                }
              });
    }
  }

  public void initGoogleClientAndSignin() {
    activityResultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
          if (result.getResultCode() == Activity.RESULT_OK) {
            // There are no request codes
            Intent data = result.getData();
            if (data != null) {
              Log.d("myLog", data.toString());
            }
          }
        });
    GoogleSignInClient signInClient = GoogleSignIn.getClient(this,
        GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
    Intent intent = signInClient.getSignInIntent();
    activityResultLauncher.launch(intent);
  }
    public boolean checkPermission() {
        int READ_EXTERNAL_PERMISSION = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int WRITE_EXTERNAL_PERMISSION = ContextCompat.checkSelfPermission(this, permission.WRITE_EXTERNAL_STORAGE);
        if((READ_EXTERNAL_PERMISSION != PackageManager.PERMISSION_GRANTED)) {
          Log.d("myLog", "Permission for read not granted!");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_WRITE);
            return false;
        }
        if((WRITE_EXTERNAL_PERMISSION != PackageManager.PERMISSION_GRANTED)) {
          Log.d("myLog", "Permission for write not granted!");
          ActivityCompat.requestPermissions(this, new String[]{permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_WRITE);
          return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==PERMISSION_WRITE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("myLog", "Permissions are granted");
        }
    }
}