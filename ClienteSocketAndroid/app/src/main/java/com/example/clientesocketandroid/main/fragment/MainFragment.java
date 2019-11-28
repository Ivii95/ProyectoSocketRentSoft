package com.example.clientesocketandroid.main.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.clientesocketandroid.R;
import com.example.clientesocketandroid.main.activity.ListaPistaActivity;

import static com.example.clientesocketandroid.main.activity.MainActivity.c;


public class MainFragment extends Fragment implements View.OnClickListener {

    Button login;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, true);
    }

    @Override
    public void onStart() {
        super.onStart();
        login = getView().findViewById(R.id.btnLogin);
        login.setOnClickListener(this);

    }

    /*  public void mainViewOnClick(View view){
        setContentView(R.layout.main_activity);
        getSupportFragmentManager().beginTransaction().commit();
    }*/

    @Override
    public void onClick(View view) {
        if (view.getId() == login.getId()) {
            loginTry(view);
        }
    }

    public void loginTry(View view) {
        EditText textField = getView().findViewById(R.id.userFile);
        EditText passField = getView().findViewById(R.id.passwordFile);
        // LLama a un metodo de login
        boolean isLoginCorrect = c.gestionLOG(textField.getText().toString(), passField.getText().toString());
        //isLoginCorrect = UsersRepository.login(textField.getText().toString(),passField.getText().toString());
        /*AlertDialog.Builder dialog =*/
        new AlertDialog.Builder(getActivity())
                .setTitle("Login")
                .setMessage(isLoginCorrect ? "correcto" : "nocorrecto")
                .create();
        if (isLoginCorrect)
            startActivity(new Intent(getActivity(), ListaPistaActivity.class));
        else {
            Toast.makeText(this.getContext(), "Usuario o contrseña incorrecta", Toast.LENGTH_LONG).show();
        }
    }


}
