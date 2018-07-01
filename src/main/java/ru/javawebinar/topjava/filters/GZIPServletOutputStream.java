package ru.javawebinar.topjava.filters;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GZIPServletOutputStream extends ServletOutputStream {

    private GZIPOutputStream internalGzipOs;

    public GZIPServletOutputStream(ServletOutputStream internalGzipOs) throws Exception {
        this.internalGzipOs = new GZIPOutputStream(internalGzipOs);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

    public GZIPOutputStream getInternalGzipOs() {
        return internalGzipOs;
    }

    @Override
    public void write(int b) throws IOException {
        internalGzipOs.write(b);
    }
}
