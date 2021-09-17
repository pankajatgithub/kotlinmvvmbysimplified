package com.example.kotlinmvvmbysimplified.ui.home.quotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.kotlinmvvmbysimplified.R
import com.example.kotlinmvvmbysimplified.util.Coroutines
import com.example.kotlinmvvmbysimplified.util.toast
import org.kodein.di.android.x.kodein
import org.kodein.di.Kodein
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

        Coroutines.main {
            val quotes = viewModel.quotes.await()
            quotes.observe(viewLifecycleOwner, Observer {
                context?.toast(it.size.toString())
            })
        }


    }



}