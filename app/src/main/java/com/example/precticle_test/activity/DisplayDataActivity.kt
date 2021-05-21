package com.example.precticle_test.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.precticle_test.R
import com.example.precticle_test.adapter.DisplayDataAdapter
import com.example.precticle_test.databinding.ActivityDisplayDataBinding
import com.example.precticle_test.model.DisplayModel
import com.example.precticle_test.model.OptionMenuModel


class DisplayDataActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDisplayDataBinding

    private var rowData1 = ArrayList<DisplayModel>()
    private var optionMenuArray1 = ArrayList<OptionMenuModel>()

    private lateinit var displyAdapter: DisplayDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   setContentView(R.layout.activity_display_data)
        initView()
        setAdapterData()
        setOnclickLis()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.imgBack -> {
                onBackPressed()
            }
        }
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_data)
    }

    private fun setOnclickLis() {
        binding.imgBack.setOnClickListener(this)
    }

    private fun setAdapterData() {
        rowData1 = ArrayList<DisplayModel>()
        optionMenuArray1 = ArrayList<OptionMenuModel>()

        rowData1.add(DisplayModel("00.00", "00:00:00", optionMenuArray1))
        optionMenuArray1.add(OptionMenuModel("Start", "Stop", "Pause", "Resume"))

        rowData1.add(DisplayModel("00.00", "00:00:00", optionMenuArray1))
        optionMenuArray1.add(OptionMenuModel("Start", "Stop", "Pause", "Resume"))

        rowData1.add(DisplayModel("00.00", "00:00:00", optionMenuArray1))
        optionMenuArray1.add(OptionMenuModel("Start", "Stop", "Pause", "Resume"))

        rowData1.add(DisplayModel("00.00", "00:00:00", optionMenuArray1))
        optionMenuArray1.add(OptionMenuModel("Start", "Stop", "Pause", "Resume"))

        //Adapter
        displyAdapter = DisplayDataAdapter(rowData1)
        binding.rvDisplayList.adapter = displyAdapter

    }

}