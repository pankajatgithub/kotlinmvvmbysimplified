package com.example.kotlinmvvmbysimplified.ui.home.quotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinmvvmbysimplified.R
import com.example.kotlinmvvmbysimplified.data.db.entities.Quote
import com.example.kotlinmvvmbysimplified.util.Coroutines
import com.example.kotlinmvvmbysimplified.util.hide
import com.example.kotlinmvvmbysimplified.util.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.android.x.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(),KodeinAware {
    override val kodein by kodein()
   private val factory : QuotesViewModelFactory by instance()

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

return inflater.inflate(R.layout.quotes_fragment,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this,factory).get(QuotesViewModel::class.java)
   bindUI()


//        Coroutines.main {
//            val quotes = viewModel.quotes.await()
//
//            quotes.observe(viewLifecycleOwner, Observer {
//                context?.toast(it.size.toString())
//            })
//        }
    }

    private fun bindUI() = Coroutines.main {
        progress_bar.show()
    viewModel.quotes.await()
        .observe(viewLifecycleOwner, Observer {
            progress_bar.hide()
            initRecyclerview(it.toQuoteItem())
        })
    }

    private fun initRecyclerview(quoteItem: List<QuoteItem>) {
        val  mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem)
        }
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }

    }

    //    creating extension function to get list of items from list of quotes
 private fun List<Quote>.toQuoteItem() : List<QuoteItem>{
     return  this.map {
         QuoteItem(it)
     }
 }


}