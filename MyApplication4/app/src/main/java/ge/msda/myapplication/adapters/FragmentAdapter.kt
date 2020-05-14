package ge.msda.myapplication.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val fragmentList = arrayListOf<Fragment>()

    fun setItems(screens: List<Fragment>) {
        this.fragmentList.apply {
            clear()
            addAll(screens)
            notifyDataSetChanged()
        }
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}
