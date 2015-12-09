package br.com.interaje.easytrade.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.adapter.ClienteAdapter;
import br.com.interaje.easytrade.model.Cliente;

public class ListCliente extends AppCompatActivity {

    private ListView listCliente;
    private ClienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListCliente.this, CadastroCliente.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }


    private void inicializarElementos(){

        listCliente = (ListView) findViewById(R.id.listCliente);


        listCliente.setAdapter(adapter);

        listCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ListCliente.this, AddProduto.class);
                Cliente cliente= (Cliente) adapter.getItem(position);


                intent.putExtra("nome", cliente.getNome_Cliente());
                intent.putExtra("email", cliente.getEmail());
                intent.putExtra("telefone", cliente.getTelefone());


                startActivity(intent);

            }
        });


    }


}
