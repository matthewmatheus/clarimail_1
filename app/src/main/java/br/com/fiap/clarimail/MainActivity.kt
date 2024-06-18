// MainActivity.kt
package br.com.fiap.clarimail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emailAdapter: EmailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewEmails)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dbHelper = DbHelper(this)
        val emails = dbHelper.getAllEmails()

        emailAdapter = EmailAdapter(emails)
        recyclerView.adapter = emailAdapter
    }
}
