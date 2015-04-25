// Muhammad Yeasin --- Matrikelnr : 115315
// Moitree Chowdhury --- Matrikelnr : 115316

package mmbuw.com.brokenproject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by E6410 on 4/25/2015.
 */
public class HTTPUtil {

    public static String retrieve(String url){
        DefaultHttpClient client = new DefaultHttpClient();

        try{

            HttpGet getReq = new HttpGet(url);
            HttpResponse getResponse = client.execute(getReq);
            int statusCode = getResponse.getStatusLine().getStatusCode();

            if(statusCode == HttpStatus.SC_OK){
                HttpEntity entity = getResponse.getEntity();
                if(entity != null){
                    String responseStr = EntityUtils.toString(entity);
                    return responseStr;
                }
                else{
                    return null;
                }
            }
        }catch(ClientProtocolException e){
            e.printStackTrace();
            return null;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }catch (IllegalStateException e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
