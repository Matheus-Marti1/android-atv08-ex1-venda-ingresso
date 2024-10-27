/*
@author:<Matheus Augusto Marti>
 */


package br.edu.fateczl.atv8ex1ingressos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.edu.fateczl.atv8ex1ingressos.model.Ingresso;
import br.edu.fateczl.atv8ex1ingressos.model.IngressoVip;

public class VendaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_venda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvResCompra = findViewById(R.id.tvResCompra);
        tvResCompra.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        Button btnVoltar = findViewById(R.id.btnVoltar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String tipo = bundle.getString("tipo");
        Ingresso ingresso;
        if (tipo.equals("vip")){
            ingresso = new IngressoVip();
        } else {
            ingresso = new Ingresso();
        }
        ingresso.setIdentificador(bundle.getString("id"));
        ingresso.setValor(bundle.getFloat("valor"));
        float taxa = bundle.getFloat("taxa");
        float valorFinal = bundle.getFloat("valorFinal");
        BigDecimal valFinal = BigDecimal.valueOf(valorFinal).setScale(2, RoundingMode.HALF_UP);
        String resCompra = tipo.toUpperCase() + "\n" + ingresso.getIdentificador() + "\nValor: R$" + ingresso.getValor() + "\nTaxa: R$" + taxa + "\nValor Final: R$" + valFinal;
        tvResCompra.setText(resCompra);
        btnVoltar.setOnClickListener(op -> voltar());
    }

    private void voltar() {
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
        this.finish();
    }
}