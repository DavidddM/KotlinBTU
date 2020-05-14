package ge.msda.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import ge.msda.myapplication.adapters.FragmentAdapter
import ge.msda.myapplication.fragments.DashboardFragment
import ge.msda.myapplication.fragments.HomeFragment
import ge.msda.myapplication.fragments.NotificationFragment
import ge.msda.myapplication.fragments.ToastFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val fragmentList: ArrayList<Fragment> = arrayListOf(
            HomeFragment(),
            DashboardFragment(),
            NotificationFragment.newInstance(-1),
            ToastFragment()
        )

        val adapter = FragmentAdapter(
            supportFragmentManager
        )

        adapter.setItems(fragmentList)

        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    pager.setCurrentItem(0, false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    pager.setCurrentItem(1, false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_notification -> {
                    pager.setCurrentItem(2, false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_toast -> {
                    pager.setCurrentItem(3, false)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                val array = arrayListOf(
                    R.id.navigation_home,
                    R.id.navigation_dashboard,
                    R.id.navigation_notification,
                    R.id.navigation_toast
                )
                navView.selectedItemId = array[position]
            }
        })

        pager.adapter = adapter
    }
}