package com.example.precticle_test.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.precticle_test.R
import com.example.precticle_test.model.DisplayModel
import java.lang.String
import java.util.*
import java.util.concurrent.TimeUnit


class DisplayDataAdapter(var arrayList: ArrayList<DisplayModel>) :
    RecyclerView.Adapter<DisplayDataAdapter.ServiceViewHolder>() {


    lateinit var context: Context

    //Declare a variable to hold count down timer's paused status
    private var isPaused = false
    private var isCanceled = false
    private var timeRemaining: Long = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        context = parent.context

        return ServiceViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_data,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        // holder.etInteger.setText(arrayList[position].minute)
        holder.tvTimer.text = arrayList[position].timmer

        holder.imgOptionMenu.setOnClickListener {
            setOptionMenu(holder)
        }
    }

    private fun setOptionMenu(holder: ServiceViewHolder) {
        val popup = PopupMenu(context, holder.imgOptionMenu)
        popup.inflate(R.menu.options_menu)
        popup.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener,
            PopupMenu.OnMenuItemClickListener {
            @SuppressLint("SimpleDateFormat")
            override fun onMenuItemClick(item: MenuItem): Boolean {
                return when (item.getItemId()) {
                    R.id.startMenu -> {

                        isPaused = false
                        isCanceled = false

                        if (holder.etInteger.text.toString().isNotEmpty()) {
                            val timer: CountDownTimer
                            val millisInFuture: Long =
                                holder.etInteger.text.toString().toLong()
                            val countDownInterval: Long = 1000 //1 second

                            //Initialize a new CountDownTimer instance
                            timer = object : CountDownTimer(millisInFuture, countDownInterval) {
                                @SuppressLint("DefaultLocale")
                                override fun onTick(millisUntilFinished: Long) {
                                    if (isPaused || isCanceled) {
                                        cancel()
                                    } else {

                                        try {
                                            //  val strCurrentTime: Long =  millisUntilFinished / 1000
                                            val strCurrentTime: Long = millisUntilFinished

                                            val hms = String.format(
                                                "%02d:%02d:%02d",
                                                TimeUnit.MILLISECONDS.toHours(strCurrentTime),
                                                TimeUnit.MILLISECONDS.toMinutes(strCurrentTime) - TimeUnit.HOURS.toMinutes(
                                                    TimeUnit.MILLISECONDS.toHours(strCurrentTime)
                                                ),
                                                TimeUnit.MILLISECONDS.toSeconds(strCurrentTime) - TimeUnit.MINUTES.toSeconds(
                                                    TimeUnit.MILLISECONDS.toMinutes(strCurrentTime)
                                                )
                                            )
                                            holder.tvTimer.text = hms

                                            //Put count down timer remaining time in a variable
                                            timeRemaining = millisUntilFinished

                                        } catch (e: Exception) {
                                            Log.e("Error", e.message.toString())
                                        }


                                    }
                                }

                                override fun onFinish() {
                                    holder.tvTimer.text = "Done"

                                }
                            }.start()

                        } else {
                            Toast.makeText(context, "Enter the seconds", Toast.LENGTH_LONG).show()
                        }
                        true
                    }

                    R.id.stopMenu -> {
                        isPaused = true;
                        true
                    }

                    R.id.pauseMenu -> {
                        isPaused = true;
                        true
                    }

                    R.id.ResumeMenu -> {
                        isPaused = false
                        isCanceled = false

                        //Initialize a new CountDownTimer instance
                        val millisInFuture = timeRemaining
                        val countDownInterval: Long = 1000
                        object : CountDownTimer(millisInFuture, countDownInterval) {
                            @SuppressLint("DefaultLocale")
                            override fun onTick(millisUntilFinished: Long) {
                                //Do something in every tick
                                if (isPaused || isCanceled) {
                                    //If user requested to pause or cancel the count down timer
                                    cancel()
                                } else {
                                    val strCurrentTime: Long = millisUntilFinished

                                    val hms = String.format(
                                        "%02d:%02d:%02d",
                                        TimeUnit.MILLISECONDS.toHours(strCurrentTime),
                                        TimeUnit.MILLISECONDS.toMinutes(strCurrentTime) - TimeUnit.HOURS.toMinutes(
                                            TimeUnit.MILLISECONDS.toHours(strCurrentTime)
                                        ),
                                        TimeUnit.MILLISECONDS.toSeconds(strCurrentTime) - TimeUnit.MINUTES.toSeconds(
                                            TimeUnit.MILLISECONDS.toMinutes(strCurrentTime)
                                        )
                                    )
                                    holder.tvTimer.text = hms
                                    //Put count down timer remaining time in a variable
                                    timeRemaining = millisUntilFinished
                                }
                            }

                            override fun onFinish() {
                                holder.tvTimer.text = "Done"
                            }
                        }.start()
                        true
                    }
                    else -> false
                }
            }
        })
        popup.show()
    }

    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val etInteger = itemView.findViewById(R.id.etInteger) as EditText
        val tvTimer = itemView.findViewById(R.id.tvTimer) as TextView
        val imgOptionMenu = itemView.findViewById(R.id.imgOptionMenu) as ImageView
    }

}