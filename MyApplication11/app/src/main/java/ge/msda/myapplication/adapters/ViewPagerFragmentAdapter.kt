package ge.msda.myapplication.adapters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ge.msda.myapplication.fargments.FirstFragment
import ge.msda.myapplication.fargments.SecondFragment
import ge.msda.myapplication.fargments.ThirdFragment

/*
* Created by ნიკოლოზ კაციტაძე on 5/28/20
*/

class ViewPagerFragmentAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment().apply {
                arguments = Bundle().apply {
                    putString("STRING", "123TEXT")
                }
            }
            2 -> ThirdFragment()
            else -> FirstFragment()
        }
    }

}