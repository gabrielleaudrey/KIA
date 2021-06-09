package its.nugrohodimas.capstonebangkit.ui.profile

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import its.nugrohodimas.capstonebangkit.R
import its.nugrohodimas.capstonebangkit.databinding.ActivityProfileBinding
import java.util.*
import kotlin.collections.HashMap

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private var jenisKelamin: String = ""
    private var tanggalLahir: String = ""
    private val c = Calendar.getInstance()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rgJenisKelamin.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rb_laki) jenisKelamin = getString(R.string.laki_laki)
            else if (checkedId == R.id.rb_perempuan) jenisKelamin = getString(R.string.perempuan)
        }

        binding.edtTanggalLahir.setOnClickListener {
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, myear, mmonth, mday ->
                    binding.edtTanggalLahir.setText(" $mday / $mmonth / $myear")
                    tanggalLahir = " $mday / $mmonth / $myear"
                },
                year,
                month,
                day
            )
            dpd.show()
        }

        Log.d("Tanggal Lahir", tanggalLahir)

        binding.btnSaveUpdate.setOnClickListener {
            val namaIbu = binding.edtNamaIbu.text.toString().trim()
            val username = binding.edtUsername.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val nomorHp = binding.edtNomerHp.text.toString().trim()
            val namaAnak = binding.edtNamaAnak.text.toString().trim()

            saveToFirestore(namaIbu, username, email, nomorHp, namaAnak, tanggalLahir, jenisKelamin)
        }
    }

    private fun saveToFirestore(
        namaIbu: String,
        username: String,
        email: String,
        nomorHp: String,
        namaAnak: String,
        tanggalLahir: String,
        jenisKelamin: String
    ) {
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["namaIbu"] = namaIbu
        user["username"] = username
        user["email"] = email
        user["nomorHp"] = nomorHp
        user["namaAnak"] = namaAnak
        user["tanggalLahir"] = tanggalLahir
        user["jenisKelamin"] = jenisKelamin

        db.collection("pengguna")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Data Anda Berhasil Disimpan", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Data Anda Gagal Disimpan", Toast.LENGTH_SHORT).show()
            }
    }
}