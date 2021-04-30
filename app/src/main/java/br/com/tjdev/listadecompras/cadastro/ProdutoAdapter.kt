package br.com.tjdev.listadecompras.cadastro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.com.tjdev.listadecompras.R
import java.text.FieldPosition
import java.text.NumberFormat
import java.util.*

class ProdutoAdapter(contexto: Context): ArrayAdapter<Produto>(contexto, 0) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val v: View

        if(convertView != null) {
            v = convertView
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false)
        }

        val item = getItem(position)

        val txtName = v.findViewById<TextView>(R.id.name_product)
        val txtQuantity = v.findViewById<TextView>(R.id.quantity_product)
        val txtPrice = v.findViewById<TextView>(R.id.price_product)

        //obtendo a instância do objeto de formatação
        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

        txtName.text = item?.name
        txtQuantity.text = item?.quantity.toString()
        txtPrice.text = item?.price.toString()
        //formatando a variável no formato moeda
        txtPrice.text = f.format(item?.price)

        return v
    }

}