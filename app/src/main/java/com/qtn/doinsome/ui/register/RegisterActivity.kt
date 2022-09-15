package com.qtn.doinsome.ui.register

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.qtn.doinsome.R
import com.qtn.doinsome.data.local.database.MovieDao
import com.qtn.doinsome.data.local.database.MovieDatabase
import com.qtn.doinsome.data.local.model.UserEntity
import com.qtn.doinsome.databinding.ActivityRegisterBinding
import com.qtn.doinsome.viewmodel.UserViewModel
import com.qtn.doinsome.viewmodel.UserViewModelFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var db : MovieDatabase
    private lateinit var dao: MovieDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtainViewModel()
        db = MovieDatabase.getDatabase(applicationContext)
        dao = db.movieDao()


        binding.btnLogin.setOnClickListener {
            validate()
        }


        binding.btnHaveAccount.setOnClickListener {
            finish()
        }
    }

    fun validate(){
        val username = binding.edUsername.text.toString().trim()
        val email = binding.edEmail.text.toString().trim()
        val password = binding.edPassword.text.toString().trim()
        val date = binding.edBorn.text.toString().trim()

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val validateEmail = dao.validateEmail(email)
        val validateUsername = dao.validateUsername(username)
        val year = Calendar.getInstance().get(Calendar.YEAR)
        var yearOfBirth = 0

        if (date.isEmpty()){
            binding.edBorn.error = "Please enter your date of birth"
        }else{
            val formatedDate = dateFormat.parse(date)
            val getYear = SimpleDateFormat("yyyy")
            yearOfBirth= getYear.format(formatedDate).toInt()
        }

        if (username.isEmpty()) binding.edUsername.error = getString(R.string.cant_empty)
        if (password.isEmpty()) binding.edPassword.error = getString(R.string.cant_empty) else if (password.length < 8) binding.edPassword.error = getString(R.string.password_length)
        if (email.isEmpty()) binding.edEmail.error = getString(R.string.cant_empty)
        when{
            email == validateEmail -> binding.edEmail.error = getString(R.string.email_exist)
            username == validateUsername -> binding.edUsername.error = getString(R.string.username_exist)
            else->{
                if(username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty() && date.isNotEmpty()){
                    if (yearOfBirth > year) Toast.makeText(this, "there's no way you're from the future!", Toast.LENGTH_SHORT).show()
                    else if((year-yearOfBirth)<18) Toast.makeText(this, "You must be 18 years old!", Toast.LENGTH_SHORT).show()
                    else {
                        viewModel.registerUser(UserEntity(0,username,email,password,date))
                        Toast.makeText(this, "Register success!", Toast.LENGTH_SHORT).show()
                        finish()
                    }

                }

            }
        }


    }


    fun SelectDate(view: View) {
        var c = Calendar.getInstance()
        var cDay = c.get(Calendar.DAY_OF_MONTH)
        var cMonth = c.get(Calendar.MONTH)
        var cYear = c.get(Calendar.YEAR)
        /**set CalendarDialog*/
        val calendarDialog = DatePickerDialog(this,
            { view, year, month, dayOfMonth ->
                cDay = dayOfMonth
                cMonth = month
                cYear = year
                val bornDate = binding.edBorn
                bornDate.setText("$cDay/${cMonth+1}/$cYear")
            },cYear,cMonth,cDay)
        calendarDialog.show()

    }

    private fun obtainViewModel(){
        val factory = UserViewModelFactory.getInstance(this.application)
        viewModel = ViewModelProvider(this,factory).get(UserViewModel::class.java)
    }
}