package br.com.interaje.easytrade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CadastroCliente extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNome_Cliente, edtEmail, edtTelefone;
    Button btnOk;
    Long id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public CadastroCliente(){
        this.id = 0l;

        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

        edtNome_Cliente = (EditText) findViewById(R.id.edtNome_Cliente);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);





    }

    @Override
    public void onClick(View v) {

    }
}
