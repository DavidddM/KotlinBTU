package ge.msda.myapplication.fargments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ge.msda.myapplication.R
import kotlinx.android.synthetic.main.second_fragment.*

/*
* Created by ნიკოლოზ კაციტაძე on 5/28/20
*/

class SecondFragment : Fragment(R.layout.second_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf {
            it.containsKey("STRING")
        }?.apply { textView2.text = getString("STRING") }

    }

}