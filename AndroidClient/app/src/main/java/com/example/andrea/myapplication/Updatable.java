package com.example.andrea.myapplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface Updatable {
    public void update(double delta);
    public void setOutputStream(OutputStream out);
    public void setInputStream(InputStream in);
}
