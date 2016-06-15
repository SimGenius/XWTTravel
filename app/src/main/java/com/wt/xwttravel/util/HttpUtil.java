
package com.wt.xwttravel.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;

import org.json.JSONObject;

public class HttpUtil {

    private static final String BOUNDARY = UUID.randomUUID().toString(); // 边界标识

    public JSONObject post(String url, Map<String, String> textParams, Map<String, String> fileparams) throws Exception {
        byte bytes[];
        if(fileparams==null||fileparams.keySet().size()==0) {
            bytes = postForm(url, textParams);
        }else{
            bytes = postMultiForm(url, textParams, fileparams);
        }
        return new JSONObject(new String(bytes, "UTF-8"));
    }



    // 发送数据到服务器，返回一个字节包含服务器的返回结果的数组
    public byte[] postMultiForm(String url, Map<String, String> textParams, Map<String, String> fileparams) throws Exception {
        HttpURLConnection conn = initConnection(url);
        conn.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + BOUNDARY);
        conn.connect();
        DataOutputStream ds = new DataOutputStream(conn.getOutputStream());
        if (fileparams != null) writeFileParams(ds, fileparams);
        if (textParams != null) writeStringParams(ds, textParams);
        ds.writeBytes("--" + BOUNDARY + "--" + "\r\n");
        ds.writeBytes("\r\n");
        InputStream inStream = conn.getInputStream();
        return readInputStream(inStream);
    }



    public byte[] postForm(String urlStr, Map<String, String> textParams) throws Exception {
        HttpURLConnection urlConn = initConnection(urlStr);
        urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConn.connect();
        urlConn.getOutputStream().write(getPostBody(textParams));// 输入参数
        InputStream inStream = urlConn.getInputStream();
        return readInputStream(inStream);
    }



    //文件上传的connection的一些必须设置
    private HttpURLConnection initConnection(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        // 使用HttpURLConnection打开连接
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        //因为这个是post请求,设立需要设置为true
        urlConn.setDoOutput(true);
        urlConn.setDoInput(true);
        // 设置以POST方式
        urlConn.setRequestMethod("POST");
        urlConn.setRequestProperty("Charset", "UTF-8");
        // Post 请求不能使用缓存
        urlConn.setUseCaches(false);
        return urlConn;
    }



    //普通字符串数据
    private void writeStringParams(DataOutputStream ds, Map<String, String> textParams) throws Exception {
        Set<String> keySet = textParams.keySet();
        for (Iterator<String> it = keySet.iterator(); it.hasNext(); ) {
            String name = it.next();
            String value = textParams.get(name);
            ds.writeBytes("--" + BOUNDARY + "\r\n");
            ds.writeBytes("Content-Disposition: form-data; name=\"" + name
                    + "\"\r\n");
            ds.writeBytes("\r\n");
            ds.writeBytes(encode(value) + "\r\n");
        }
    }



    //文件数据
    private void writeFileParams(DataOutputStream ds, Map<String, String> fileparams) throws Exception {
        Set<String> keySet = fileparams.keySet();
        for (Iterator<String> it = keySet.iterator(); it.hasNext(); ) {
            String name = it.next();
            File value = new File(fileparams.get(name));
            ds.writeBytes("--" + BOUNDARY + "\r\n");
            ds.writeBytes("Content-Disposition: form-data; name=\"" + name
                    + "\"; filename=\"" + encode(value.getName()) + "\"\r\n");
            ds.writeBytes("Content-Type: " + getContentType(value) + "\r\n");
            ds.writeBytes("\r\n");
            ds.write(getBytes(value));
            ds.writeBytes("\r\n");
        }
    }



    //获取文件的上传类型，图片格式为image/png,image/jpg等。非图片为application/octet-stream
    private String getContentType(File f) throws Exception {
        int lastDot = f.getName().lastIndexOf(".");
        if (lastDot < 0)
            return "application/octet-stream";
        String ext = f.getName().substring(lastDot + 1).toUpperCase();
        if (ext.equals("JPG")) return "image/jpeg";
        else if (ext.equals("JPG")) return "image/jpeg";
        else if (ext.equals("JPEG")) return "image/jpeg";
        else if (ext.equals("GIF")) return "image/gif";
        else if (ext.equals("PNG")) return "image/png";
        else return "application/octet-stream";
    }



    //把文件转换成字节数组
    private byte[] getBytes(File f) throws Exception {
        FileInputStream in = new FileInputStream(f);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int n;
        while ((n = in.read(b)) != -1) {
            out.write(b, 0, n);
        }
        in.close();
        return out.toByteArray();
    }



    // 对包含中文的字符串进行转码，此为UTF-8。服务器那边要进行一次解码
    private String encode(String value) throws Exception {
        if(value==null)return "";
        return URLEncoder.encode(value, "UTF-8");
    }



    public byte[] getPostBody(Map<String, String> paramsMap) throws Exception {
        StringBuffer params = new StringBuffer();
        if (paramsMap != null && !paramsMap.isEmpty()) {
            int i = 0;
            for (String key : paramsMap.keySet()) {
                // 表单参数与get形式一样
                i++;
                params.append(key).append("=").append(encode(paramsMap.get(key)));
                if (i < paramsMap.values().size()) params.append("&");
            }
        }
        return params.toString().getBytes("utf-8");
    }



    public byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();//网页的二进制数据
        outStream.close();
        inStream.close();
        return data;
    }



    public void downFile(String urlStr, String path, String filename) throws IOException {
        HttpURLConnection con = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConn = (HttpURLConnection) url
                    .openConnection();
            urlConn.setConnectTimeout(10 * 1000);
            urlConn.setReadTimeout(10 * 1000);
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            InputStream input = urlConn.getInputStream();
            File folderFile = new File(path);
            if (!folderFile.exists()) {
                folderFile.mkdir();
            }
            File file = new File(path + File.separator + filename);
            file.createNewFile();
            FileOutputStream output = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int c = 0;
            while ((c = input.read(buffer, 0, 1024)) > 0) {
                output.write(buffer, 0, c);
            }
            output.flush();
            input.close();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }



    public Bitmap getBitmapFormUrl(String url) {
        Bitmap bitmap = null;
        HttpURLConnection con = null;
        try {
            URL mImageUrl = new URL(url);
            con = (HttpURLConnection) mImageUrl.openConnection();
            con.setConnectTimeout(10 * 1000);
            con.setReadTimeout(10 * 1000);
            con.setDoInput(true);
            con.setDoOutput(true);
            bitmap = BitmapFactory.decodeStream(con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return bitmap;
    }
}
