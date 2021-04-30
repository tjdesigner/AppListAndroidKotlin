package br.com.tjdev.listadecompras.cadastro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import br.com.tjdev.listadecompras.R
import br.com.tjdev.listadecompras.productGlobal

class RegisterProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_product)

        val btnAdd = findViewById<Button>(R.id.btn_add_product)

        val editProduct = findViewById<EditText>(R.id.editName)
        val editQantity = findViewById<EditText>(R.id.editQuantity)
        val editPrice = findViewById<EditText>(R.id.editPrice)

        btnAdd.setOnClickListener() {

            val product = editProduct.text.toString()
            val quantity = editQantity.text.toString()
            val price = editPrice.text.toString()

            if (product.isNotEmpty() && quantity.isNotEmpty() && price.isNotEmpty()) {

                val prod = Produto(product, quantity.toInt(), price.toDouble())

                productGlobal.add(prod)

                editProduct.text.clear()
                editQantity.text.clear()
                editPrice.text.clear()
            } else {
                if (editProduct.text.isEmpty()) {
                    editProduct.error = "Preencha o nome do produto"
                } else {
                    editProduct.error = null
                }
                if (editQantity.text.isEmpty()) {
                    editQantity.error = "Preencha a quantidade"
                } else {
                    editQantity.error = null
                }
                if (editPrice.text.isEmpty()) {
                    editPrice.error = "Preencha o valor"
                } else {
                    editPrice.error = null
                }
            }

        }
    }
}