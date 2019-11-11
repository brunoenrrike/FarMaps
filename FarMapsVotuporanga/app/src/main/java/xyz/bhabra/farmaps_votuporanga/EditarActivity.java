package xyz.bhabra.farmaps_votuporanga;

import android.content.Intent;
import android.net.Uri;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class EditarActivity extends AppCompatActivity {

    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Intent intent = getIntent();

        final EditText edtNome = (EditText)findViewById(R.id.edtNome);
        final EditText edtEmail = (EditText)findViewById(R.id.edtEmail);
        final EditText edtLink = (EditText)findViewById(R.id.edtLink);

        codigo = intent.getStringExtra("codigo");

        edtNome.setText(intent.getStringExtra("nome"));
        edtEmail.setText(intent.getStringExtra("email"));
        edtLink.setText(intent.getStringExtra("link"));


        Button btnAtualizar = (Button) findViewById(R.id.btnAtualizar);
        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ion.with(getBaseContext())
                        .load("http://farmacias.bhabra.xyz/projeto/atualizar.php")
                        .setBodyParameter("codigo", codigo)
                        .setBodyParameter("nome", edtNome.getText().toString())
                        .setBodyParameter("email", edtEmail.getText().toString())
                        .setBodyParameter("link", edtLink.getText().toString())
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (result.get("retorno").getAsString().equals("NO")){
                                    Toast.makeText(getBaseContext(),"Erro ao alterar o cliente.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getBaseContext(),"Cliente alterado com sucesso.", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
            }
        });

        Button btnApagar = (Button) findViewById(R.id.btnApagar);
        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ion.with(getBaseContext())
                        .load("http://farmacias.bhabra.xyz/projeto/deletar.php")
                        .setBodyParameter("codigo", codigo)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (result.get("retorno").getAsString().equals("NO")){
                                    Toast.makeText(getBaseContext(),"Erro ao excluir o cliente.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getBaseContext(),"Cliente excluido com sucesso.", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
            }
        });

    }
    /*public void maps (View view){
        EditText searchTextField = (EditText) view.findViewById(R.id.txvLink);

        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + searchTextField.getText().toString());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }*/
}
