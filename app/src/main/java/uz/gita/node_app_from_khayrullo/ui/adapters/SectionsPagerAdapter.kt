package uz.gita.node_app_from_khayrullo.ui.adapters

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import uz.gita.node_app_from_khayrullo.R
import uz.gita.node_app_from_khayrullo.ui.screens.AllNotesScreen
import uz.gita.node_app_from_khayrullo.ui.screens.PersonalNotesScreen
import uz.gita.node_app_from_khayrullo.ui.screens.StudyNotesScreen
import uz.gita.node_app_from_khayrullo.ui.screens.WorkNotesScreen

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    @StringRes
    private val tabTitles = intArrayOf(R.string.tab_all, R.string.tab_personal, R.string.tab_work, R.string.tab_study)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment =
                AllNotesScreen()
            1 -> fragment =
                PersonalNotesScreen()
            2 -> fragment =
                WorkNotesScreen()
            3 -> fragment =
                StudyNotesScreen()
        }
        return fragment as Fragment
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(tabTitles[position])
    }

    override fun getCount(): Int {
        return 4
    }
}