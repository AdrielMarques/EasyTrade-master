package br.com.interaje.easytrade.repositories;

import android.content.Context;

import java.util.List;

import br.com.interaje.easytrade.database.Database;
import br.com.interaje.easytrade.model.Produto;

/**
 * Created by charles on 21/11/15.
 */
public interface ProdutoDAO {

    boolean salvar(Produto produto, Database database);

    List<Produto> findAll(Context context, Database database);

    void remove(Long id, Context context, Database database);
}
