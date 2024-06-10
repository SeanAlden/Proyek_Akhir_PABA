package project.c14210052.proyekakhir_paba

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import project.c14210052.proyekakhir_paba.LoginRegister.Users

class EditProfileActivity : AppCompatActivity() {

    private lateinit var backButton : ImageButton
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var auth:FirebaseAuth
        val db = Firebase.firestore
        lateinit var userID:String
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = Firebase.auth

        var intentData = intent.getParcelableExtra<Users>("kirimDataProfile")
        var _etFullname: EditText = findViewById(R.id.etFullNameProfileEdit)
        var _btnSave: Button = findViewById(R.id.btnSaveProfileBtn)
        _etFullname.setText(intentData!!.fullname.toString())
        userID = auth.currentUser!!.uid

        _btnSave.setOnClickListener {
            val docRef = db.collection("users").document(userID)
            val newData = Users(docRef.id,_etFullname.text.toString(), intentData.email.toString(), intentData.password.toString(), intentData.status.toString())

            docRef.set(newData).addOnSuccessListener {
                Toast.makeText(this@EditProfileActivity, "Edit Profile Success", Toast.LENGTH_LONG).show()
            }
            finish()
        }

        backButton = findViewById(R.id.backButtonFromEditProfile)

        backButton.setOnClickListener {
            val intent = Intent(this@EditProfileActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}