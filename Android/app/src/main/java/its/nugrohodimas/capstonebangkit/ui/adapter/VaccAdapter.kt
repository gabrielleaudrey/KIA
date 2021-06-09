package its.nugrohodimas.capstonebangkit.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import its.nugrohodimas.capstonebangkit.ui.imunitation.detail.DetailVaccineActivity
import its.nugrohodimas.core.R
import its.nugrohodimas.core.data.source.local.entity.VaccineEntity

class VaccAdapter(
    private val dataList: MutableList<VaccineEntity>
) :
    RecyclerView.Adapter<VaccAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_vaccine, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataList[position]
        holder.name.text = data.vaccineName
        holder.date.text = data.date
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailVaccineActivity::class.java)
            intent.putExtra(DetailVaccineActivity.EXTRA_DATA, data)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = dataList.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tv_vaccine_name)
        var date: TextView = itemView.findViewById(R.id.tv_vaccine_date)
        var imageStatus: ImageView = itemView.findViewById(R.id.img_vaccine_status)
    }
}