package cn.simgenius.commons.internetutilities;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by Genius on 3/14/16.
 */
public class JSONObjectGet extends AsyncTask<String,Void,JSONObject> {


    OnTaskFinishListener listener;

    public JSONObjectGet(String url, OnTaskFinishListener listener) {
        executeTask(url,listener);
    }

    @Override
    protected JSONObject doInBackground(String... url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url[0]).openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 设置以POST方式
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Charset", "UTF-8");
            // Post 请求不能使用缓存
            connection.setUseCaches(false);
            connection.connect();
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


    public void executeTask(String url, OnTaskFinishListener listener){
        execute(url);
        this.listener = listener;
    }

    public interface OnTaskFinishListener{
        void onTaskFinish(JSONObject result);
    }
}
