package ge.msda.myapplication

import android.app.Application
import ge.msda.myapplication.api.RetrofitClient

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitClient.initClient()
    }

}