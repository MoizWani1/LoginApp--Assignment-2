package com.example.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private static final String HARDCODED_USERNAME = "admin";
    private static final String HARDCODED_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        View mainView = findViewById(R.id.main);
        mainView.post(() -> {
            ImageView logo = findViewById(R.id.logo);
            TextInputLayout usernameLayout = findViewById(R.id.username_layout);
            TextInputLayout passwordLayout = findViewById(R.id.password_layout);
            Button loginButton = findViewById(R.id.login_button);
            Button resetButton = findViewById(R.id.btnReset);

            // Animations
            ObjectAnimator logoFade = ObjectAnimator.ofFloat(logo, "alpha", 0f, 1f);
            logoFade.setDuration(1000);
            ObjectAnimator logoSlide = ObjectAnimator.ofFloat(logo, "translationY", -100f, 0f);
            logoSlide.setDuration(1000);

            ObjectAnimator usernameFade = ObjectAnimator.ofFloat(usernameLayout, "alpha", 0f, 1f);
            usernameFade.setDuration(1000);
            ObjectAnimator usernameSlide = ObjectAnimator.ofFloat(usernameLayout, "translationY", 100f, 0f);
            usernameSlide.setDuration(1000);

            ObjectAnimator passwordFade = ObjectAnimator.ofFloat(passwordLayout, "alpha", 0f, 1f);
            passwordFade.setDuration(1000);
            ObjectAnimator passwordSlide = ObjectAnimator.ofFloat(passwordLayout, "translationY", 100f, 0f);
            passwordSlide.setDuration(1000);

            ObjectAnimator loginFade = ObjectAnimator.ofFloat(loginButton, "alpha", 0f, 1f);
            loginFade.setDuration(1000);

            ObjectAnimator resetFade = ObjectAnimator.ofFloat(resetButton, "alpha", 0f, 1f);
            resetFade.setDuration(1000);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(logoFade, logoSlide, usernameFade, usernameSlide, passwordFade, passwordSlide, loginFade, resetFade);
            animatorSet.start();
        });

        Button resetButton = findViewById(R.id.btnReset);
        resetButton.setOnClickListener(v -> {
            EditText etName = findViewById(R.id.etName);
            EditText etPassword = findViewById(R.id.etPassword);
            TextView tvMessage = findViewById(R.id.tvMessage);
            etName.setText("");
            etPassword.setText("");
            tvMessage.setText("");
            Toast.makeText(MainActivity.this, "Fields are cleared!", Toast.LENGTH_SHORT).show();
        });

        TextView registerLink = findViewById(R.id.register_link);
        registerLink.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RegistrationActivity.class)));

        TextView forgotPasswordLink = findViewById(R.id.forgot_password_link);
        forgotPasswordLink.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ForgotPasswordActivity.class)));
    }

    public void login(View v) {
        EditText etName = findViewById(R.id.etName);
        String strName = etName.getText().toString();
        EditText etPassword = findViewById(R.id.etPassword);
        String strPassword = etPassword.getText().toString();

        if (strName.isEmpty()) {
            etName.setError("Enter username");
            return;
        } else if (strPassword.isEmpty()) {
            etPassword.setError("Enter password");
            return;
        }

        if (strName.equals(HARDCODED_USERNAME) && strPassword.equals(HARDCODED_PASSWORD)) {
            Intent intent = new Intent(this, LandingScreenActivity.class);
            intent.putExtra("USERNAME", strName);
            startActivity(intent);
            finish();
        } else {
            TextView tvMessage = findViewById(R.id.tvMessage);
            tvMessage.setText(R.string.invalid_credentials);
        }
    }
}
