package com.example.guessword

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.guessword.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val listOfWords = listOf("of","hi","day","cry","dry","fray","song","days","house","music","sleep","change")
    private var numberRandom = 0
    private var score :Int =0
    private var trying : Int = 3
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //initialize the variables
        this.init()
        this.binding.button.setOnClickListener{
              when {
                  this.binding.button.text.toString().lowercase() =="valider" -> {
                      this.guessWord() // c'est utilisateur clique valider
                  }
                  this.binding.button.text.toString().lowercase() =="nouveau" -> {
                      this.init() // initialiser le jeu
                  }
                  else -> this.next()
              } // jouez autre jeu
       }
        setContentView(this.binding.root)
    }


    @SuppressLint("SetTextI18n")
    fun init(){ // fonction d 'initialiser
        this.numberRandom = (2..6).random()
        this.trying = 3
        this.score = 0
        this.binding.score.text = this.score.toString()
        this.binding.button.text = "Valider"
        this.binding.textEnter.text = "Entrez un mot de longueur $numberRandom"
        this.binding.textMessage.visibility = View.INVISIBLE
        this.binding.inputWord.text.clear()
    }
    @SuppressLint("SetTextI18n")
    fun next(){ // fonction de suivant jeu
        this.numberRandom = (2..6).random()
        this.binding.textEnter.text = "Entrez un mot de longueur $numberRandom"
        this.binding.textMessage.visibility = View.INVISIBLE
        this.binding.button.text = "Valider"
        this.binding.inputWord.text.clear()
    }

    @SuppressLint("SetTextI18n")
    private fun guessWord(){ // fonction de valider la reponse

         var word: String = this.binding.inputWord.text.toString() // mot d'utilisatuer
        if(word.isEmpty()){
                this.binding.textMessage.isVisible = true
                this.binding.textMessage.text = "entrez le mot"
            }else{
                 word = word.lowercase()
                if(word.length==this.numberRandom){
                    val generateListWords : List<String>
                    generateListWords = this.listOfWords.filter { it.length==this.numberRandom }.toMutableList()
                    val wordGenerate = generateListWords.random()
                    if (word == wordGenerate) { // c'est correcte
                        this.score+=5
                        this.binding.textMessage.text = "Bravo !!!! Vous avez obtenu 5 points"
                        this.binding.textMessage.visibility = View.VISIBLE
                        this.binding.textMessage.setTextColor(Color.parseColor("#00FF00"))
                       this.binding.score.text = this.score.toString()
                    }else{ // c'est uncorrecte
                        this.binding.textMessage.text = "Echec !!!!"
                        this.binding.textMessage.visibility = View.VISIBLE
                        this.binding.textMessage.setTextColor(Color.parseColor("#FF0000"))
                    }
                    this.trying = this.trying -1
                    this.binding.button.text = if(this.trying<=0)"Nouveau" else "Suivant($trying)"
                }else{ // c'est longueur pas egale
                    this.binding.textMessage.text = "Entrer un mot de meme longueur"
                    this.binding.textMessage.visibility = View.VISIBLE
                    this.binding.textMessage.setTextColor(Color.parseColor("#FF0000"))
                }
            }
    }


}