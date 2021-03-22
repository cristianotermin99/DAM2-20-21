package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    int contador =0;
    Button buttonToast;
    Button buttonReset;
    Button buttonContador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textview_contador);
        buttonToast=findViewById(R.id.button_toast);
        buttonToast.setOnClickListener(new onClickListener());
        buttonReset=findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new onClickListener2());
        buttonContador=findViewById(R.id.button_contador);
        buttonContador.setOnClickListener(new onClickListener3());
        //nnnnn
    }

    public void showToast(View view){
        int duration= Toast.LENGTH_SHORT;
        Toast toast=Toast.makeText(this, "hola toast", duration);
        toast.show();
    }

    public void reset(View view){
        contador=0;
        textView.setText(Integer.toString(contador));
    }

    public void sumaContador(View view){
        textView.setText(Integer.toString(++contador));
    }

    private class onClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            showToast(v);
        }
    }
    private class onClickListener2 implements View.OnClickListener{
        @Override
        public void onClick(View v){
            reset(v);
        }
    }
    private class onClickListener3 implements View.OnClickListener{
        @Override
        public void onClick(View v){
            sumaContador(v);
        }
    }
}