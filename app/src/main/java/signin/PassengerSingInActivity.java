package signin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taxiapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import maps.PassengerMapsActivity;

import static android.content.ContentValues.TAG;

public class PassengerSingInActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private TextInputLayout textEmailAddress;
    private TextInputLayout textInputName;
    private TextInputLayout textInputPassword;
    private TextInputLayout textConfirmInputPassword;

    private Button loginSingInUpButton;
    private TextView toggleLoginSingUpTextView;

    private boolean isLoginModeActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(PassengerSingInActivity.this,
                    PassengerMapsActivity.class));
        }

        textEmailAddress = findViewById(R.id.TextInputEmail);
        textInputName = findViewById(R.id.TextInputName);
        textInputPassword = findViewById(R.id.TextInputPassword);
        textConfirmInputPassword = findViewById(R.id.TextInputConfirmPassword);

        loginSingInUpButton = findViewById(R.id.loginSingInUpButton);
        toggleLoginSingUpTextView = findViewById(R.id.toggleLoginSingUpTextView);

    }

    private boolean validateEmail(){

        String emailInput = textEmailAddress.getEditText()
                .getText()
                .toString()
                .trim();

        if (emailInput.isEmpty()){
            textEmailAddress.setError("Please input your email");
            return false;
        } else {
            textEmailAddress.setError("");
            return true;
        }
    }

    private boolean validateName(){

        String nameInput = textInputName.getEditText()
                .getText()
                .toString()
                .trim();

        if (nameInput.isEmpty()){
            textInputName.setError("Please input your name");
            return false;
        } else if (nameInput.length() > 15){
            textInputName.setError("Please input your name max 15 chars");
            return false;
        } else {
            textInputName.setError("");
            return true;
        }
    }

    private boolean validatePassword() {

        String userPassword = textInputPassword.getEditText()
                .getText()
                .toString()
                .trim();

        if (userPassword.isEmpty()){
            textInputPassword.setError("Please input password");
            return false;
        } else if (userPassword.length() < 7){
            textInputPassword.setError("Please input password min 8 chars");
            return false;
        } else {
            textInputPassword.setError("");
            return true;
        }
    }


    private boolean validateConfirmPassword() {

        String userPassword = textInputPassword.getEditText()
                .getText()
                .toString()
                .trim();

        String confirmPassword = textConfirmInputPassword
                .getEditText()
                .getText()
                .toString()
                .trim();

        if (!confirmPassword.equals(userPassword)){
            textConfirmInputPassword.setError("Your password not confirm");
            return false;
        } else {
            textInputPassword.setError("");
            return true;
        }

    }

    public void loginSignUpUser(View view) {

        if (!validateEmail() | !validateName() | !validatePassword()){
            return;
        }

        if (isLoginModeActive){
            firebaseAuth.signInWithEmailAndPassword(textEmailAddress.getEditText().getText().toString().trim(),
                    textInputPassword.getEditText().getText().toString().trim())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                startActivity(new Intent(
                                        PassengerSingInActivity.this,
                                        PassengerMapsActivity.class
                                        ));
                                /*updateUI(user);*/
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(PassengerSingInActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                /*updateUI(null);*/
                            }
                        }
                    });

        } else {

            if (!validateEmail() | !validateName() | !validatePassword() | !validateConfirmPassword()){
                return;
            }

            firebaseAuth.createUserWithEmailAndPassword(
                    textEmailAddress.getEditText().getText().toString().trim(),
                    textInputPassword.getEditText().getText().toString().trim()
            )
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Log.d(TAG, "signInWithEmail:success");
                                Toast.makeText(PassengerSingInActivity.this, "Authentication complete",
                                        Toast.LENGTH_LONG).show();

                                FirebaseUser users = firebaseAuth.getCurrentUser();

                                startActivity(new Intent(
                                        PassengerSingInActivity.this,
                                        PassengerMapsActivity.class
                                ));

                                /*createUser(users);*/

                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(PassengerSingInActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }

    }

    public void toggleLoginSignUp(View view) {

        if (isLoginModeActive){
            isLoginModeActive = false;
            loginSingInUpButton.setText("Sing Up");
            toggleLoginSingUpTextView.setText("Or, log in");
            textConfirmInputPassword.setVisibility(View.VISIBLE);
        } else {
            isLoginModeActive = true;
            loginSingInUpButton.setText("Log In");
            toggleLoginSingUpTextView.setText("Or, sing up");
            textConfirmInputPassword.setVisibility(View.GONE);
        }

    }
}