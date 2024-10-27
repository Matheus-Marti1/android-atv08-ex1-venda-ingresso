/*
@author:<Matheus Augusto Marti>
 */

package br.edu.fateczl.atv8ex1ingressos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.fateczl.atv8ex1ingressos.model.Ingresso;
import br.edu.fateczl.atv8ex1ingressos.model.IngressoVip;

public class MainActivity extends AppCompatActivity {

    private EditText etId;
    private EditText etValor;
    private EditText etTaxa;
    private CheckBox cbVip;
    private Ingresso ingresso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etId = findViewById(R.id.etId);
        etValor = findViewById(R.id.etValor);
        etTaxa = findViewById(R.id.etTaxa);
        cbVip = findViewById(R.id.cbVip);
        Button btnComprar = findViewById(R.id.btnComprar);
        btnComprar.setOnClickListener(op -> comprar());
    }

    private void comprar() {
        String tipo = "";
        if(cbVip.isChecked()){
            ingresso = new IngressoVip();
            tipo = "vip";
        } else{
            ingresso = new Ingresso();
            tipo = "normal";
        }

        String id = etId.getText().toString();
        float valor = Float.parseFloat(etValor.getText().toString());
        float taxa = Float.parseFloat(etTaxa.getText().toString());
        ingresso.setValor(valor);
        float valorFinal = ingresso.valorFinal(taxa);

       // BigDecimal valFinal = BigDecimal.valueOf(valorFinal).setScale(2, RoundingMode.HALF_UP);

        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("tipo", tipo);
        bundle.putFloat("valor", valor);
        bundle.putFloat("taxa", taxa);
        bundle.putFloat("valorFinal", valorFinal);

        troca(bundle);
    }

    private void troca(Bundle bundle) {
        Intent i = new Intent(this, VendaActivity.class);
        i.putExtras(bundle);
        this.startActivity(i);
        this.finish();
    }
}