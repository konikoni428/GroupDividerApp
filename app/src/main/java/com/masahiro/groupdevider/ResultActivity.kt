package com.masahiro.groupdevider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//旧バージョン　使っていない
class ResultActivity : AppCompatActivity() {
    companion object{
        const val NAME_DATASET = "com.masahiro.groupdevider.name_dataset"
        val TAG = ResultActivity::class.java.simpleName
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter1: RecyclerView.Adapter<*>
    private lateinit var viewAdapter2: RecyclerView.Adapter<*>
    private lateinit var viewManager1: RecyclerView.LayoutManager
    private lateinit var viewManager2: RecyclerView.LayoutManager

    private lateinit var Dataset1 : List<String>
    private lateinit var Dataset2 : List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        viewManager1 = LinearLayoutManager(this)
        viewManager2 = LinearLayoutManager(this)

        val nameDataset = intent.getStringArrayExtra(NAME_DATASET) ?: arrayOf()

        devideGroup(nameDataset.toList())

        viewAdapter1 = NameResultAdapter(Dataset1)
        viewAdapter2 = NameResultAdapter(Dataset2)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view_result_1).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager1
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter1
        }

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view_result_2).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager2
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter2
        }

    }

    fun devideGroup(nameDataSet: List<String>){
        //要素数４ならばsize=4
        val datasize = nameDataSet.size
        Log.d(TAG, "size: $datasize")

        val shuffledNameDataset = nameDataSet.shuffled()
        val chunkedNamedDataset = shuffledNameDataset.chunked((datasize+1)/2) //切りあげ

        Dataset1 = chunkedNamedDataset[0]
        Dataset2 = chunkedNamedDataset[1]
    }
}
