package com.example.raulrcg.webservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class insertar extends AppCompatActivity {
    String link="/datos/insertar_alumno.php";

    EditText txNom,txDir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        link=getString(R.string.urlNgrok)+link;

        txNom=(EditText)findViewById(R.id.nomIn);
        txDir=(EditText)findViewById(R.id.dirIn);

        findViewById(R.id.btnInsertar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodosPost mp=new metodosPost();

                if(!mp.insertar(link,txNom.getText().toString(),txDir.getText().toString())){
                    Toast.makeText(getApplication(),"No insertado",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplication(),"Insertado",Toast.LENGTH_SHORT).show();
                Intent nv = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(nv);
            }
        });
    }
}
