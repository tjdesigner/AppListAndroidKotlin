package br.com.tjdev.listadecompras

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import br.com.tjdev.listadecompras.cadastro.ProdutoAdapter
import br.com.tjdev.listadecompras.cadastro.RegisterProductActivity
import android.widget.ArrayAdapter
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btnNavRegister = findViewById<Button>(R.id.btn_nav_register)
        val listProtucts = findViewById<ListView>(R.id.list_item)

        //Cria variável de navegação
        val intent = Intent(this, RegisterProductActivity::class.java)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        //lista de produtos
        val produtosAdapter = ProdutoAdapter(this)
        produtosAdapter.addAll(productGlobal)

        listProtucts.adapter = produtosAdapter

        btnNavRegister.setOnClickListener() {
            startActivity(intent)
        }

        listProtucts.setOnItemLongClickListener { adapter: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = produtosAdapter.getItem(position)
            produtosAdapter.remove(item)
            productGlobal.remove(item)
            onResume()
            true
        }

    }

    override fun onResume() {
        super.onResume()
        val listProtucts = findViewById<ListView>(R.id.list_item)
        val adapter = listProtucts.adapter as ProdutoAdapter
        var textSumTotalProducts = findViewById<TextView>(R.id.txt_total)
        val soma = productGlobal.sumByDouble { it.price * it.quantity }
        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        adapter.clear()
        adapter.addAll(productGlobal)
        textSumTotalProducts.text = f.format(soma)
    }

    //Encerra o app através do hardwareBackbutton
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            finishAffinity()
            true
        } else super.onKeyDown(keyCode, event)
    }
}
