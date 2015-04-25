// Muhammad Yeasin --- Matrikelnr : 115315
// Moitree Chowdhury --- Matrikelnr : 115316

package mmbuw.com.brokenproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import mmbuw.com.brokenproject.R;

public class AnotherBrokenActivity extends Activity {

    private TextView txtEnteredUrl;
    private TextView txtHttpResponse;
    public String URL = "http://";

    ProgressDialog pd;
    String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_broken);

        txtEnteredUrl = (TextView) findViewById(R.id.txtEnteredUrl);
        txtHttpResponse = (TextView) findViewById(R.id.etHttpResponse);

        Intent intent = getIntent();
        String message = intent.getStringExtra(BrokenActivity.EXTRA_MESSAGE);
        //What happens here? What is this? It feels like this is wrong.
        //Maybe the weird programmer who wrote this forgot to do something?

        URL = URL + message + ".com";

        txtEnteredUrl.setText("" + URL);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.another_broken, menu);
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

    class RequestThread extends Thread{
        public void run(){
            response = HTTPUtil.retrieve(URL.toString());

            if(response != null){
                handler.sendEmptyMessage(0);
            }
            else{
                handler.sendEmptyMessage(-1);
            }
        }
    }

    Handler handler = new Handler(){

        public void handleMessage(android.os.Message msg){
            if(msg.what >= 0){
                txtHttpResponse.setText(response);
            }
            else{
                txtHttpResponse.setText("Error Occured");
            }
            pd.dismiss();
        }
    };

    public void fetchHTML(View view) throws IOException {

        printShort(URL);

        pd = ProgressDialog.show(AnotherBrokenActivity.this,"We are connecting","Loading...");

        RequestThread rThread = new RequestThread();
        rThread.start();

        //printLong(response);

       // txtHttpResponse.setText("Connected");

        //According to the exercise, you will need to add a button and an EditText first.
        //Then, use this function to call your http requests
        //Following hints:
        //Android might not enjoy if you do Networking on the main thread, but who am I to judge?
        //An app might not be allowed to access the internet without the right (*hinthint*) permissions
        //Below, you find a staring point for your HTTP Requests - this code is in the wrong place and lacks the allowance to do what it wants
        //It will crash if you just un-comment it.

        /*
        Beginning of helper code for HTTP Request.

        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(new HttpGet("http://lmgtfy.com/?q=android+ansync+task"));
        StatusLine status = response.getStatusLine();
        if (status.getStatusCode() == HttpStatus.SC_OK){
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            response.getEntity().writeTo(outStream);
            String responseAsString = outStream.toString();
             System.out.println("Response string: "+responseAsString);
        }else {
            //Well, this didn't work.
            response.getEntity().getContent().close();
            throw new IOException(status.getReasonPhrase());
        }

          End of helper code!

                  */
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
