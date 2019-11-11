package xyz.bhabra.farmaps_votuporanga;

import android.content.Intent;
import android.net.Uri;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class ListarActivity extends AppCompatActivity {

    private ArrayAdapter<JsonObject> clientesAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
    }

    protected void onResume(){
        super.onResume();
        clientesAd = new ArrayAdapter<JsonObject>(this,0){

            @Override
            public View getView(int position, View view, ViewGroup viewGroup){
                if(view == null){
                    view = getLayoutInflater().inflate(R.layout.listview_clientes, null);
                }
                JsonObject obj = getItem(position);

                TextView txvNome = (TextView) view.findViewById(R.id.txvNome);
                txvNome.setText(obj.get("nome").getAsString());

                TextView txvEmail = (TextView) view.findViewById(R.id.txvEmail);
                txvEmail.setText(obj.get("email").getAsString());

                TextView txvId = (TextView) view.findViewById(R.id.txvId);
                txvId.setText(obj.get("id").getAsString());

                TextView txvLink = (TextView) view.findViewById(R.id.txvLink);
                txvLink.setText(obj.get("link").getAsString());

                TextView txvFone = (TextView) view.findViewById(R.id.txvFone);
                txvFone.setText(obj.get("fone").getAsString());

                TextView txvStatus = (TextView) view.findViewById(R.id.txvStatus);
                txvStatus.setText(obj.get("status").getAsString());

                /*
                TextView txvHoraInicialSemana = (TextView) view.findViewById(R.id.txvHoraInicialSemana);
                txvHoraInicialSemana.setText(obj.get("h_abre_semana").getAsString());

                TextView txvHoraFinalSemana = (TextView) view.findViewById(R.id.txvHoraFinalSemana);
                txvHoraFinalSemana.setText(obj.get("h_fecha_semana").getAsString());

                TextView txvHoraFinalSemana = (TextView) view.findViewById(R.id.txvHoraFinalSemana);
                txvHoraFinalSemana.setText(obj.get("h_fecha_semana").getAsString());

                TextView txvHoraSabado1 = (TextView) view.findViewById(R.id.txvHoraSabado1);
                txvHoraSabado1.setText(obj.get("h_abre_sabado").getAsString());

                TextView txvHoraSabado2 = (TextView) view.findViewById(R.id.txvHoraSabado2);
                txvHoraSabado2.setText(obj.get("h_fecha_sabado").getAsString());

                TextView txvHoraDomingo1 = (TextView) view.findViewById(R.id.txvHoraDomingo1);
                txvHoraDomingo1.setText(obj.get("h_abre_domingo").getAsString());

                TextView txvHoraDomingo2 = (TextView) view.findViewById(R.id.txvHoraDomingo2);
                txvHoraDomingo2.setText(obj.get("h_fecha_domingo").getAsString());*/

                return view;
            }
        };

        Ion.with(getBaseContext()).load("http://farmacias.bhabra.xyz/projeto/listar.php")
                .asJsonArray().setCallback(new FutureCallback<JsonArray>() {
            @Override
            public void onCompleted(Exception e, JsonArray result) {
                for(int i = 0; i < result.size(); i++){
                    JsonObject jsonObject = result.get(i).getAsJsonObject();
                    clientesAd.add(jsonObject);
                }

                ListView ltwClientes = findViewById(R.id.ltwClientes);
                ltwClientes.setAdapter(clientesAd);

                ltwClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        JsonObject obj = (JsonObject) parent.getItemAtPosition(position);
                        String codigo = obj.get("id").getAsString();
                        String nome = obj.get("nome").getAsString();
                        String email = obj.get("email").getAsString();
                        String link = obj.get("link").getAsString();
                        String fone = obj.get("fone").getAsString();
                        String status = obj.get("status").getAsString();
                        String h_abre_semana = obj.get("h_abre_semana").getAsString();
                        String h_fecha_semana = obj.get("h_fecha_semana").getAsString();
                        String h_abre_sabado = obj.get("h_abre_sabado").getAsString();
                        String h_fecha_sabado = obj.get("h_fecha_sabado").getAsString();
                        String h_abre_domingo = obj.get("h_abre_domingo").getAsString();
                        String h_fecha_domingo = obj.get("h_fecha_domingo").getAsString();


                        //Intent intent = new Intent(getBaseContext(), EditarActivity.class);
                        Intent intent = new Intent(getBaseContext(),Detalhes.class);
                        intent.putExtra("codigo", codigo);
                        intent.putExtra("nome", nome);
                        intent.putExtra("email", email);
                        intent.putExtra("link", link);
                        intent.putExtra("fone", fone);
                        intent.putExtra("status", status);
                        intent.putExtra("h_abre_semana", h_abre_semana);
                        intent.putExtra("h_fecha_semana", h_fecha_semana);
                        intent.putExtra("h_abre_sabado", h_abre_sabado);
                        intent.putExtra("h_fecha_sabado", h_fecha_sabado);
                        intent.putExtra("h_abre_domingo", h_abre_domingo);
                        intent.putExtra("h_fecha_domingo", h_fecha_domingo);

                        startActivity(intent);
                    }
                });
                //JsonObject retorno = result.get(2).getAsJsonObject();
                //Toast.makeText(getBaseContext(), retorno.get("nome").toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void maps (View view){
        TextView searchTextField = (TextView) view.findViewById(R.id.txvLink);

        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + searchTextField.getText().toString());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

}


