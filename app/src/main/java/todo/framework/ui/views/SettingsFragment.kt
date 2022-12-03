package todo.framework.ui.views

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.LogingPageBinding
import com.example.todo.databinding.SettingsPageBinding
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment:Fragment(R.layout.settings_page) {
    private var _binding: SettingsPageBinding? = null
    private val binding get()= _binding!!
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SettingsPageBinding.bind(view)
        navController = findNavController()
        binding.btnSingOut.setOnClickListener {
             val pref = requireContext().getSharedPreferences(ProjectFragment.PREFNAME,Context.MODE_PRIVATE).edit()
            pref.clear()
            pref.apply()
            FirebaseAuth.getInstance().signOut()
           navController.navigate(R.id.loginFragment)
        }
    }
}