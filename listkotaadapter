package com.simplecode01.indonesianhospital.adapter

class ListKotaKecamatanAdapter(private val listCity: List<City>) :
    RecyclerView.Adapter<ListKotaKecamatanAdapter.ListViewHolder>(), FastScroller.SectionIndexer{

    var clickListenerr: ((getCity: City)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kota, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val city = listCity[position]
        holder.bindView(city)
        holder.binding.cvCity.setOnClickListener {
            clickListenerr?.invoke(city)
        }
    }

    override fun getItemCount(): Int {
        return listCity.size
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: ItemKotaBinding by viewBinding()
        fun bindView(getCity: City){
            binding.city.text = getCity.name
            binding.cityId.text = "City ID :  ${getCity.id}"
        }
    }

    override fun getSectionText(position: Int): CharSequence {
        val citys = listCity[position]
        val popUpText = "Kota ${citys.name}"
        return popUpText
    }
}
