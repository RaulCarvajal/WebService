package com.example.raulrcg.webservice;

import android.os.StrictMode;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by raulrcg on 23/10/17.
 */

public class metodosPost {

    public metodosPost(){
    }

    public boolean eliminar(String link,int id) {
        try {
            String json_string;
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = new URL(link);
            HttpURLConnection connection =  (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            JSONObject json = new JSONObject();
            json.put("idalumno",id+"");
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            writer.write(json.toString());
            writer.flush();
            writer.close();
            os.close();
            int respuesta = 0;
            respuesta = connection.getResponseCode();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                while ((json_string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(json_string + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
            }
        }catch (java.net.MalformedURLException e){
            return false;
        }catch (IOException e) {
            return false;
        }catch (org.json.JSONException e){
            return false;
        }
        return true;
    }

    public boolean actualizar(String link,int id,String name, String address) {
        try {
            String json_string;
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = new URL(link);
            HttpURLConnection connection =  (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            JSONObject json = new JSONObject();
            json.put("idalumno",id+"");
            json.put("nombre",name);
            json.put("direccion",address);
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            writer.write(json.toString());
            writer.flush();
            writer.close();
            os.close();
            int respuesta = 0;
            respuesta = connection.getResponseCode();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                while ((json_string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(json_string + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
            }
        }catch (java.net.MalformedURLException e){
            return false;
        }catch (IOException e) {
            return false;
        }catch (org.json.JSONException e){
            return false;
        }
        return true;
    }

    public boolean insertar(String link,String name, String address) {
        try {
            String json_string;
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = new URL(link);
            HttpURLConnection connection =  (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            JSONObject json = new JSONObject();
            json.put("nombre",name);
            json.put("direccion",address);
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            writer.write(json.toString());
            writer.flush();
            writer.close();
            os.close();
            int respuesta = 0;
            respuesta = connection.getResponseCode();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                while ((json_string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(json_string + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
            }
        }catch (java.net.MalformedURLException e){
            return false;
        }catch (IOException e) {
            return false;
        }catch (org.json.JSONException e){
            return false;
        }
        return true;
    }

}
