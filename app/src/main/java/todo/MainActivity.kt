package todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import todo.domain.usescases.CreateProjectUC

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.todo.R.layout.activity_main)

    }
}