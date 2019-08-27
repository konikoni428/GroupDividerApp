package com.masahiro.groupdevider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    public val nameDataset : MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = NameAdapter(nameDataset)

        recyclerView = findViewById<RecyclerView>(R.id.name_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        add_button.setOnClickListener {
            if(!name_text.text.isNullOrEmpty()){
                nameDataset.add(name_text.text.toString())
                name_text.text.clear()
                viewAdapter.notifyDataSetChanged()
            }
        }

        submit_button.setOnClickListener {
            if(!nameDataset.isNullOrEmpty()) {
//                val intent = Intent(this, ResultActivity::class.java)
//                intent.putExtra(ResultActivity.NAME_DATASET, nameDataset.toTypedArray())
//                startActivity(intent)
                val intent = Intent(this, ResultTabActivity::class.java)
                intent.putExtra(ResultTabActivity.NAME_DATASET, nameDataset.toTypedArray())
                val divideNum = Integer.parseInt(number_text.text.toString())
                intent.putExtra(ResultTabActivity.DIVIDE_NUM, divideNum)
                startActivity(intent)
            }
        }
    }


}
