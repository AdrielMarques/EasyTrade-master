package br.com.interaje.easytrade.repositories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import br.com.interaje.easytrade.database.ClienteDatabase;
import br.com.interaje.easytrade.database.ProdutoDatabase;
import br.com.interaje.easytrade.database.ClienteDatabaseHelper;
import br.com.interaje.easytrade.model.Cliente;
import br.com.interaje.easytrade.repositories.ClienteDAO;


/**
 * Created by Adriel on 21/11/15.
 */
public class ClienteDAOImpl implements ClienteDAO{

    private String tableDAO = "cliente";
    /**
     * Inserir
     */
    @Override
    public boolean salvar(Cliente cliente, ClienteDatabase produtoDatabase) {
        ContentValues cv = new ContentValues();
        produtoDatabase.open();
        try {
            if (cliente != null) {
                cv.put(ClienteDatabaseHelper.COLUMN_NOME, cliente.getNome_Cliente());
                cv.put(ClienteDatabaseHelper.COLUMN_EMAIL, cliente.getEmail());
                cv.put(ClienteDatabaseHelper.COLUMN_TELEFONE, cliente.getTelefone());


                if(cliente.getId() == 0l) {
                    produtoDatabase.get().insert(tableDAO, null, cv);
                }else {
                    produtoDatabase.get().update(tableDAO, cv, "_id=?", new String[]{String.valueOf(cliente.getId())});
                }
            }
        } catch (Exception e) {
            Log.d("ClienteDAOImpl",
                    "Method: salvar().\nErro ao inserir dados do banco. Causa: "
                            + e.getCause());

            produtoDatabase.close();
            return false;
        }

        produtoDatabase.close();
        return true;

    }

    /**
     * Listar todos
     */
    @Override
    public ArrayList<Cliente> findAll(Context context, ProdutoDatabase produtoDatabase) {
        Cursor cursor = null;
        produtoDatabase.open();

        try {
            cursor = produtoDatabase.get().rawQuery("select * from "+tableDAO+" order by nome", null);
        } catch (Exception e) {

            Log.d("cliente",
                    "Method: findall().\nErro ao recuperar dados do banco. Causa: "
                            + e.getMessage());

        }


        ArrayList<Cliente> listEntity = null;
        Cliente entity;
        cursor.moveToFirst();

        if (cursor != null && !cursor.isClosed()) {
            listEntity = new ArrayList<Cliente>();
            while (cursor.moveToNext()) {
                entity = new Cliente();
                entity.setId(cursor.getLong(cursor.getColumnIndex(ClienteDatabaseHelper.COLUMN_ID)));
                entity.setNome_Cliente(cursor.getString(cursor.getColumnIndex(ClienteDatabaseHelper.COLUMN_NOME)));
                entity.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDatabaseHelper.COLUMN_EMAIL)));
                entity.setTelefone(cursor.getString(cursor.getColumnIndex(ClienteDatabaseHelper.COLUMN_TELEFONE)));


                listEntity.add(entity);
            }
            cursor.close();
        }

        produtoDatabase.close();
        return listEntity;
    }

    /**
     * Remover
     */
    @Override
    public void remove(Long id, Context context, ProdutoDatabase produtoDatabase) {
        try {
            produtoDatabase.open();
            // (TABELA, COLUNA, WHERE CLAUSE)
            produtoDatabase.get().delete(tableDAO, "_id",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d("ClienteDAOImpl",
                    "Method: remove().\nErro ao remover dados do banco. Causa: "
                            + e.getMessage());
        } finally {
            produtoDatabase.close();
        }
    }

}    