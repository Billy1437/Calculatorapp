package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    private Button btnAdd,btnMinus,btnMultiply,btnDivide,btnModulus;
    private Button btnReset,btnCalculate,btnDecimal,btnDelete;

//    calculator login variables
    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isOperatorClicked = false;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setupClickListeners();
    }

    private void initializeViews(){
        display = findViewById(R.id.displayText);

//        number btns
        btn0 = findViewById(R.id.zerobtn);
        btn1 = findViewById(R.id.onebtn);
        btn2 = findViewById(R.id.twobtn);
        btn3 = findViewById(R.id.threebtn);
        btn4 = findViewById(R.id.fourbtn);
        btn5 = findViewById(R.id.fivebtn);
        btn6 = findViewById(R.id.sixbtn);
        btn7 = findViewById(R.id.sevenbtn);
        btn8 = findViewById(R.id.eightbtn);
        btn9 = findViewById(R.id.ninebtn);

        btnAdd = findViewById(R.id.plusbtn);
        btnMinus = findViewById(R.id.minusbtn);
        btnMultiply = findViewById(R.id.xbtn);
        btnDivide = findViewById(R.id.dividebtn);
        btnModulus = findViewById(R.id.modulusbtn);

        btnReset = findViewById(R.id.Cbtn);
        btnCalculate = findViewById(R.id.equalbtn);
        btnDecimal = findViewById(R.id.decimalbtn);
        btnDelete = findViewById(R.id.Backbtn);
    }


    private void setupClickListeners (){
    View.OnClickListener numberClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button clickedButton = (Button) v;
            String number = clickedButton.getText().toString();
            onNumberClick(number);
        }
    };

//    same listener for all number btns
        btn0.setOnClickListener(numberClickListener);
        btn1.setOnClickListener(numberClickListener);
        btn2.setOnClickListener(numberClickListener);
        btn3.setOnClickListener(numberClickListener);
        btn4.setOnClickListener(numberClickListener);
        btn5.setOnClickListener(numberClickListener);
        btn6.setOnClickListener(numberClickListener);
        btn7.setOnClickListener(numberClickListener);
        btn8.setOnClickListener(numberClickListener);
        btn9.setOnClickListener(numberClickListener);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorClick("+");
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorClick("-");
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorClick("x");
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorClick("*");
            }
        });

//        function
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqualsClick();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClick();

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResetClick();

            }
        });


    }

    private void onNumberClick(String number){

        if(isOperatorClicked){
            currentInput = "";
            isOperatorClicked=false;
        }
//        because dont want to be 02, 03 etc
        if(currentInput.equals("0") && !number.equals("0")){
            currentInput = number;
        }else{
            currentInput += number;
        }
        updateDisplay(currentInput);
    }

    private void onOperatorClick(String op){
        if(!currentInput.isEmpty()){
            if(!operator.isEmpty() && !isOperatorClicked){
                onEqualsClick();
            }

            firstNumber = Double.parseDouble(currentInput);
            operator = op;
            isOperatorClicked = true;
        }


    }

    private void onEqualsClick(){
        if(!operator.isEmpty() && !currentInput.isEmpty() &&!isOperatorClicked ){
            double secondNum = Double.parseDouble(currentInput);
            double result = performCalculation(firstNumber,secondNum,operator);

            updateDisplay(formatResult(result));
            currentInput = String.valueOf(result);
            operator = "";
            isOperatorClicked = true;
        }
    }


    private double performCalculation(double firstNumber,double secondNum,String operator){
        switch (operator){
            case "+":
                return firstNumber+secondNum;
            case "-":
                return firstNumber-secondNum;
            case "/":
                if(secondNum != 0 ){
                    return firstNumber/secondNum;
                }else{
                    updateDisplay("Error");
                    return 0;
                }
            case "x":
                return firstNumber * secondNum;
            default:
                return secondNum;

        }
    }


    private void onResetClick(){
        currentInput = "";
        operator = "";
        firstNumber = 0;
        isOperatorClicked = false;
        updateDisplay("0");

    }

    private void onDeleteClick(){
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            if (currentInput.isEmpty()) {
                updateDisplay("0");
            } else {
                updateDisplay(currentInput);
            }
        }
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.valueOf((long) result);
        } else {
            return String.valueOf(result);
        }
    }


    private void updateDisplay(String text){
        display.setText(text);
    }






}