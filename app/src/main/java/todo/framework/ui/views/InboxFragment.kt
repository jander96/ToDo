package todo.framework.ui.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.InboxPageBinding
import todo.framework.ui.adapters.TaskIboxAdapter

class InboxFragment: Fragment(R.layout.inbox_page) {
    private  var _binding:InboxPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    private val listaTestAdapter = listOf("Hacer tiempo para descansar ", "Tomar el sol","Terminar de hacer esta app,"
    ,"Seguir estudiando Lagoritmos","Estudiar el libro en Ingles de Android","Estudiar Ingles","Aprender JEtPackCompose",
    "Crear un bonito diseño para l app","Crear base de datos local con room para cacchear ","Aprender Testing",
    "Acabar por fin de entender la bendita animacion", "Bla bla bla","Un pquito mas de bl abla bla ")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = InboxPageBinding.bind(view)
        recyclerView = binding.recyclerView
        val adapter = TaskIboxAdapter(listaTestAdapter)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

    }

    override fun onDestroy() {
        _binding= null
        super.onDestroy()
    }
}