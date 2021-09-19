package com.example.kotlinmvvmbysimplified.ui.home.quotes

import com.example.kotlinmvvmbysimplified.R
import com.example.kotlinmvvmbysimplified.data.db.entities.Quote
import com.example.kotlinmvvmbysimplified.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

//as we are using library for recyclerview which supports databinding
//layout file name is item_quote ,so dinding class will be ItemQuoteBinding
class QuoteItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>() {


    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }

}