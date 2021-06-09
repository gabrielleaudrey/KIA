package its.nugrohodimas.capstonebangkit.ui.nutrition

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import its.nugrohodimas.capstonebangkit.databinding.FragmentNutritionBinding
import its.nugrohodimas.capstonebangkit.ml.Model
import org.json.JSONObject
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.InputStream


@Suppress("DEPRECATION")
class NutritionFragment : Fragment() {

    private lateinit var bitmap: Bitmap
    private var kalori = ""
    private var lemak = ""
    private var kolesterol = ""
    private var natrium = ""
    private var kalium = ""
    private var karbo = ""
    private var protein = ""
    private lateinit var nutritionViewModel: NutritionViewModel
    private var _binding: FragmentNutritionBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        nutritionViewModel =
            ViewModelProvider(this).get(NutritionViewModel::class.java)

        _binding = FragmentNutritionBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val fileName = "label.txt"
        val inputString = context?.assets?.open(fileName)?.bufferedReader().use {
            it?.readText()
        }
        val townList = inputString?.split("\n")

        binding.buttonSelect.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        }

        binding.buttonCamera.setOnClickListener {
            //req cam permission
            if (ActivityCompat.checkSelfPermission(
                    requireContext(), Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.CAMERA),
                    101
                )
            }

            //Open Cam
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 101)
        }

        binding.buttonPredict.setOnClickListener {
            val resized: Bitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true)

            //ml
            val model = Model.newInstance(requireContext())

            // Creates inputs for reference.
            val inputFeature0 =
                TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
            val tensorImage = TensorImage(DataType.FLOAT32)

            tensorImage.load(resized)
            //bytebuffer
            val byteBuffer = tensorImage.buffer
            inputFeature0.loadBuffer(byteBuffer)

            // Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer
            val max = getMax(outputFeature0.floatArray)

            // Releases model resources if no longer used.
            model.close()

            //show nutrition after predict
            val res = townList?.get(max)?.trim()
            binding.tvTittleNutrition.text = "Kandungan Gizi $res"

            //Read and Parse JSON
            if (res != null) {
                readJson(res)
            }

            binding.tvResultKalori.text = kalori
            binding.tvResultLemak.text = lemak
            binding.tvResultKolesterol.text = kolesterol
            binding.tvResultNatrium.text = natrium
            binding.tvResultKalium.text = kalium
            binding.tvResultKarbohidrat.text = karbo
            binding.tvResultProtein.text = protein

        }

        return root
    }

    private fun getMax(arr: FloatArray): Int {
        var a = 0
        var min = 0.0f

        //result data only 7
        for (i in 0..6) {
            if (arr[i] > min) {
                a = i
                min = arr[i]
            }
        }

        return a
    }

    private fun readJson(res: String) {
        val json: String?

        try {
            val input: InputStream? = context?.assets?.open("nutrition.json")
            json = input?.bufferedReader().use { it?.readText() }
            val obj = JSONObject(json)
            val jsonArr = obj.getJSONArray("nutrition")

            for (i in 0 until jsonArr.length()) {
                val jsonObj = jsonArr.getJSONObject(i)
                if (jsonObj.getString("prediksi").equals(res)) {
                    kalori = jsonObj.getString("kalori")
                    lemak = jsonObj.getString("lemak")
                    kolesterol = jsonObj.getString("kolesterol")
                    natrium = jsonObj.getString("natrium")
                    kalium = jsonObj.getString("kalium")
                    karbo = jsonObj.getString("karbohidrat")
                    protein = jsonObj.getString("protein")
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 101) {
            val img: Bitmap? = data?.getParcelableExtra("data")
            binding.imageView.setImageBitmap(img)
            if (img != null) {
                bitmap = img
            }
        } else if (requestCode == 100) {
            binding.imageView.setImageURI(data?.data)
            val uri: Uri? = data?.data
            bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}