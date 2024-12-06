package com.example.studentportal

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class RegistrationActivity : AppCompatActivity() {
    var stdname:EditText?= null
    var stdroll:EditText?=null
    var stdpassword:EditText?= null
    var cnfpassword:EditText?=null
    var btnmale:RadioButton?=null
    var btnfemale:RadioButton?= null
    var conditonscheck:CheckBox?= null
    var btnregister:Button?=null
    var branchspinner:Spinner?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        stdname=findViewById(R.id.edt_name)
        stdroll=findViewById(R.id.edt_rollno)
        stdpassword=findViewById(R.id.edt_password)
        cnfpassword=findViewById(R.id.edt_cnfpswd)
        btnmale=findViewById(R.id.radio_male)
        btnfemale=findViewById(R.id.radio_female)
        conditonscheck=findViewById(R.id.termsCheckBox)
        btnregister=findViewById(R.id.btn_register)
        branchspinner=findViewById(R.id.branch_spinner)

        var branchs=resources.getStringArray(R.array.branches_array)
        var adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,branchs)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        branchspinner?.adapter=adapter


        btnregister?.setOnClickListener{
            val name: String = stdname?.text.toString()
            val rollNo: String = stdroll?.text.toString()
            val password: String = stdpassword?.text.toString()
            val confirmPassword: String = cnfpassword?.text.toString()
            val branch: String = branchspinner?.selectedItem.toString()

            val conditionchecked:Boolean=conditonscheck?.isChecked==true
            var pswdcheck=Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\\$\\%\\^&*])[a-zA-Z0-9!@#\\$\\%\\^&*]+$");
            val gender: String = when {
                btnmale?.isChecked == true -> "Male"
                btnfemale?.isChecked == true -> "Female"
                else -> ""
            }
            var error_msz=when{
                name.isEmpty() && rollNo.isNotEmpty() && password.isEmpty() &&confirmPassword.isEmpty() && gender.isEmpty() && branch.isEmpty()->"Please fill all the details"
                name.isEmpty()->"Enter the name"
                password.isEmpty()->"Enter password"
                confirmPassword.isEmpty()->"Emter confirmation password"
                !pswdcheck.matcher(password).matches() -> "Password must contain at least one letter, one number, and one special character, and be at least 6 characters long."
                password.compareTo(confirmPassword) != 0 ->"Passwords do not match"
                gender.isEmpty()->"Select GENDER"
                branch.isEmpty() || branch=="Select Branch"->"Select branch"
                !conditionchecked->"Accept the Terms and conditions"
                else -> null
            }
            if(error_msz!=null){
               // Toast.makeText(this,error_msz,Toast.LENGTH_SHORT).show()
                alert(error_msz)
            }
            else{
                userdetails(name,password,branch,this)
                Toast.makeText(this,"Registration successful",Toast.LENGTH_SHORT).show()

                val intent=Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
    fun userdetails(studentname:String,studentpassword:String,studentbranch:String,context: Context){
        val sharedpref=context.getSharedPreferences("myprefs",Context.MODE_PRIVATE)
        val editor=sharedpref.edit()
        editor.putString("Student Name",studentname)
        editor.putString("Student Password",studentpassword)
        editor.putString("Selected Branch",studentbranch)
        editor.apply()
    }

    fun alert(errormessage:String){
        val setalert=AlertDialog.Builder(this)
        setalert.setTitle("Error")
        setalert.setMessage(errormessage)
        setalert.setPositiveButton("ok"){ dialog: DialogInterface, which: Int ->
            dialog.dismiss() }
        val dialog: AlertDialog = setalert.create()
        dialog.show()
    }
}
