package me.chenhe.wearvisiondemo

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import me.chenhe.wearvision.dialog.AlertDialog
import me.chenhe.wearvision.util.enableRsbSupport
import me.chenhe.wearvision.util.postRequestFocus
import me.chenhe.wearvisiondemo.databinding.AtyDialogBinding

class DialogsAty : Activity() {

    private lateinit var binding: AtyDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AtyDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = SimpleAdapter { position ->
            when (position) {
                0 -> AlertDialog(this).apply {
                    title = "Dialog"
                    message = "This is an ordinary dialog. This is an ordinary dialog. \n\n" +
                            "Slide to dismiss."
                    enableCancelTip()
                }.show()
                1 -> AlertDialog(this).apply {
                    title = "Dialog"
                    message = "This is a dialog with single button."
                    enableCancelTip()
                    enablePositiveTip()
                }.show()
                2 -> AlertDialog(this).apply {
                    title = "Dialog"
                    message = "This is a dialog with 2 buttons."
                    enableCancelTip()
                    enablePositiveTip()
                    enableNegativeTip()
                }.show()
                3 -> AlertDialog(this).apply {
                    val b = StringBuilder(50)
                    for (i in 0 until 10)
                        b.append("This is a long text dialog.\n")
                    title = "Dialog"
                    message = b.toString()
                    enableCancelTip()
                    enablePositiveTip()
                    enableNegativeTip()
                }.show()
                4 -> AlertDialog(this).apply {
                    title = "Dialog"
                    message =
                        "This is a dialog with an icon. This is a dialog with an icon. This is a dialog with an icon."
                    icon = AppCompatResources.getDrawable(this@DialogsAty, R.drawable.warning)
                    enableCancelTip()
                    enablePositiveTip()
                    enableNegativeTip()
                }.show()
                5 -> AlertDialog(this).apply {
                    title = "Dialog"
                    message =
                        "This is a dialog with a check box. This is a dialog with a check box. This is a dialog with a check box."
                    skipText = "Keep my choice"
                    showSkipLayout = true
                    enableCancelTip()
                    enableNegativeTip()
                    setPositiveButtonIcon(R.drawable.dialog_pos) { _, _ ->
                        toast("positive + $isSkipChecked")
                    }
                }.show()

            }
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.enableRsbSupport()

        val data =
            mutableListOf(
                "Ordinary",
                "Single Button",
                "Two Button",
                "Long Text",
                "Title Icon",
                "Check Box"
            )
        adapter.submitList(data)

    }

    private fun AlertDialog.enableCancelTip() {
        setOnCancelListener {
            toast("cancel")
        }
    }

    private fun AlertDialog.enablePositiveTip() {
        setPositiveButtonIcon(R.drawable.dialog_pos) { _, _ ->
            toast("positive")
        }
    }

    private fun AlertDialog.enableNegativeTip() {
        setNegativeButtonIcon(R.drawable.dialog_nega) { _, _ ->
            toast("negative")
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