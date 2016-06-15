package cn.simgenius.test.geniusnetworkutility.util;

import android.os.AsyncTask;

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
public class JSONObjectPost extends AsyncTask<String,Void,JSONObject> {


    OnTaskFinishListener listener;

    public JSONObjectPost(String url, Map<String, String> params, OnTaskFinishListener listener) {
        executeTask(url,params,listener);
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
}
