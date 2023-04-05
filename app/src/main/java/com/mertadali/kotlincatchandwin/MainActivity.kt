package com.mertadali.kotlincatchandwin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mertadali.kotlincatchandwin.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    var score=0
    var sayac =0
    var imageArray = ArrayList<ImageView>()
    /* Eğer bir işlemin ne kadar süreceğini bilmiyorsam ve sürekli devam etmesini istiyorsam app
     çökmemesi için runnable ve handler tanımlıyoruz.*/
    var handler = Handler(Looper.getMainLooper())
    var runnable = Runnable {  }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Image Array
        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        imageArray.add(binding.imageView10)
        imageArray.add(binding.imageView11)
        imageArray.add(binding.imageView12)
        imageArray.add(binding.imageView13)
        imageArray.add(binding.imageView14)
        imageArray.add(binding.imageView15)
        imageArray.add(binding.imageView16)

        hideImages()




        // CountDown Timer
        object : CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                binding.textView2.text="Time: " +p0/1000
                sayac = sayac+1                                     /*Bu yapı bize ne kadar süreden başlasın ve ne kadar
                 süreyle azalsın şeklinde bir yapı oluşturmaya yaradı. Ontick denilen fonksiyon her tıklandığında ne olacağını yapan işlemdir.
                 on finish ise bittiğinde ne yapılacağını yazdığımız fonksiyondur.*/
            }

            override fun onFinish() {
                binding.textView2.text="Time: 0"

                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility= View.INVISIBLE
                    if (sayac>=10){
                        Toast.makeText(this@MainActivity,"Congrats :)",Toast.LENGTH_LONG).show()
                    }else if (sayac<10){
                        Toast.makeText(this@MainActivity,"Failed :(",Toast.LENGTH_LONG).show()
                    }          // Toast message alt kısımda oyun bittiğinde bize bir mesaj iletecek.


                }


                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over!")
                alert.setMessage("Do you want to start again?")               // oyun bitince tekrardan oynamak istersek bize mesaj ile soracak.
                alert.setPositiveButton("Yes") {dialog,  which ->
                    // Restart Game
                    val intent = intent               // eğer kullanıcı evet tuşuna basarsa intent tekrardan açılacak.
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No") {dialog, which ->
                    Toast.makeText(this@MainActivity,"Game Over!",Toast.LENGTH_LONG).show()
                }
                alert.show()

            }

        }.start()


    }

    private fun hideImages() {
        runnable = object : Runnable{
            override fun run() {
                for (image in imageArray){
                    image.visibility= View.INVISIBLE           // verilen layoutların başlangıçta gizli olması ve ardından random bir şekilde bize gösterişlmesini sağlıyoruz.
                }

                // gizlediğimiz görselleri şimdi de rastgele bir şekilde görünür yapalım
                val random = Random()
                val randomIndex = random.nextInt(16)
                imageArray[randomIndex].visibility=View.VISIBLE
                handler.postDelayed(runnable,500)

            }

        }
        handler.post(runnable)

    }

    // Tüm ImageViewleri gizlemek için tanımladığımız listeden yapacağız.

    fun increase(view: View){
        score = score +1
        binding.textView.text="Score: $score"

    }
    fun dicrease(view: View){
        score = score -1
        binding.textView.text="Score: $score"

    }
}