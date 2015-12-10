package br.com.interaje.easytrade.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.database.ClienteDatabase;
import br.com.interaje.easytrade.database.ClienteDatabaseHelper;
import br.com.interaje.easytrade.model.Cliente;
import br.com.interaje.easytrade.repositories.ClienteDAO;
import br.com.interaje.easytrade.repositories.impl.ClienteDAOImpl;


public class CadastroCliente extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNome_Cliente, edtEmail, edtTelefone;
    Button btnOk;
    Long id;
    private Bundle extras;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

        public void iniciarElementos(){
        this.id = 0l;

        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

        edtNome_Cliente = (EditText) findViewById(R.id.edtNome_Cliente);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);

            extras = getIntent().getExtras();

            if(extras != null){
                inicializarElementosExtras();
            }

    }

        public void inicializarElementosExtras(){
            id = extras.getLong("_id");
            edtNome_Cliente.setText(extras.getString("nome"));
            edtEmail.setText(extras.getString("email"));
            edtTelefone.setText(extras.getString("telefone"));

        }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.salvar:

                Cliente cliente = new Cliente();

                //verificando se os valores foram informados
                if (!edtNome_Cliente.getText().toString().equals("")
                        && !edtEmail.getText().toString().equals("")
                        && !edtTelefone.getText().toString().equals("")
                        ) {

                    cliente.setId(id);
                    cliente.setNome_Cliente(edtNome_Cliente.getText().toString());
                    cliente.setEmail(edtEmail.getText().toString());
                    cliente.setTelefone(edtTelefone.getText().toString());


                    //salvando no banco
                    if (salvarCliente(cliente)) {
                        Toast.makeText(this, "Salvo com sucesso.", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(this, ListProduto.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Ocorreu um problema ao salvar! Tente novamente.", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(this, "Nome, valor, quantidade e foto são obrigatórios! Informe-os.", Toast.LENGTH_SHORT).show();

                }









                break;
        }


    }

    private boolean salvarCliente(Cliente cliente) {
        ClienteDatabase database = new ClienteDatabase(new ClienteDatabaseHelper(this));
        ClienteDAO dao = new ClienteDAOImpl();

        return dao.salvar(cliente, database);
    }

}
