package cn.simgenius.commons.internetutilities;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Genius on 3/14/16.
 */
public class JSONArrayGet extends AsyncTask<String,Void,JSONArray> {



    OnTaskFinishListener listener;

    public JSONArrayGet(String url, OnTaskFinishListener listener) {
        executeTask(url,listener);
    }


    @Override
    protected JSONArray doInBackground(String... url) {
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
            return new JSONArray(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONArray jsonObject) {
        super.onPostExecute(jsonObject);
        listener.onTaskFinish(jsonObject);
    }


    public void executeTask(String url, OnTaskFinishListener listener){
        execute(url);
        this.listener = listener;
    }

    public interface OnTaskFinishListener{
        void onTaskFinish(JSONArray result);
    }
}
