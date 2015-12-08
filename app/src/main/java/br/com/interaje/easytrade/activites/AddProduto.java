package br.com.interaje.easytrade.activites;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.database.Database;
import br.com.interaje.easytrade.database.ProdutoDatabaseHelper;
import br.com.interaje.easytrade.model.Produto;
import br.com.interaje.easytrade.repositories.ProdutoDAO;
import br.com.interaje.easytrade.repositories.impl.ProdutoDAOImpl;

public class AddProduto extends AppCompatActivity implements View.OnClickListener{

    private Button salvar;
    private EditText nome, valor, quantidade, descricao;
    private byte[] foto;
    private Bundle extras;
    private Long id;
    private ImageButton btnCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produto);

        this.inicializarElementos();
    }

    private void inicializarElementos(){
        this.id = 0l;

        btnCamera = (ImageButton) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(this);

        salvar = (Button) findViewById(R.id.salvar);
        salvar.setOnClickListener(this);

        nome = (EditText) findViewById(R.id.nome);
        valor = (EditText) findViewById(R.id.valor);
        quantidade = (EditText) findViewById(R.id.quantidade);
        descricao = (EditText) findViewById(R.id.descricao);

        extras = getIntent().getExtras();

        if(extras != null){
            inicializarElementosExtras();
        }

    }

    private void inicializarElementosExtras(){

        id = extras.getLong("_id");
        nome.setText(extras.getString("nome"));
        valor.setText(String.valueOf(extras.getDouble("valor")));
        quantidade.setText(String.valueOf(extras.getInt("quantidade")));
        descricao.setText(extras.getString("descricao"));
        foto = extras.getByteArray("foto");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.salvar:

                Produto produto = new Produto();

                //verificando se os valores foram informados
                if (!nome.getText().toString().equals("")
                        && !valor.getText().toString().equals("")
                        && !quantidade.getText().toString().equals("")
                        && foto != null) {

                    produto.setId(id);
                    produto.setNome(nome.getText().toString());
                    produto.setValor(Double.parseDouble(valor.getText().toString()));
                    produto.setQuantidade(Integer.parseInt(quantidade.getText().toString()));
                    produto.setDescricao(descricao.getText().toString());
                    produto.setFoto(foto);

                    //salvando no banco
                    if(salvarProduto(produto)){
                        Toast.makeText(this, "Salvo com sucesso.", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(this, ListProduto.class));
                        finish();
                    }else{
                        Toast.makeText(this, "Ocorreu um problema ao salvar! Tente novamente.", Toast.LENGTH_SHORT).show();
                    }

                }else{

                    Toast.makeText(this, "Nome, valor, quantidade e foto são obrigatórios! Informe-os.", Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.btnCamera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
                break;
        }
    }

    private boolean salvarProduto(Produto produto) {
        Database database = new Database(new ProdutoDatabaseHelper(this));
        ProdutoDAO dao = new ProdutoDAOImpl();

        return dao.salvar(produto, database);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap photo = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
        foto = stream.toByteArray();
    }
}
