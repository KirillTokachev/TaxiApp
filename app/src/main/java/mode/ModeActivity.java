package mode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taxiapp.R;

import signin.DriverSingInActivity;
import signin.PassengerSingInActivity;

public class ModeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_sign_up);


    }

    public void goToPassengerSignInActivity(View view) {
        startActivity(new Intent(
                ModeActivity.this,
                PassengerSingInActivity.class
        ));
    }

    public void goToDriverSignInActivity(View view) {
        startActivity(new Intent(
                ModeActivity.this,
                DriverSingInActivity.class
        ));
    }
}