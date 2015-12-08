package br.com.interaje.easytrade.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.model.Cliente;


public class ClienteAdapter extends BaseAdapter{

    private List<Cliente> clienteList;
    private LayoutInflater mLayout;

    public ClienteAdapter(List<Cliente> lista, Context c) {
        clienteList = lista;

        mLayout = LayoutInflater.from(c);

    }

    @Override
    public int getCount() {
        return clienteList.size();
    }

    @Override
    public Object getItem(int position) {
        return clienteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 1l;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mLayout.inflate(R.layout.content_item_list_cliente, parent, false);

        TextView edtNome_Cliente = (TextView) convertView.findViewById(R.id.edtNome_Cliente);
        TextView edtEmail = (TextView) convertView.findViewById(R.id.edtEmail);
        TextView edtTelefone = (TextView) convertView.findViewById(R.id.edtTelefone);

        Cliente cliente = clienteList.get(position);
        DecimalFormat telefoneFormatado;
        String telefone;

        edtNome_Cliente.setText(cliente.getNome_Cliente());
        edtEmail.setText(cliente.getEmail());

        telefoneFormatado = new DecimalFormat("'(00 0-0000.0000");
        telefone = telefoneFormatado.format(cliente.getTelefone());

        edtTelefone.setText(telefone);

        return convertView;
    }
}