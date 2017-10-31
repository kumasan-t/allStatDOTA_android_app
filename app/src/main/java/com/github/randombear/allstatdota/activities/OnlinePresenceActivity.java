package com.github.randombear.allstatdota.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.randombear.allstatdota.R;
import com.github.randombear.allstatdota.dataaccessobject.entities.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class OnlinePresenceActivity extends AppCompatActivity {

    private static String TAG ="UserList Activity";
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;

    private FirebaseListAdapter<User> mAdapter;
    private ListView mListView;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_online_presence);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewStub mViewStub = findViewById(R.id.online_presence_viewstub);
        mListView = findViewById(R.id.activity_online_list_view);

        if (mAuth.getCurrentUser() == null) {
            mViewStub.setLayoutResource(R.layout.offline_state_presence);
            mViewStub.inflate();
            SignInButton signInButton = findViewById(R.id.sign_in_button);
            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signIn();
                }
            });
        } else {
            createFirebaseListAdapter();
        }

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getBaseContext().getString(R.string.local_web_id))
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("MAIN ACTIVITY", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            firebaseAuthWithGoogle(acct);
            Toast.makeText(getBaseContext(),"Connected as " + acct.getDisplayName(), Toast.LENGTH_LONG).show();
        } else {
            // Signed out, show unauthenticated UI.
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = mAuth.getCurrentUser();
                            DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                            db.child("users").child(user.getUid()).child("online").setValue(true);
                            db.child("users").child(user.getUid()).child("name").setValue(user.getDisplayName());

                            // Set ViewStub new layout
                            LinearLayout layout = findViewById(R.id.activity_online_coordinator_layout);
                            layout.removeView(findViewById(R.id.offline_state_relative_layout));
                            createFirebaseListAdapter();
                            mAdapter.startListening();

                            Log.d(TAG, "Connected user "  + user.toString());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }


    /**
     * onStart checks if the user has already performed the Firebase authentication:
     * if that is true then updates the current user online status to true.
     */
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            FirebaseDatabase.getInstance().getReference()
                    .child("users")
                    .child(mAuth.getCurrentUser().getUid())
                    .child("online").setValue(true);
            Log.d(TAG,"User " + mAuth.getCurrentUser().getUid() +  " online state set TRUE");

            mAdapter.startListening();

        } else
            Log.d(TAG, "User is not signed in");
    }


    /**
     * If the user has already performed the Firebase authentication then its online status
     * is set to false whenever the activity goes on pause.
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (mAuth.getCurrentUser() != null) {
            FirebaseDatabase.getInstance().getReference()
                    .child("users")
                    .child(mAuth.getCurrentUser().getUid())
                    .child("online").setValue(false);

            mAdapter.stopListening();
        }
    }

    private void createFirebaseListAdapter() {
        Log.d(TAG,"Inside else statement in onStart");
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("users")
                .limitToLast(50);

        FirebaseListOptions<User> options = new FirebaseListOptions.Builder<User>()
                .setQuery(query, User.class)
                .setLayout(R.layout.user_single_layout)
                .build();
        mAdapter = new FirebaseListAdapter<User>(options) {
            @Override
            protected void populateView(final View v, User model, int position) {
                TextView name = v.findViewById(R.id.user_single_name);
                TextView online = v.findViewById(R.id.user_single_online_status);
                name.setText(model.getName());
                online.setText(model.isOnline() ? "connected" : "last seen: " + model.getDate());
            }
        };
        mListView.setAdapter(mAdapter);
        mListView.setVisibility(View.VISIBLE);
    }

}
