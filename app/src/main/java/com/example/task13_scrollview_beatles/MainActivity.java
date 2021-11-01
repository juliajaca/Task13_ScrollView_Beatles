package com.example.task13_scrollview_beatles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    boolean botonEditar= true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText texto = findViewById(R.id.article);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String mensaje = sharedPref.getString("texto", getString(R.string.texto_prueba));
        Log.d("CONSOLA", mensaje);
        texto.setText(mensaje);
        //SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        //String highScore = sharedPref.getString(getString(R.string.article_text), "ol");
        //String highScore = sharedPref.getString()
        //texto.setText(highScore);

        Button boton = findViewById(R.id.boton);
        activarBotonEditar(texto, boton);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!botonEditar){
                    activarBotonGuardar(texto, boton);
                }else{
                    activarBotonEditar(texto, boton);
                }
                //Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                String texto_editado = texto.getText().toString();

                editor.putString("texto", texto_editado);
                editor.commit();

                String nuevo_texto = sharedPref.getString("texto", "Texto no encontrado");
                Log.d("CONSOLA", nuevo_texto);
                texto.setText(nuevo_texto);
            }
        });
    }


    private void activarBotonEditar(EditText texto,Button boton ){
        texto.setEnabled(false);
        texto.setInputType(InputType.TYPE_NULL);
        texto.setFocusable(false);
        boton.setText(R.string.add_comment);
        botonEditar = false;
    }

    public void activarBotonGuardar(EditText texto,Button boton){
        texto.setEnabled(true);
        texto.setInputType(InputType.TYPE_CLASS_TEXT);
        texto.setFocusable(true);
        boton.setText(R.string.save_comment);
        botonEditar = true;
    }
}