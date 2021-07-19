package me.chenhe.wearvision.preference

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.recyclerview.widget.RecyclerView
import me.chenhe.wearvision.R
import me.chenhe.wearvision.util.enableRsbSupport
import kotlin.math.max

private const val DIALOG_FRAGMENT_TAG = "wearvision.preference.PreferenceFragment.DIALOG"

/**
 * A subclass of [PreferenceFragmentCompat] in AndroidX. You should always consider using this implement.
 *
 * This class deals with WearVision's dialog preference and adds padding bottom to recycler view to avoid screen
 * cropping.
 */
abstract class PreferenceFragmentCompat : PreferenceFragmentCompat() {

    private var recyclerView: RecyclerView? = null

    override fun onCreateRecyclerView(
        inflater: LayoutInflater?,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    )
            : RecyclerView {
        val padding = resources.getDimensionPixelOffset(R.dimen.wv_page_vertical_padding)
        return super.onCreateRecyclerView(inflater, parent, savedInstanceState).also { rv ->
            rv.setPadding(
                rv.paddingLeft,
                rv.paddingTop,
                rv.paddingTop,
                max(rv.paddingBottom, padding)
            )
            rv.enableRsbSupport()
            recyclerView = rv
        }
    }

    override fun onResume() {
        super.onResume()
        // resume focus to respond to rsb scrolling
        recyclerView?.post { recyclerView?.requestFocus() }
    }

    @Suppress("DEPRECATION") // we use targetFragment to get preferences, not pass result
    override fun onDisplayPreferenceDialog(preference: Preference?) {
        val handled = when (preference) {
            is ListPreference -> {
                ListPreferenceDialogFragmentCompat.newInstance(preference.key).apply {
                    setTargetFragment(this@PreferenceFragmentCompat, 0)
                }.show(parentFragmentManager, DIALOG_FRAGMENT_TAG)
                true
            }
            else -> false
        }
        if (!handled) {
            super.onDisplayPreferenceDialog(preference)
        }
    }
}