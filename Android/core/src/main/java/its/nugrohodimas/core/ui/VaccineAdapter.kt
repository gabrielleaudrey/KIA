package its.nugrohodimas.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import its.nugrohodimas.core.databinding.ItemListVaccineBinding
import its.nugrohodimas.core.domain.model.Vaccine
import java.util.ArrayList

class VaccineAdapter : RecyclerView.Adapter<VaccineAdapter.ViewHolder>() {

    private var listData = ArrayList<Vaccine>()

    fun setData(newListData: List<Vaccine>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListVaccineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    class ViewHolder(private val binding: ItemListVaccineBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(vaccine: Vaccine) {
            with(binding) {
                tvVaccineName.text = vaccine.vaccineName
                tvVaccineDate.text = vaccine.date
                //Tambahkan gambar status nanti
            }
        }
    }
}