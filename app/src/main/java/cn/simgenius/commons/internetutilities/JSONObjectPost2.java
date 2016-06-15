package cn.simgenius.commons.internetutilities;

import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Genius on 3/14/16.
 */
public class JSONObjectPost2 extends AsyncTask<String,Void,JSONObject> {

    OnTaskFinishListener listener;
    Map<String,String> paramMap;
    String url;

    public JSONObjectPost2(String url){
        this.url = url;
        paramMap = new HashMap<String,String>();
    }

    @Override
    protected JSONObject doInBackground(String... request) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(request[0]).openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 设置以POST方式
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Charset", "UTF-8");
            // Post 请求不能使用缓存
            connection.setUseCaches(false);
            connection.connect();
            connection.getOutputStream().write(request[1].getBytes("utf-8"));
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int len;
            while ((len = inputStream.read(buffer))!= -1){
                outputStream.write(buffer,0,len);
            }



            String result = new String(outputStream.toByteArray(),"utf-8");
            return new JSONObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        listener.onTaskFinish(jsonObject);
    }

    public void executeTask(String url,Map<String,String> params, OnTaskFinishListener listener){
        StringBuilder builder = new StringBuilder();
        for(String key : params.keySet()){
            builder.append(key).append("=").append(params.get(key)).append("&");
        }
        execute(url,builder.toString().substring(0,builder.length()-1));
        this.listener = listener;
    }

    public interface OnTaskFinishListener{
        void onTaskFinish(JSONObject result);
    }

    public JSONObjectPost2 add(String key, String value){
        paramMap.put(key,value);
        return this;
    }
    public JSONObjectPost2 add(String key, long value){
        paramMap.put(key,Long.toString(value));
        return this;
    }
    public JSONObjectPost2 add(String key, int value){
        paramMap.put(key,Integer.toString(value));
        return this;
    }
    public JSONObjectPost2 add(String key, double value){
        paramMap.put(key,Double.toString(value));
        return this;
    }
    public JSONObjectPost2 add(String key, float value){
        paramMap.put(key,Float.toString(value));
        return this;
    }
    public JSONObjectPost2 add(String key, boolean value){
        paramMap.put(key,Boolean.toString(value));
        return this;
    }

    public void send(OnTaskFinishListener finishMethod){
        this.listener = finishMethod;
        if(this.url != null){
            executeTask(this.url, this.paramMap, this.listener);
        }
    }
}
