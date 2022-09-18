package com.qtn.doinsome.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.qtn.doinsome.R
import com.qtn.doinsome.data.local.database.MovieDao
import com.qtn.doinsome.data.local.database.MovieDatabase
import com.qtn.doinsome.databinding.ActivityLoginBinding
import com.qtn.doinsome.preference.SessionModel
import com.qtn.doinsome.ui.MainActivity
import com.qtn.doinsome.ui.register.RegisterActivity
import com.qtn.doinsome.viewmodel.DatastoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val preference: DatastoreViewModel by viewModels()
    private lateinit var db: MovieDatabase
    private lateinit var dao: MovieDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        db = MovieDatabase.getDatabase(applicationContext)
        dao = db.movieDao()

        preference.getLoginState().observe(this){
            if (it.state){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            loginValidate()
        }

        binding.btnDontHaveAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }


    }
    private fun loginValidate(){
        val email = binding.edEmail.text.toString()
        val password = binding.edPassword.text.toString()
        when{
            email.isEmpty() -> binding.edEmail.error = getString(R.string.cant_empty)
            password.isEmpty() -> binding.edPassword.error = getString(R.string.cant_empty)
            else -> {
                val isUser = dao.readDataUser(email, password)
                if (isUser != null){
                    startActivity(Intent(this, MainActivity::class.java))
                    preference.login()
                    preference.saveUser(
                        SessionModel(
                            email,
                            true
                        )
                    )
                    finish()
                }else{
                    Toast.makeText(this, "Incorrectly entered the username or password you entered", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}