package me.chenhe.wearvisiondemo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import me.chenhe.wearvision.dialog.AlertDialog
import me.chenhe.wearvision.util.enableRsbSupport
import me.chenhe.wearvision.util.postRequestFocus
import me.chenhe.wearvisiondemo.databinding.AtyChoiceDialogBinding

class ChoiceDialogsAty : Activity() {

    private lateinit var binding: AtyChoiceDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AtyChoiceDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = arrayOf("🍎Apple", "🍊Orange", "🍇Grape", "🍑Peach", "🍐Pear")

        val adapter = SimpleAdapter { position ->
            when (position) {
                0 -> AlertDialog(this).apply {
                    title = "Choice"
                    message = "Which fruit do you like best?"
                    enableCancelTip()
                    setSingleChoiceItems(items, -1) { _, _ ->
                        toast(items[getCheckedItemPosition()])
                        dismiss()
                    }
                }.show()
                1 -> AlertDialog(this).apply {
                    title = "Choice"
                    message = "Select fruits you like."
                    enableCancelTip()
                    setMultiChoiceItems(items, listOf(1, 2)) { _, pos, checked ->
                        Log.i("Choice", items[pos] + if (checked) "checked" else "unchecked")
                    }
                    setPositiveButtonIcon(R.drawable.dialog_pos) { _, _ ->
                        val str = getCheckedItemPositions()?.joinToString { items[it] } ?: "None"
                        toast(str)
                    }
                }.show()
            }
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.enableRsbSupport()

        val data = mutableListOf("Single Choice", "Multiple Choice")
        adapter.submitList(data)

    }

    private fun AlertDialog.enableCancelTip() {
        setOnCancelListener {
            toast("cancel")
        }
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        binding.recyclerView.postRequestFocus()
    }

}