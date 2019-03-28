package fi.oulu.tol.simplecalculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int sum = 0;
    int previous = 0;
    int number = 0;
    String text = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        Button button5 = (Button)findViewById(R.id.button5);
        Button button6 = (Button)findViewById(R.id.button6);
        Button button7 = (Button)findViewById(R.id.button7);
        Button button8 = (Button)findViewById(R.id.button8);
        Button button9 = (Button)findViewById(R.id.button9);
        Button button0 = (Button)findViewById(R.id.button0);
        Button buttonAdd = (Button)findViewById(R.id.buttonAdd);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        EditText et = (EditText)findViewById(R.id.plain_text_input);
        boolean cont = true;


            switch (v.getId()) {
                case R.id.button1:
                    number = 1;
                    text = "" + number;
                    //sum = calculation(1);
                    //text = "" + sum;
                    //sum = previous + 1;
                    //text = previous + " + 1 =" + sum;
                    //previous = sum;
                    break;
                case R.id.button2:
                    number = 2;
                    text = "" + number;
                    //sum = calculation(2);
                    //text = "2";
                    break;
                case R.id.button3:
                    number = 3;
                    text = "" + number;
                    break;
                case R.id.button4:
                    number = 4;
                    text = "" + number;
                    break;
                case R.id.button5:
                    number = 5;
                    text = "" + number;
                    break;
                case R.id.button6:
                    number = 6;
                    text = "" + number;
                    break;
                case R.id.button7:
                    number = 7;
                    text = "" + number;
                    break;
                case R.id.button8:
                    number = 8;
                    text = "" + number;
                    break;
                case R.id.button9:
                    number = 9;
                    text = "" + number;
                    break;
                case R.id.button0:
                    number = 0;
                    text = "" + number;
                    break;
                case R.id.buttonAdd:
                    sum = previous + number;
                    text = "" + sum;

            }
            previous = sum;
            et.setText(text);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_simple_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.clear:
                clear();
                return true;
            case R.id.store:
                sum = R.id.plain_text_input;
                store(sum);
                return true;
            case R.id.recall:
                recall();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void clear(){
        EditText et = (EditText)findViewById(R.id.plain_text_input);
        sum = 0;
        previous = 0;
        number = 0;
        text = "" + number;
        et.setText(text);
    }

    private void store(int number) {
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(RESULT, Context.MODE_PRIVATE);
            byte[] bytes = new byte[8];
            ByteBuffer.wrap(bytes).putDouble(number);
            outputStream.write(bytes);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recall() {
        FileInputStream inputStream;
        try {
            inputStream = openFileInput(RESULT);
            byte[] bytes = new byte[8];
            inputStream.read(bytes);
            EditText et = (EditText)findViewById(R.id.plain_text_input);
            et.setText("" + ByteBuffer.wrap(bytes).getDouble());
           // calculator.setResult(ByteBuffer.wrap(bytes).getDouble());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private final static String RESULT = "result";

   // public int recall(){

    //}

}
