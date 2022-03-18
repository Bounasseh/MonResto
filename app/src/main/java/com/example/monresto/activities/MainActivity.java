package com.example.monresto.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monresto.R;
import com.example.monresto.persistence.MonRestoDbHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    MonRestoDbHelper monRestoDbHelper = new MonRestoDbHelper(this);
    SharedPreferences preferences;

    boolean isUsernameTyped;
    boolean isPasswordTyped;

    @BindView(R.id.checkBox)
    CheckBox checkBox;

    @BindView(R.id.username)
    EditText usernameEditText;

    @BindView(R.id.password)
    EditText passwordEditText;

    @BindView(R.id.connect)
    Button connectButton;

    @OnTextChanged(R.id.username)
    public void onUsernameEditTextChanged() {
        if (!usernameEditText.getText().toString().isEmpty()) {
            isUsernameTyped = true;
            if (isPasswordTyped) {
                connectButton.setEnabled(true);
                return;
            }
        }
        connectButton.setEnabled(false);
    }

    @OnTextChanged(R.id.password)
    public void onPasswordEditTextChanged() {
        if (!passwordEditText.getText().toString().isEmpty()) {
            isPasswordTyped = true;
            if (isUsernameTyped) {
                connectButton.setEnabled(true);
                return;
            }
        }
        connectButton.setEnabled(false);
    }

    @OnClick(R.id.connect)
    public void onConnectButtonClicked() {
        if (passwordEditText.getText().toString().equals(usernameEditText.getText().toString() + "123")) {

            if(checkBox.isChecked()) {
                preferences.edit().putString("username", usernameEditText.getText().toString()).commit();
                preferences.edit().putString("password", passwordEditText.getText().toString()).commit();
            }

            Toast.makeText(this, "Connecté", Toast.LENGTH_SHORT).show();
            Intent categoriesIntent = new Intent(this, CategoriesActivity.class);
            categoriesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(categoriesIntent);
        } else {
            Toast.makeText(this, "Informations Incorrectes", Toast.LENGTH_SHORT).show();
        }
    }

    private void activateConnectButton() {
        if (usernameEditText.getText().toString().isEmpty() && passwordEditText.getText().toString().isEmpty())
            connectButton.setEnabled(false);
        else {
            isUsernameTyped = true;
            isPasswordTyped = true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (!preferences.getBoolean("isDataImported", false))
        {
            monRestoDbHelper.insert();
            preferences.edit().putBoolean("isDataImported", true).commit();
        }

        if (preferences.getString("username", null) != null && preferences.getString("password", null) != null)
        {
            Intent categoriesIntent = new Intent(this, CategoriesActivity.class);
            categoriesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(categoriesIntent);
        }

        ButterKnife.bind(this);
        activateConnectButton();
    }
}