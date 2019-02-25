package com.example.kolobova_irina.irinakolobovahw6;
/**
    Author: Irina Vadimovna Kolobova
    Course: CS 211 D
    Date: November 19 2018
    Homework #: 6
    Objective: Application which asks user to input name of the state or capital
               and shows corresponding capital or state by reading the file with
               list of states and capitals.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.io.InputStream;
import java.util.Scanner;

public class US_States extends AppCompatActivity
{
    private Button resultButton;
    private EditText enterText;
    private TextView resultText;
    private String userInput;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_us__states);

        resultButton = findViewById(R.id.resultButton);
        enterText = findViewById(R.id.enterText);
        resultText = findViewById(R.id.resultText);
    }


    public void onShowResult(View view)
    {
        boolean resultFound = false;
        userInput = enterText.getText().toString();

        String states[]   = new String[50];
        String capitals[] = new String[50];

        parseUSStates(states, capitals);

        int i = 0;
        while(resultFound == false && i < states.length)
        {

            if(states[i].equalsIgnoreCase(userInput))
            {
                resultText.setText(capitals[i]);
                resultFound = true;
            } else if (capitals[i].equalsIgnoreCase(userInput))
            {
                resultText.setText(states[i]);
                resultFound = true;
            }

            i++;
        }

        /*for(int i = 0; i < states.length; i++)
        {
            if(states[i].equalsIgnoreCase(userInput))
            {
                resultText.setText(capitals[i]);
                resultFound = true;
                break;
            } else if (capitals[i].equalsIgnoreCase(userInput))
            {
                resultText.setText(states[i]);
                resultFound = true;
                break;
            }
        }*/

        if(resultFound == false)
        {
            resultText.setText("Incorrect input.");
        }
    }

    private void parseUSStates(String states[], String capitals[])
    {
        InputStream input = getResources().openRawResource(R.raw.us_states);

        Scanner sc = new Scanner(input);
        String line;
        int i = 0;

        sc.nextLine();
        sc.nextLine(); // skip over couple of headers

        while(sc.hasNext())
        {
            line = sc.nextLine();
            String temp[] = line.split("\\s\\s+");
            if(temp.length >= 2)
            {
                if(temp.length == 2)
                {
                    states[i]     = temp[0];
                    capitals[i++] = temp[1];
                }
                else
                {
                    states[i]     = temp[0] + " " + temp[1];
                    capitals[i++] = temp[2];
                }
            }
        }
    }


}
