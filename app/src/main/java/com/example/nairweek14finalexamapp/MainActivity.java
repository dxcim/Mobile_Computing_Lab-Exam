package com.example.nairweek14finalexamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView workings;
    TextView results;

    String prblm = "";
    String temp1 = "";
    String temp2 = "";

    boolean lbracket = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTV();
    }

    private void initTV() {
        workings = (TextView)findViewById(R.id.quest);
        results = (TextView)findViewById(R.id.soln);
    }

    private void appworkings(String inputs) {
        prblm = prblm + inputs;
        workings.setText(prblm);
    }

    public void tapclr(View view) {
        workings.setText("");
        prblm = "";
        lbracket = true;
        results.setText("");
    }

    public void tapbracket(View view) {
        if(lbracket) {
            appworkings("(");
            lbracket = false;
        }
        else {
            appworkings(")");
            lbracket = true;
        }
    }

    public void tapexp(View view) {
        appworkings("^");
    }

    public void tapdiv(View view) {
        appworkings("/");
    }

    public void tap7(View view) {
        appworkings("7");
    }

    public void tap8(View view) {
        appworkings("8");
    }

    public void tap9(View view) {
        appworkings("9");
    }

    public void tapmult(View view) {
        appworkings("*");
    }

    public void tap4(View view) {
        appworkings("4");
    }

    public void tap5(View view) {
        appworkings("5");
    }

    public void tap6(View view) {
        appworkings("6");
    }

    public void tapsub(View view) {
        appworkings("-");
    }

    public void tap1(View view) {
        appworkings("1");
    }

    public void tap2(View view) {
        appworkings("2");
    }

    public void tap3(View view) {
        appworkings("3");
    }

    public void tapadd(View view) {
        appworkings("+");
    }

    public void tapdot(View view) {
        appworkings(".");
    }

    public void tap0(View view) {
        appworkings("0");
    }

    public void tapeql(View view) {
        Double answer = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        expcalc();
        try {
            answer = (double)engine.eval(temp1);
        } catch (ScriptException e) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if(answer != null)
            results.setText(String.valueOf(answer.doubleValue()));

    }

    private void expcalc() {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i=0;i<prblm.length();i++) {
            if(prblm.charAt(i) == '^')
                indexOfPowers.add(i);
        }
        temp1 = prblm;
        temp2 = prblm;

        for(Integer index: indexOfPowers) {
            convexp(index);
        }
        temp1 = temp2;
    }

    private void convexp(Integer index) {
        String lnum = "";
        String rnum = "";

        for(int i = index+1; i<prblm.length();i++) {
            if(isNumeric(prblm.charAt(i)))
                rnum = rnum + prblm.charAt(i);
            else
                break;;
        }

        for(int i = index-1; i>=0;i--) {
            if(isNumeric(prblm.charAt(i)))
                lnum = lnum + prblm.charAt(i);
            else
                break;;
        }

        String ori = lnum+"^"+rnum;
        String conv = "Math.pow("+lnum+","+rnum+")";
        temp2=temp2.replace(ori,conv);
    }

    private boolean isNumeric(char c) {
        if((c <= '9' && c >= '0') || c == '.')
            return true;
        return false;
    }
}