package xyz.bhabra.farmaps_votuporanga;

import android.content.Intent;
import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button btnCadastro = (Button)findViewById(R.id.btnSalvar);
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText edtNome = (EditText)findViewById(R.id.edtNome);
                EditText edtEmail = (EditText)findViewById(R.id.edtEmail);

                Ion.with(getBaseContext()).load("http://farmacias.bhabra.xyz/projeto/inserir.php")
                        .setBodyParameter("nome", edtNome.getText().toString())
                        .setBodyParameter("email", edtEmail.getText().toString())
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (result.get("retorno").getAsString().equals("YES")){
                                    Toast.makeText(getBaseContext(),"Cliente cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getBaseContext(), ListarActivity.class));
                                }
                            }
                        });

                /*try {
                    StringBuilder strURL = new StringBuilder();
                    strURL.append("http://farmacias.bhabra.xyz/projeto/inserir.php?");
                    strURL.append("nome=");
                    strURL.append(URLEncoder.encode(edtNome.getText().toString(), "UTF-8"));
                    strURL.append("&email=");
                    strURL.append(URLEncoder.encode(edtEmail.getText().toString(), "UTF-8"));
                    new HttpRequest().execute(strURL.toString());
                }catch (Exception ex){

                }*/
            }
        });
    }

    private class HttpRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String retorno = null;
            try {
                String urlHttp = params[0];
                URL url = new URL(urlHttp);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                InputStreamReader ips = new InputStreamReader(http.getInputStream());
                BufferedReader bfr = new BufferedReader(ips);
                retorno = bfr.readLine();
            }catch (Exception ex){

            }
            return retorno;
        }

        protected void onPostExecute(String retorno){
            if (retorno.equals("NO")){
                Toast.makeText(getBaseContext(),"Erro ao cadastrar o cliente.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getBaseContext(),"Cliente cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(), ListarActivity.class));
            }

        }

    }

}
