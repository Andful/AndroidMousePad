package com.example.andrea.myapplication;

import android.util.Log;

import java.io.IOException;
import java.net.Socket;

import static java.lang.Math.max;

public class NetworkThread implements Runnable {

    static final int TARGET_FPS = 60;
    static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
    volatile boolean running=true;
    final Updatable updatable;

    public NetworkThread(Updatable updatable) {
        this.updatable=updatable;
    }
    @Override
    public void run() {
        Log.d("Andrea","hi");
        try(Socket socket=new Socket("192.168.178.122",8080)) {
            updatable.setInputStream(socket.getInputStream());
            updatable.setOutputStream(socket.getOutputStream());
            gameLoop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Andrea","loop ended");
    }
    public void gameLoop() {
        long lastLoopTime = System.nanoTime();
        while(running) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double) OPTIMAL_TIME);

            updatable.update(delta);

            try{
                Thread.sleep( max(0,(lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 ));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void stop() {
        running=false;
    }
}
