package com.caitlykate.trafficlight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import java.util.*

class MainActivity : AppCompatActivity() {
    var imTL: ImageView? = null
    var imButton: ImageButton? = null
    var isRun = false
    var counter: Int = 0
    val imgArray: IntArray = intArrayOf(R.drawable.semafor_red,R.drawable.semafor_yellow,R.drawable.semafor_green,R.drawable.semafor_yellow)
    var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imTL = findViewById(R.id.imTL)
        imButton = findViewById(R.id.imButton)

    }

    fun onClickStartStop(view:View){
        view as ImageButton
        if (!isRun ){
            startStop()
            view.setImageResource(R.drawable.button_stop)
            isRun = true
        } else {
            timer?.cancel()
            view.setImageResource(R.drawable.button_start)
            imTL?.setImageResource(R.drawable.semafor_grey)
            isRun = false
            counter = 0
        }

    }

    private fun startStop(){
        timer = Timer()
        timer?.schedule(object: TimerTask(){
            override fun run() {                                     //второстепенный поток
                runOnUiThread {                                      //незьзя менять элементы экрана на второстепенном потоке
                    imTL?.setImageResource(imgArray[counter])
                    counter++
                    if (counter == 4) counter = 0
                }
            }
        },0,1000)

    }
}