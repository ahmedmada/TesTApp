package com.swensonhe.test.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.swensonhe.test.databinding.ActivityMainBinding
import com.swensonhe.test.model.Item
import com.swensonhe.test.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)

        initObserver()
    }

    private fun initObserver() {
        mainViewModel.items.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {

                    binding.list.visibility = View.VISIBLE
                    it.data.let { res ->
                        binding.list.apply {
                            adapter = ItemsAdapter(res as ArrayList<Item>, this@MainActivity)
                        }
                    }
                }

                Status.LOADING -> {
                    binding.list.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.list.visibility = View.VISIBLE
                }
            }
        }

    }

}