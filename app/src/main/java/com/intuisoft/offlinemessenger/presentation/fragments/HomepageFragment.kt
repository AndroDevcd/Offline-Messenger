package com.intuisoft.offlinemessenger.presentation.fragments

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.intuisoft.offlinemessenger.R
import com.intuisoft.offlinemessenger.presentation.viewmodel.MainActivityViewModel
import com.intuisoft.offlinemessenger.presentation.util.injectVM
import kotlinx.android.synthetic.main.homepage_layout.*


class HomepageFragment : Fragment(R.layout.homepage_layout) {
    private lateinit var navController: NavController
    private val viewModel: MainActivityViewModel by lazy {
        injectVM(requireActivity(), MainActivityViewModel::class.java)
    }

    companion object {
        private val REQUEST_ENABLE_BLUETOOTH = 100
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        if(viewModel.getPrefs().isFirstOpen) {
            viewModel.getPrefs().isFirstOpen = true
            viewModel.getPrefs().isAppSupported =
                viewModel.isAppSupported()

            if(!viewModel.getPrefs().isAppSupported) {
                Toast.makeText(requireActivity(), "Bluetooth is not available!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                }
            })

        viewModel.bluetoothEnabled.observe(viewLifecycleOwner, Observer { event ->
            if(!event) {
                val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(
                    enableIntent,
                    REQUEST_ENABLE_BLUETOOTH,
                    null
                )
            }
        })

        viewModel.bluetoothSearch.observe(viewLifecycleOwner, Observer { event ->
            if(!event) {
                val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(
                    enableIntent,
                    REQUEST_ENABLE_BLUETOOTH,
                    null
                )
            }
        })


        connectButton.setOnClickListener {
            viewModel.pair()
        }

        //viewModel.setupBluetooth()
//        home_view_pager.adapter =
//            HomePagerAdapter(
//                requireActivity()
//            )


//        home_header_ifu_btn.setOnClickListener{
//            val bundle = HtmlViewerFragmentArgs.Builder()
//                .also { it.sourceFragmentId = id }
//            navController.navigate(R.id.htmlViewerFragment, bundle.build().toBundle())
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_ENABLE_BLUETOOTH && resultCode == Activity.RESULT_OK) {
            viewModel.pair()
        }
    }
}