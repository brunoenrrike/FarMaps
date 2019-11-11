package xyz.bhabra.farmaps_votuporanga;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
//import android.support.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Conexao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verificaConexao();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void tentarNovamente(View view){
        verificaConexao();
    }

    public boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager
                .getActiveNetworkInfo() != null && conectivtyManager
                .getActiveNetworkInfo()
                .isAvailable() && conectivtyManager
                .getActiveNetworkInfo()
                .isConnected()) { conectado = true;
            startActivity(new Intent(getBaseContext(), ListarActivity.class));
            finish();

        }
        else {
            conectado = false;
            setContentView(R.layout.activity_conexao);
        }
        return conectado; }
}
