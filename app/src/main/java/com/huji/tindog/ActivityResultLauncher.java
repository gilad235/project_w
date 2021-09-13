//package com.example.tindog;
//
//
//import android.content.Intent;
//
//import androidx.activity.result.ActivityResultCallback;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
//import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
//
//public class ActivityResultLauncher extends AppCompatActivity {
//    // See: https://developer.android.com/training/basics/intents/result
//    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
//            new FirebaseAuthUIActivityResultContract(),
//            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
//                @Override
//                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
//                    onSignInResult(result);
//                }
//            }
//    );
//
//}
