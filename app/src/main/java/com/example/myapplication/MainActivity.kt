package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnItemClickListener{
    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter:Adapter
    private lateinit var webView: WebView
    private val lista = mutableListOf<Resultado>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerview()
        getEmailPopular()
        webView = findViewById(R.id.wb)
        webView.loadUrl("https://www.google.co.in/")
       // web()
    }


    private fun initRecyclerview(){
        adapter = Adapter(lista, this)
        findViewById<RecyclerView>(R.id.listadoRv).layoutManager= LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.listadoRv).adapter = adapter
        val recycler = findViewById<RecyclerView>(R.id.listadoRv)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.nytimes.com/svc/mostpopular/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    private fun getEmailPopular(){
        CoroutineScope(Dispatchers.IO).launch {
            //https://api.nytimes.com/svc/mostpopular/v2/emailed/7.json?api-key=lm7xxH8g2OvFOTFBSYNbWQSHn2acIW9D
            //QAZ1Wvu3QIlniUfYfo7tvGAmGB8r4lui
            val call:Response<Respuesta> = getRetrofit().create(APIService::class.java).getResponse("emailed/7.json?api-key=lm7xxH8g2OvFOTFBSYNbWQSHn2acIW9D")
            val nytResponse: Respuesta? = call.body()
            val res: List<Resultado> = nytResponse?.results ?: emptyList()
            runOnUiThread {
                if (nytResponse != null) {
                  //  findViewById<TextView>(R.id.tv).text = nytResponse.results.first().title
                      //limpiando la lista de artículos
                    lista.clear()
                    //llenando la lista
                    lista.addAll(res)
                    //notificando el adaptador
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onItemClick(noticia:Resultado) {


         findViewById<WebView>(R.id.wb).loadUrl(noticia.url)
    }

    //override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
      //  val webView = findViewById<WebView>(R.id.wb).loadUrl(noticia.url)
    //}


}