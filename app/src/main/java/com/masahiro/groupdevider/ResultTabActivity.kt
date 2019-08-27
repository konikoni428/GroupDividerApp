package com.masahiro.groupdevider

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.masahiro.groupdevider.ui.main.PageViewModel
import com.masahiro.groupdevider.ui.main.SectionsPagerAdapter
import kotlin.math.ceil

class ResultTabActivity : AppCompatActivity() {
    companion object{
        const val NAME_DATASET = "com.masahiro.groupdevider.name_dataset"
        const val DIVIDE_NUM = "com.masahiro.groupdevider.divide_num"
        val TAG = ResultTabActivity::class.java.simpleName
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_tab)

        val nameDataset = intent.getStringArrayExtra(ResultTabActivity.NAME_DATASET) ?: arrayOf()
        val divideNum = intent.getIntExtra(ResultTabActivity.DIVIDE_NUM, 2)
        val nameDatasets = divideGroup(nameDataset.toList(), divideNum)

        val viewModel = ViewModelProviders.of(this).get(PageViewModel::class.java)
        viewModel.nameDatasets = nameDatasets

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, nameDatasets)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    fun divideGroup(nameDataSet: List<String>, divideNum: Int): Map<Int, List<String>>{
        //要素数４ならばsize=4
        val datasize = nameDataSet.size
        Log.d(ResultActivity.TAG, "size: $datasize")

        val shuffledNameDataset = nameDataSet.shuffled()
        val chunkedNamedDataset:Map<Int, List<String>> = shuffledNameDataset.withIndex().groupBy(
            {it.index % divideNum}, {it.value}
        )

        return chunkedNamedDataset
    }
}