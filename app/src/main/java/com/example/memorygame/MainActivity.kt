 package com.example.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*


 class MainActivity : AppCompatActivity() {
     companion object{
         const val INTERVAL: Long = 1000
     }
     private val handler = Handler()
     private var counter = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val order = mutableListOf( 1, 2, 3, 4 )
        order.shuffle()

        val boxList = listOf(box1, box2, box3, box4)

        setButton.setOnClickListener{ processLogic(order) }
        doneButton.setOnClickListener{ doneLogic(order) }
        refresh_button.setOnClickListener{ reloadActivity() }

        for(i in boxList){
            i.setOnClickListener{ boxLogic(it) }
        }


    }

     private fun reloadActivity() {
         finish()
         startActivity(intent)
     }

     private fun doneLogic(correctOrder: MutableList<Int>) {
         val boxList = listOf(box1, box2, box3, box4)
         val boxItems : MutableList<Int> = mutableListOf()
         val answer : MutableList<Int> = mutableListOf()
         for(i in boxList) boxItems.add(i.text.toString().toInt())

         for(ind in 1..4)
             answer.add(boxItems.indexOf(ind) + 1)



         success_msg.visibility = View.VISIBLE
         doneButton.visibility = View.GONE
             if(answer == correctOrder) {
                 info_text.text = "WELL DONE"
                 info_text.setTextColor(resources.getColor(R.color.GREEN))
                 success_msg.text = "You have a really good memory "
                 success_msg.setTextColor(resources.getColor(R.color.GREEN))
             }
            else {
                 info_text.text = "TRY AGAIN"
                 info_text.setTextColor(resources.getColor(R.color.RED))
                success_msg.text = "There is room for improvement"
                 success_msg.setTextColor(resources.getColor(R.color.RED))
            }

         refresh_button.visibility=View.VISIBLE

     }

     private  fun processLogic(order: MutableList<Int>){

         var timeDelay: Long = INTERVAL
         setButton.visibility = View.GONE

         info_text.text = getText(R.string.game_detail)
         for(item in order){

             handler.postDelayed( { showImages(item) }, timeDelay )
             timeDelay += INTERVAL
         }
         handler.postDelayed( { showBoxes() }, timeDelay )
     }

     private fun showBoxes() {
         val picList = mapOf(1 to image1, 2 to image2, 3 to image3, 4 to image4)
         val boxList = mapOf(1 to box1, 2 to box2, 3 to box3, 4 to box4)
         for (item in 1..4){
             picList[item]?.visibility = View.GONE
             boxList[item]?.visibility = View.VISIBLE
         }

         info_text.text = getText(R.string.game_detail_submit)

     }

     private fun showImages(item: Int) {
         val picList = mapOf(1 to image1, 2 to image2, 3 to image3, 4 to image4)
         val boxList = mapOf(1 to box1, 2 to box2, 3 to box4, 4 to box3)
         boxList[item]?.visibility = View.INVISIBLE
         picList[item]?.visibility = View.VISIBLE
     }

     private fun boxLogic(view: View){

         if (counter < 4){

             when (view.id) {
                 box1.id -> if(box1.text==""){ counter++
                                                box1.text = counter.toString()}
                 box2.id -> if(box2.text==""){ counter++
                                            box2.text = counter.toString()}
                 box4.id -> if(box4.text==""){ counter++
                                                box4.text = counter.toString()}
                 box3.id -> if(box3.text==""){ counter++
                                                box3.text = counter.toString()}

             }

             if(counter == 4)
                 doneButton.visibility= View.VISIBLE

         }

        println(counter)
     }


 }