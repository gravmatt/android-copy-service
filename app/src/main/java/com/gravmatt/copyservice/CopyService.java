package com.gravmatt.copyservice;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class CopyService extends Service {
    private CopyHandler _copyHandler;
    private  Context context;

    public CopyService() {
    }

    private final class CopyHandler extends Handler {
        public static final String CATEGORY_COPY_TEXT = "copytext";

        public CopyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Intent intent = (Intent)msg.obj;

            if(intent.hasCategory(CATEGORY_COPY_TEXT)) {
                try {
                    FileInputStream fis = context.openFileInput("copy.txt");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader bufferedReader = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line + "\r\n");
                    }
                    Log.i("CopyHandler", sb.toString());

                    ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("copytext", sb);
                    clipboard.setPrimaryClip(clip);
                }
                catch(FileNotFoundException ex) {
                    Log.i("CopyHandler", "File not found");
                }
                catch(IOException ex) {
                    Log.i("CopyHandler", "Read file failed");
                }
            }
        }
    }

    @Override
    public void onCreate() {
        Log.i("CopyHandler", "On Create");
        context = getApplicationContext();
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        _copyHandler = new CopyHandler(thread.getLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("CopyHandler", "On Start Command. startId: " + startId + ", flags: " + flags);
        Message msg = _copyHandler.obtainMessage();
        msg.arg1 = startId;
        msg.obj = intent;
        _copyHandler.sendMessage(msg);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }

    @Override
    public void onDestroy() { }
}
