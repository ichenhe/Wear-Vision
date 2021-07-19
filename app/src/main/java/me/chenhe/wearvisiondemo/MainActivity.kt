package me.chenhe.wearvisiondemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import me.chenhe.wearvision.util.enableRsbSupport
import me.chenhe.wearvision.util.postRequestFocus
import me.chenhe.wearvisiondemo.databinding.ActivityMainBinding
import me.chenhe.wearvisiondemo.preference.PreferenceAty

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = SimpleAdapter { position ->
            when (position) {
                0 -> startActivity(Intent(this, TextAppearanceAty::class.java))
                1 -> startActivity(Intent(this, DialogsAty::class.java))
                2 -> startActivity(Intent(this, ChoiceDialogsAty::class.java))
                3 -> startActivity(Intent(this, PreferenceAty::class.java))
            }
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.enableRsbSupport()

        val data = mutableListOf("Text Appearance", "Dialog", "Choice Dialog", "Preference")
        for (i in 1..10)
            data.add("Item $i")
        adapter.submitList(data)

    }

    override fun onResume() {
        super.onResume()
        binding.recyclerView.postRequestFocus()
    }

}