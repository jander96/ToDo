package todo.framework.ui.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.InboxPageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import todo.framework.room.TodoDataBase
import todo.framework.ui.adapters.TaskIboxAdapter
import javax.inject.Inject
@AndroidEntryPoint
class InboxFragment: Fragment(R.layout.inbox_page) {
    private  var _binding:InboxPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController
    @Inject
    lateinit var database : TodoDataBase

    private val listaTestAdapter = listOf("Hacer tiempo para descansar ", "Tomar el sol","Terminar de hacer esta app,"
    ,"Seguir estudiando Lagoritmos","Estudiar el libro en Ingles de Android","Estudiar Ingles","Aprender JEtPackCompose",
    "Crear un bonito diseño para l app","Crear base de datos local con room para cacchear ","Aprender Testing",
    "Acabar por fin de entender la bendita animacion", "Bla bla bla","Un pquito mas de bl abla bla ")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = InboxPageBinding.bind(view)
        navController = findNavController()
        recyclerView = binding.recyclerView
        val adapter = TaskIboxAdapter(listaTestAdapter)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager


        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.inboxFragment, R.id.taskFragment, R.id.labelFragment))

        binding.collapsingToolbar.setupWithNavController(
            binding.toolbar,
            navController,
            appBarConfiguration
        )
        binding.toolbar.setupWithNavController(navController)
        binding.toolbar.inflateMenu(R.menu.main_menu)





    }


    override fun onDestroy() {
        _binding= null
        super.onDestroy()
    }
}