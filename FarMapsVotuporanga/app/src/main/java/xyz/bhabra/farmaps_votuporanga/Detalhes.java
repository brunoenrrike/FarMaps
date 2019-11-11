package xyz.bhabra.farmaps_votuporanga;

import android.content.Intent;
import android.net.Uri;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Detalhes extends AppCompatActivity {

    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Intent intent = getIntent();

        final TextView edtNome = (TextView)findViewById(R.id.edtNome);
        final TextView edtEmail = (TextView)findViewById(R.id.edtEmail);
        final TextView edtLink = (TextView)findViewById(R.id.txvLink);
        final TextView edtFone = (TextView)findViewById(R.id.txvFone);
        final TextView edtStatus = (TextView)findViewById(R.id.txvStatus);
        final TextView edtHoraInicial = (TextView)findViewById(R.id.txvHoraInicialSemana);
        final TextView edtHoraFinal = (TextView)findViewById(R.id.txvHoraFinalSemana);
        final TextView txvHoraSabado1 = (TextView)findViewById(R.id.txvHoraSabado1);
        final TextView txvHoraSabado2 = (TextView)findViewById(R.id.txvHoraSabado2);
        final TextView txvHoraDomingo1 = (TextView)findViewById(R.id.txvHoraDomingo1);
        final TextView txvHoraDomingo2 = (TextView)findViewById(R.id.txvHoraDomingo2);


        codigo = intent.getStringExtra("codigo");

        edtNome.setText(intent.getStringExtra("nome"));
        edtEmail.setText(intent.getStringExtra("email"));
        edtLink.setText(intent.getStringExtra("link"));
        edtFone.setText(intent.getStringExtra("fone"));
        edtStatus.setText(intent.getStringExtra("status"));
        edtHoraInicial.setText(intent.getStringExtra("h_abre_semana"));
        edtHoraFinal.setText(intent.getStringExtra("h_fecha_semana"));
        txvHoraSabado1.setText(intent.getStringExtra("h_abre_sabado"));
        txvHoraSabado2.setText(intent.getStringExtra("h_fecha_sabado"));
        txvHoraDomingo1.setText(intent.getStringExtra("h_abre_domingo"));
        txvHoraDomingo2.setText(intent.getStringExtra("h_fecha_domingo"));
    }

    public void discador (View view){
        TextView searchTextFieldFone = (TextView) view.findViewById(R.id.txvFone);

        Uri gmmIntentUri = Uri.parse("tel:"+searchTextFieldFone.getText().toString());
        Intent intent = new Intent(Intent.ACTION_DIAL, gmmIntentUri);
        startActivity(intent);
    }


    public void maps (View view){
        TextView searchTextField = (TextView) view.findViewById(R.id.txvLink);

        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + searchTextField.getText().toString());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
