package ge.msda.myapplication.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import ge.msda.myapplication.R
import kotlinx.android.synthetic.main.fragment_notification.view.*
import kotlinx.android.synthetic.main.fragment_toast.*

class ToastFragment: Fragment(R.layout.fragment_toast) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button2.setOnClickListener {
            Toast.makeText(view.context,"Hello. I'm a toast!",Toast.LENGTH_SHORT).show()
        }
    }
}