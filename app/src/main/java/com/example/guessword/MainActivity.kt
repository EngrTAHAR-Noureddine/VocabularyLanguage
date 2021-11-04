package com.example.guessword

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.guessword.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val list = listOf("of",/*"hi","day","cry",*/"dry","fray",/*"song","days","house",*/"music",/*"sleep",*/"change")
    private var number = 0
    var score :Int =0
    private var trying : Int = 3
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //initialize the varibles
        this.init()
        this.binding.button.setOnClickListener{
              if(this.binding.button.text.toString().lowercase() =="valider") {
                  this.guessWord()
              }else if(this.binding.button.text.toString().lowercase() =="nouveau") {
                  this.init()
              }else this.next()
       }
        setContentView(this.binding.root)
    }


    fun init(){
        this.number = (2..6).random()
        this.trying = 3
        this.score = 0
        this.binding.score.text = this.score.toString()
        this.binding.button.text = "Valider"
        this.binding.textEnter.text = "Entrez un mot de longueur $number"
        this.binding.textMessage.visibility = View.INVISIBLE
        this.binding.inputWord.text.clear()
    }
    fun next(){
        this.number = (2..6).random()
        this.binding.textEnter.text = "Entrez un mot de longueur $number"
        this.binding.textMessage.visibility = View.INVISIBLE
        this.binding.button.text = "Valider"
        this.binding.inputWord.text.clear()
    }

    private fun guessWord(){

         var word: String = this.binding.inputWord.text.toString()
        if(word.isEmpty()){
                this.binding.textMessage.isVisible = true
                this.binding.textMessage.text = "entrez le mot"
            }else{
                 word = word.lowercase()
                if(word.length==this.number){
                    val generateListWords : List<String>
                    generateListWords = this.list.filter { it.length==this.number }.toMutableList()
                    val wordGenerate = generateListWords.random()
                    if (word == wordGenerate) {
                        this.score+=5
                        this.binding.textMessage.text = "Bravo !!!! Vous avez obtenu 5 points"
                        this.binding.textMessage.visibility = View.VISIBLE
                        this.binding.textMessage.setTextColor(Color.parseColor("#00FF00"))
                       this.binding.score.text = this.score.toString()
                    }else{
                        this.binding.textMessage.text = "Echec !!!!"
                        this.binding.textMessage.visibility = View.VISIBLE
                        this.binding.textMessage.setTextColor(Color.parseColor("#FF0000"))
                    }
                    this.trying = this.trying -1
                    this.binding.button.text = if(this.trying<=0)"Nouveau" else "Suivant($trying)"
                }else{
                    this.binding.textMessage.text = "Entrer un mot de meme longueur"
                    this.binding.textMessage.visibility = View.VISIBLE
                    this.binding.textMessage.setTextColor(Color.parseColor("#FF0000"))
                }
            }
    }


}