package todo.framework.ui.views


import android.content.Context
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.LogingPageBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class LoginFragment : Fragment(R.layout.loging_page) {
    private var _binding: LogingPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth


    companion object {
        const val USERNAME = "username"
    }

    private val googleSigInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
            val account = GoogleSignIn.getSignedInAccountFromIntent(activityResult?.data).result

            account.let {
                authGoogleInFirebase(it)
            }


    }

    private fun authGoogleInFirebase(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken,null)
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                auth.signInWithCredential(credential).await()
                   enter(account.email)

            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(),"Fallo al Ingresar con Google",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null) enter(currentUser.email)
        binding.authLayout.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = LogingPageBinding.bind(view)
        navController = findNavController()
        auth = FirebaseAuth.getInstance()


        binding.bntRegister.setOnClickListener {
            val email = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) registerWithEmail(email, password)
        }

        binding.bntLoging.setOnClickListener {
            val email = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) loginWithEmail(email, password)
        }

        binding.btnGoogle.setOnClickListener {
            val googleOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.default_web_client_id))
                .build()

            val singInClient = GoogleSignIn.getClient(requireContext(),googleOptions)
            singInClient.signOut()
            googleSigInLauncher.launch(singInClient.signInIntent)

        }

        session()
    }

    private fun registerWithEmail(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    enter(user?.email)
                } else {
                    // Fallo al crear Cuenta
                }
            }
    }

    private fun loginWithEmail(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    enter(user?.email)
                } else {
                    // Fallo al Login
                }
            }
    }

    private fun enter(emailUser: String?) {
        val bundle = Bundle()
        bundle.putString(USERNAME, emailUser)
        navController.navigate(R.id.action_global_inboxFragment, bundle)
    }



    private fun session() {
        val pref =
            requireContext().getSharedPreferences(ProjectFragment.PREFNAME, Context.MODE_PRIVATE)
        val user = pref.getString("user", null)
        if (user != null) {
            binding.authLayout.visibility = View.INVISIBLE
            val bundle = Bundle()
            bundle.putString(USERNAME, user)
            navController.navigate(R.id.action_global_inboxFragment, bundle)
        }
    }


}