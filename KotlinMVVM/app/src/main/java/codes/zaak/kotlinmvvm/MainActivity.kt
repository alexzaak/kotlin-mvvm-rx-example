package codes.zaak.kotlinmvvm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import codes.zaak.kotlinmvvm.view.MovieListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MovieListFragment()).commit()
        }
    }
}
