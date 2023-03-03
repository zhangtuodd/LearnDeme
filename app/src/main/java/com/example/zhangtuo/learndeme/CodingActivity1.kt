package com.example.zhangtuo.learndeme

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.GridView
import android.widget.TextView

/**
 * 描述信息
 *
 * @author zhangtuo
 * @date 2023/2/14
 */
class CodingActivity1 : BaseActivity() {
    lateinit var adapter: GridAdapter
    lateinit var keyWord: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coding_layout)
        val editText = findViewById<EditText>(R.id.et)
        val gridView = findViewById<GridView>(R.id.gridview)
        keyWord = findViewById<TextView>(R.id.keyWord)
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 5 && adapter.getDataSize() < 30) {
                    fit(s.toString()).let {
                        setData(it)
                    }
                }
            }
        })
        adapter = GridAdapter(this)
        gridView.adapter = adapter
    }

    private fun fit(input: String): ArrayList<Model> {
        val list = arrayListOf<Model>()
        val word = keyWord.text
        val inputLength = input.length
        val wordLength = keyWord.length()

        for (i in 0 until inputLength) {
            val model = Model(word = input[i].toString())
            if (i < wordLength && word[i] == input[i]) {
                model.color = resources.getColor(R.color.green,null)
            } else if (word.contains(input[i])) {
                model.color = resources.getColor(R.color.yellow_30,null)
            } else {
                model.color = resources.getColor(R.color.gray_20,null)
            }
            list.add(model)
        }
        return list
    }

    fun setData(list: List<Model>) {
        adapter.addData(list)
    }
}

class Model(var word: String? = "", var color: Int? = 0)

class GridAdapter(val context: CodingActivity1) : BaseAdapter() {
    val list = arrayListOf<Model>()
    override fun getCount() = list.size

    override fun getItem(position: Int) = list[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView: TextView =
            LayoutInflater.from(context).inflate(R.layout.grid_item_layout, null) as TextView
        list[position].let {
            textView.text = it.word
            it.color?.let {
                textView.setBackgroundColor(it)
            }

        }
        return textView
    }

    fun addData(list: List<Model>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun getDataSize() = list.size
}