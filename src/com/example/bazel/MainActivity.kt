package com.example.bazel

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Main class for the Bazel Android "XML Parser" app.
 */
 class MainActivity : Activity() {
 	override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listAdapter = ListAdapter()
        val list = findViewById<RecyclerView>(R.id.list)
        list.adapter = listAdapter

        HttpUtils.parse("http://feeds.feedburner.com/blogspot/hsDu")
            .flatMap {
                atom_parse(it)
            }
            // Run on a background thread
            .subscribeOn(Schedulers.io())
            // Be notified on the main thread
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            	{
            		listAdapter.result = it
            	},
            	{ throwable ->
            		Log.e("MainActivity", Log.getStackTraceString(throwable))
            	},
            	{
            		listAdapter.notifyDataSetChanged()
            	}
            )
    }
}