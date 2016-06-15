package com.wt.xwttravel.util;

import android.app.Fragment;
import android.content.Intent;

/**
 * Created by Genius on 3/3/16.
 */
public abstract class MessageFragment extends Fragment {
    OnMessageCallbackListener callbackListener;

    public interface OnMessageCallbackListener{
        void onMessageCallback(Intent message);//
    }
    public void setCallbackListener(OnMessageCallbackListener listener){
        callbackListener = listener;
    }

    public abstract void onMessageArrived(Intent message);

    private void sendMessage(Intent message){
        onMessageArrived(message);
    }

    public void sendbackMessage(Intent message){
        callbackListener.onMessageCallback(message);
    }


}
