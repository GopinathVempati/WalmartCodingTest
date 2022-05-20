package com.walmart.walmartcodingtest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.walmart.walmartcodingtest.data.CountriesListResponse
import com.walmart.walmartcodingtest.databinding.ItemCountriesBinding
import com.walmart.walmartcodingtest.utils.Extensions.checkNull
import kotlinx.android.synthetic.main.item_countries.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var countriesListResponse: List<CountriesListResponse> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val itemNyclistBinding = ItemCountriesBinding.inflate(inflater, parent, false)
        return MainViewHolder(itemNyclistBinding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val countriesListResponseItem = countriesListResponse[position]
        holder.bind(countriesListResponseItem)
    }

    override fun getItemCount() = countriesListResponse.size

    fun updateData(list: List<CountriesListResponse>) {
        this.countriesListResponse = list
        notifyDataSetChanged()
    }

    class MainViewHolder(val itemNyclistBinding: ItemCountriesBinding) :
        RecyclerView.ViewHolder(itemNyclistBinding.root) {
        fun bind(item: CountriesListResponse) {
            /*Setting recyclerview data on UI*/
            itemView.countryTv.text = item.name.checkNull().plus(" , ").plus(item.region.checkNull())
            itemView.countryCodeTv.text = item.code.checkNull()
            itemView.capitalTv.text = item.capital.checkNull()
        }

    }
}