package br.com.interaje.easytrade.repositories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import br.com.interaje.easytrade.database.Database;
import br.com.interaje.easytrade.database.ProdutoDatabaseHelper;
import br.com.interaje.easytrade.model.Produto;
import br.com.interaje.easytrade.repositories.ProdutoDAO;

/**
 * Created by charles on 21/11/15.
 */
public class ProdutoDAOImpl implements ProdutoDAO {

    private String tableDAO = "produto";
    /**
     * Inserir
     */
    @Override
    public boolean salvar(Produto produto, Database database) {
        ContentValues cv = new ContentValues();
        database.open();
        try {
            if (produto != null) {
                cv.put(ProdutoDatabaseHelper.COLUMN_NOME, produto.getNome());
                cv.put(ProdutoDatabaseHelper.COLUMN_VALOR, produto.getValor());
                cv.put(ProdutoDatabaseHelper.COLUMN_QUANTIDADE, produto.getQuantidade());
                cv.put(ProdutoDatabaseHelper.COLUMN_DESCRICAO, produto.getDescricao());
                cv.put(ProdutoDatabaseHelper.COLUMN_FOTO, produto.getFoto());

                if(produto.getId() == 0l) {
                    database.get().insert(tableDAO, null, cv);
                }else {
                    database.get().update(tableDAO, cv, "_id=?", new String[]{String.valueOf(produto.getId())});
                }
            }
        } catch (Exception e) {
            Log.d("ProdutoDAOImpl",
                    "Method: salvar().\nErro ao inserir dados do banco. Causa: "
                            + e.getCause());

            database.close();
            return false;
        }

        database.close();
        return true;

    }

    /**
     * Listar todos
     */
    @Override
    public ArrayList<Produto> findAll(Context context, Database database) {
        Cursor cursor = null;
        database.open();

        try {
            cursor = database.get().rawQuery("select * from "+tableDAO+" order by nome", null);
        } catch (Exception e) {

            Log.d("CarDAOImpl",
                    "Method: findall().\nErro ao recuperar dados do banco. Causa: "
                            + e.getMessage());

        }


        ArrayList<Produto> listEntity = null;
        Produto entity;
        cursor.moveToFirst();

        if (cursor != null && !cursor.isClosed()) {
            listEntity = new ArrayList<Produto>();
            while (cursor.moveToNext()) {
                entity = new Produto();
                entity.setId(cursor.getLong(cursor.getColumnIndex(ProdutoDatabaseHelper.COLUMN_ID)));
                entity.setNome(cursor.getString(cursor.getColumnIndex(ProdutoDatabaseHelper.COLUMN_NOME)));
                entity.setValor(cursor.getDouble(cursor.getColumnIndex(ProdutoDatabaseHelper.COLUMN_VALOR)));
                entity.setQuantidade(cursor.getInt(cursor.getColumnIndex(ProdutoDatabaseHelper.COLUMN_QUANTIDADE)));
                entity.setDescricao(cursor.getString(cursor.getColumnIndex(ProdutoDatabaseHelper.COLUMN_DESCRICAO)));
                entity.setFoto(cursor.getBlob(cursor.getColumnIndex(ProdutoDatabaseHelper.COLUMN_FOTO)));

                listEntity.add(entity);
            }
            cursor.close();
        }

        database.close();
        return listEntity;
    }

    /**
     * Remover
     */
    @Override
    public void remove(Long id, Context context, Database database) {
        try {
            database.open();
            // (TABELA, COLUNA, WHERE CLAUSE)
            database.get().delete(tableDAO, "id",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d("ProdutoDAOImpl",
                    "Method: remove().\nErro ao remover dados do banco. Causa: "
                            + e.getMessage());
        } finally {
            database.close();
        }
    }

}    