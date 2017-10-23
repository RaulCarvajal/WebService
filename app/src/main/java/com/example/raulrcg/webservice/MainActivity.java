package com.example.raulrcg.webservice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    AdaptadorItem adp;
    hiloSecundario hsec;
    String link="/datos/obtener_alumnos.php";
    String json_string;
    String nom="",dir="",id="";
    String nombre[],direccion[];
    String ids[];
    int muestra=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        link=getString(R.string.urlNgrok)+link;
        lista=(ListView) findViewById(R.id.lista);

        hsec = new hiloSecundario();
        hsec.execute(link,"1");

        final Intent nv = new Intent(this,verDetalle.class);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nv.putExtra("id", Integer.parseInt(ids[i]));
                startActivity(nv);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),insertar.class);
                startActivity(in);
                //Toast.makeText(getApplicationContext(),R.string.urlNgrok,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.olakace){
            Toast.makeText(getApplicationContext(),"Ola k ase",Toast.LENGTH_SHORT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class hiloSecundario extends AsyncTask<String, Void, String>{
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

                        muestra=jsonObj.getInt("estado");

                        JSONArray arr=jsonObj.getJSONArray("alumnos");
                        for (int k=0;k<arr.length();k++){
                            JSONObject temp=arr.getJSONObject(k);
                            id+=temp.getString("idalumno")+"/";
                            nom+=temp.getString("nombre")+"/";
                            dir+=temp.getString("direccion")+"/";
                        }
                        cadena=id+"\n"+nom+"\n"+dir;
                        //cadena=id+nom+dir;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return  cadena;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            if(s.length()>0){
                String temp[]=s.split("\n");
                //Toast.makeText(getApplicationContext(),temp[2],Toast.LENGTH_SHORT).show();
                ids=temp[0].split("/");
                nombre=temp[1].split("/");
                direccion=temp[2].split("/");
                adp = new AdaptadorItem(getApplicationContext(),nombre,direccion);
                lista.setAdapter(adp);
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


    }
}
