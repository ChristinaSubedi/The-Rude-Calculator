package com.christinamade.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat
import java.util.Random
import com.opencsv.CSVReader
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {
    private val fortuneMessages = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var isNew=true

        var input=findViewById<TextView>(R.id.input)
        var output=findViewById<TextView>(R.id.output)
        var btn0 =findViewById<Button>(R.id.btn0)
        var btn1 =findViewById<Button>(R.id.btn1)
        var btn2 =findViewById<Button>(R.id.btn2)
        var btn3 =findViewById<Button>(R.id.btn3)
        var btn4 =findViewById<Button>(R.id.btn4)
        var btn5 =findViewById<Button>(R.id.btn5)
        var btn6 =findViewById<Button>(R.id.btn6)
        var btn7 =findViewById<Button>(R.id.btn7)
        var btn8 =findViewById<Button>(R.id.btn8)
        var btn9 =findViewById<Button>(R.id.btn9)

        var mul =findViewById<Button>(R.id.multiply)
        var div =findViewById<Button>(R.id.divide)
        var ac =findViewById<Button>(R.id.btnAC)
        var clr =findViewById<Button>(R.id.clear)
        var add =findViewById<Button>(R.id.add)
        var sub =findViewById<Button>(R.id.sub)
        var dot =findViewById<Button>(R.id.dot)
        var eq =findViewById<Button>(R.id.eq)
        var rb =findViewById<Button>(R.id.bracket_r)
        var lb =findViewById<Button>(R.id.bracket)

        var fortune=findViewById<TextView>(R.id.fortune)


        fun readForMes(){
            try{
                val inputStream=resources.openRawResource(R.raw.fortune)
                val reader=CSVReader(InputStreamReader(inputStream))

                var nextLine: Array<String>?
                while (reader.readNext().also{nextLine=it}!=null){
                    fortuneMessages.add(nextLine!![0])
                }
                reader.close()
            }catch(e:Exception){
                e.printStackTrace()
            }
        }
        readForMes()

        fun setForMsg(){
            val random= Random()
            val randomIndex=random.nextInt(fortuneMessages.size)
            val selectedMessage=fortuneMessages[randomIndex]
            fortune.text=selectedMessage
        }

        fun concat(inp:String){
            if (isNew){
                input.text=""
                output.text=""
                isNew=false
            }
            input.text=input.text.toString()+ "" + inp

        }

        fun concatOp(inp:String){
            if (isNew){
                isNew=false
                input.text=output.text
                output.text=""
            }
            input.text=input.text.toString()+ "" + inp

        }

        fun showResult(){
            val result=Expression(input.text.toString()).calculate()
            isNew=true
            setForMsg()
            if (result.isNaN()){
                output.text=""
                input.text=""
                return
            } else{
                output.text=DecimalFormat("0.##").format(result).toString()

            }
        }

        clr.setOnClickListener {
            input.text=""
            output.text=""
        }

        rb.setOnClickListener {
            concat(")")
        }

        lb.setOnClickListener {
            concat("(")
        }

        div.setOnClickListener {
            concatOp("/")
        }

        btn7.setOnClickListener {
            concat("7")
        }

        btn8.setOnClickListener {
            concat("8")
        }

        btn9.setOnClickListener {
            concat("9")
        }

        mul.setOnClickListener {
            concatOp("*")
        }

        btn4.setOnClickListener {
            concat("4")
        }

        btn5.setOnClickListener {
            concat("5")
        }

        btn6.setOnClickListener {
            concat("6")
        }

        sub.setOnClickListener {
            concatOp("-")
        }

        btn1.setOnClickListener {
            concat("1")
        }

        btn2.setOnClickListener {
            concat("2")
        }

        btn3.setOnClickListener {
            concat("3")
        }

        add.setOnClickListener {
            concatOp("+")
        }

        ac.setOnClickListener {
            isNew=false
            val temp=input.text.toString().dropLast(1)
            input.text=temp
        }

        btn0.setOnClickListener {
            concat("0")
        }

        dot.setOnClickListener {
            concat(".")
        }

        eq.setOnClickListener {
            showResult()
        }














    }
}