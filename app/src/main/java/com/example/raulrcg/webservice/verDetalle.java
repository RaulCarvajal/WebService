package com.example.raulrcg.webservice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class verDetalle extends AppCompatActivity {

    String link="/datos/obtener_alumno_por_id.php?idalumno=";
    String linkdel="/datos/borrar_alumno.php";
    String linkupd="/datos/actualizar_alumno.php";
    int id;
    String nom,dir,json_string;
    hiloSecundario hsec;
    metodosPost mtPost;

    EditText txNom,txDir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_detalle);

        mtPost = new metodosPost();

        id = getIntent().getExtras().getInt("id");
        link=getString(R.string.urlNgrok)+link+id;

        txNom=(EditText)findViewById(R.id.txtNom);
        txDir=(EditText)findViewById(R.id.txtDir);

        hsec = new hiloSecundario();
        hsec.execute(link,"1");
    }

    public void eventoReg(View v){
        regreso();
    }

    public void eventoUpd(View v){
        if(!mtPost.actualizar(getString(R.string.urlNgrok)+linkupd,id,txNom.getText().toString(),txDir.getText().toString())){
            Toast.makeText(this,"No Actualizado",Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this,"Actualizado",Toast.LENGTH_SHORT).show();
        regreso();
    }
    public void eventoDel(View v){
        if(!mtPost.eliminar(getString(R.string.urlNgrok)+linkdel,id)){
            Toast.makeText(this,"No eliminado",Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this,"Eliminado",Toast.LENGTH_SHORT).show();
        regreso();
    }

    private void regreso(){
        Intent nv = new Intent(this,MainActivity.class);
        startActivity(nv);
    }

    public class hiloSecundario extends AsyncTask<String, Void, String> {
        URL url;
        @Override
        protected String doInBackground(String... strings) {
            String cadena = "";
            if (strings[1]== "1"){
                try {
                    url = new URL(link);
                    HttpURLConnection connection = null; // Abrir conexion
                    connection = (HttpURLConnection) url.openConnection();

                    int respuesta = 0;
                    respuesta = connection.getResponseCode();
                    InputStream inputStream = null;
                    inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        while ((json_string = bufferedReader.readLine()) != null) {
                            stringBuilder.append(json_string + "\n");
                        }
                        bufferedReader.close();
                        inputStream.close();
                        connection.disconnect();
                        String temporal = stringBuilder.toString();
                        JSONObject jsonObj = new JSONObject(temporal);
                        JSONObject alumno= jsonObj.getJSONObject("alumno");

                        cadena=alumno.getString("nombre")+"\n"+alumno.getString("direccion");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return  cadena;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.length()>0){
                String [] temp=s.split("\n");
                txNom.setText(temp[0]);
                txDir.setText(temp[1]);
            }
        }
    }
}
