package cl.cdum.therickandmorty

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import cl.cdum.therickandmorty.databinding.ActivityLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        binding.apply {
            btnLogin.setOnClickListener {
                loginUser(
                    etEmail.text.toString(),
                    etPassword.text.toString()
                )
            }

            etEmail.doOnTextChanged { _, _, _, _ -> binding.tvError.isVisible = false }
            etPassword.doOnTextChanged { _, _, _, _ -> binding.tvError.isVisible = false }
        }
    }

    private fun loginUser(email: String, password: String) {
        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    binding.tvError.isVisible = true
                }
            }
    }
}