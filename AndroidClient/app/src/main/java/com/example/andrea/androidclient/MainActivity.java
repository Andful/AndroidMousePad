package com.example.andrea.androidclient;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements Updatable {

    private BluetoothAdapter bluetooth;
    private Set<BluetoothDevice> pairedDevices;
    private DataOutputStream out;

    private float x;
    private float y;
    private volatile float dx;
    private volatile float dy;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new NetworkThread(this)).start();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            x=event.getX();
            y=event.getY();
        } else if(event.getAction()==MotionEvent.ACTION_MOVE) {
            synchronized (this) {
                dx+=event.getX()-x;
                dy+=event.getY()-y;
            }
            x=event.getX();
            y=event.getY();
        }
        return false;
    }

    @Override
    public void update(double delta){
        if(dx!=0 || dy!=0) {
            synchronized (this) {
                try {
                    out.writeFloat(dx);
                    out.writeFloat(dy);
                    dx = 0;
                    dy = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void setOutputStream(OutputStream out) {
        this.out=new DataOutputStream(out);
    }

    @Override
    public void setInputStream(InputStream in) {

    }
}
