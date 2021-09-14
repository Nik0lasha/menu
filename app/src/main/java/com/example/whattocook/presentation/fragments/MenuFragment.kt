package com.example.whattocook.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.whattocook.MainActivity.Companion.isConnect
import com.example.whattocook.NetworkConnection
import com.example.whattocook.R
import com.example.whattocook.Utill.isVisible
import com.example.whattocook.base.BaseActivity
import com.example.whattocook.data.db.repository.MenuRepository
import com.example.whattocook.presentation.MenuViewModelProviderFactory
import com.example.whattocook.presentation.adapter.MenuAdapter
import com.example.whattocook.presentation.viewmodel.MenuViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_menu.*


class MenuFragment : Fragment(R.layout.fragment_menu) {
    private lateinit var buttonSelection: AppCompatImageButton
    private lateinit var buttonSettings: AppCompatImageButton
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var viewModel: MenuViewModel
    private lateinit var networkConnection: NetworkConnection
    private val menuAdapter: MenuAdapter by lazy {
        MenuAdapter({
            Log.d(
                "ClickOnArticle",
                it.description
            )
        },
            {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
            })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val menuRepository = MenuRepository()
        val viewModelProviderFactory = MenuViewModelProviderFactory(menuRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MenuViewModel::class.java)
        networkConnection = NetworkConnection((context as BaseActivity).application)
        networkConnection.observe(this, { isConnected ->
            isConnect = if (isConnected) 1 else 0
            if (isConnected) {
                val keyWord: String? =
                    SelectionFragment.sharedPreferences?.getString(
                        SelectionFragment.KEY_WORD,
                        "error"
                    )

                if (keyWord == null) {
                    viewModel.getBreakingNews("us")
                } else {
                    viewModel.getNewsByKeyWord(keyWord)
                }
            } else {
                Toast.makeText(
                    context,
                    "No Internet Connection. Please check your internet connection",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.breakingMenuListLiveData.observe(viewLifecycleOwner, Observer { response ->
            menuAdapter.listOfItems = response
            menuAdapter.notifyDataSetChanged()
        })

        viewModel.errorStateLiveData.observe(viewLifecycleOwner, Observer { error ->
            Log.e("TAG", "An error: $error")
        })

        viewModel.loadingMutableLiveData.observe(viewLifecycleOwner, Observer { visibility ->
            pagination_progress_bar.isVisible(visibility)
        })

        buttonSelection = view.findViewById(R.id.img_settings)
        buttonSelection.setOnClickListener {
            (requireActivity() as BaseActivity).fragmentRouter.openSelectionFragment()
        }

        swipeRefresh = view.findViewById(R.id.swipe_refresh)
        swipeRefresh.setOnRefreshListener {
            if (isConnect == 1) {
                SelectionFragment.sharedPreferences?.edit()?.clear()?.apply()
                viewModel.getBreakingNews("us")
            }
            swipeRefresh.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        when (isConnect) {
            0 -> {
                Toast.makeText(
                    context,
                    "No Internet Connection. Please check your internet connection",
                    Toast.LENGTH_SHORT
                ).show()
            }
            1 -> {
                val keyWord: String? =
                    SelectionFragment.sharedPreferences?.getString(
                        SelectionFragment.KEY_WORD,
                        "error"
                    )

                if (keyWord == null) {
                    viewModel.getBreakingNews("us")
                } else {
                    viewModel.getNewsByKeyWord(keyWord)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        rv_menu.apply {
            adapter = menuAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}