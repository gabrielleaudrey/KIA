package its.nugrohodimas.capstonebangkit.ui.imunitation.detail

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import its.nugrohodimas.capstonebangkit.R
import its.nugrohodimas.capstonebangkit.databinding.ActivityDetailVaccineBinding
import its.nugrohodimas.capstonebangkit.ui.imunitation.ImunitationViewModel
import its.nugrohodimas.core.data.source.local.entity.VaccineEntity
import its.nugrohodimas.core.data.source.local.room.KiaDatabase
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailVaccineActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val imunitationViewModel: ImunitationViewModel by viewModel()
    private lateinit var binding: ActivityDetailVaccineBinding
    private val c = Calendar.getInstance()


    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailVaccineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detail = intent.getParcelableExtra<VaccineEntity>(EXTRA_DATA)
        supportActionBar?.title = detail?.vaccineName
        showDetail(detail)
        binding.fabDetailVaccine.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater: LayoutInflater = layoutInflater
            val dialogLayout: View = inflater.inflate(R.layout.imunitation_dialog, null)
            val edtTanggal: EditText = dialogLayout.findViewById(R.id.edt_dialog_date)
            val edtWaktu: EditText = dialogLayout.findViewById(R.id.edt_dialog_time)
            val edtHospital: EditText = dialogLayout.findViewById(R.id.edt_dialog_hospital)
            val rgStatusVaccine: RadioGroup = dialogLayout.findViewById(R.id.rg_status_vaccine)

            with(builder) {
                setTitle("Imunisasi")
                setPositiveButton("Simpan") { dialog, which ->
                    val tanggal = edtTanggal.text.toString().trim()
                    val waktu = edtWaktu.text.toString().trim()
                    val hospital = edtHospital.text.toString().trim()
                    var status = false
                    rgStatusVaccine.setOnCheckedChangeListener { group, checkedId ->
                        if (checkedId == R.id.rb_sudah) status = true
                        else if (checkedId == R.id.rb_belum) status = false
                    }

                    val vaccine = VaccineEntity(
                        detail!!.idVaccine,
                        detail.vaccineName,
                        detail.vaccineFunction,
                        detail.vaccineIndication,
                        detail.vaccinePostEvent,
                        tanggal,
                        waktu,
                        hospital,
                        status
                    )

                    Thread {
                        val db = Room.databaseBuilder(
                            applicationContext,
                            KiaDatabase::class.java,
                            "Health"
                        ).build()
                        db.kiaDao().updateDataVaccine(vaccine)
                    }.start()
                    Toast.makeText(applicationContext, "Data Berhasil Disimpan", Toast.LENGTH_SHORT)
                        .show()
                }
                setNegativeButton("Cancel") { dialog, which ->

                }
                setView(dialogLayout)
                show()

                edtTanggal.setOnClickListener {
                    val year = c.get(Calendar.YEAR)
                    val month = c.get(Calendar.MONTH)
                    val day = c.get(Calendar.DAY_OF_MONTH)
                    val dpd = DatePickerDialog(
                        context,
                        DatePickerDialog.OnDateSetListener { view, myear, mmonth, mday ->
                            edtTanggal.setText(" $mday / $mmonth / $myear")
                        },
                        year,
                        month,
                        day
                    )
                    dpd.show()
                }

                edtWaktu.setOnClickListener {
                    val timeSetListener =
                        TimePickerDialog.OnTimeSetListener { view, mHour, mMinute ->
                            c.set(Calendar.HOUR_OF_DAY, mHour)
                            c.set(Calendar.MINUTE, mMinute)
                            edtWaktu.setText(SimpleDateFormat("HH:mm").format(c.time))
                        }
                    TimePickerDialog(
                        context,
                        timeSetListener,
                        c.get(Calendar.HOUR_OF_DAY),
                        c.get(Calendar.MINUTE),
                        true
                    ).show()
                }
            }
        }
    }

    private fun showDetail(item: VaccineEntity?) {
        item.let {
            binding.tvDetailFunctionVaccine.text = it?.vaccineFunction
            binding.tvDetailAfterVaccine.text = it?.vaccinePostEvent
            binding.tvDetailIndicationVaccine.text = it?.vaccineIndication
        }
    }
}