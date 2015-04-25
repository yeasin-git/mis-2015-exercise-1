// Muhammad Yeasin --- Matrikelnr : 115315
// Moitree Chowdhury --- Matrikelnr : 115316

package mmbuw.com.brokenproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class BrokenActivity extends Activity{

    static String EXTRA_MESSAGE = "Hello";

    private EditText auntEdith;
    private EditText auntEdit;
    private Button btnSentRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broken);
        auntEdit = (EditText)findViewById(R.id.edittext);
        btnSentRequest = (Button) findViewById(R.id.btnSentRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.broken, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void brokenFunction(View v){
        //I was once, perhaps, possibly a functioning function

        //auntEdit.setText("Hello Yeasin From Button Click");

        printShort("Bug Fixed");

        //Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();


        if (auntEdit.getText().toString().equals("Timmy")){
            System.out.println("Timmy fixed a bug!");
        }

        System.out.println("If this appears in your console, you fixed a bug.");
        Intent intent = new Intent(this,AnotherBrokenActivity.class);
        String message = auntEdit.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);

    }

    public void printLong(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void printShort(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
