package ru.javawebinar.topjava.filters;

import lombok.SneakyThrows;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;

public class CompressionResponseWrapper extends HttpServletResponseWrapper {

    private GZIPServletOutputStream servletGzipOs = null;
    private PrintWriter printWriter = null;
    private Object streamUsed = null;

    public CompressionResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public GZIPOutputStream getServletGzipOs() {
        return servletGzipOs.getInternalGzipOs();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (Objects.nonNull(streamUsed) && (streamUsed != printWriter)) {
            throw new IllegalStateException();
        }
        return servletGzipOs;
    }

    @Override
    @SneakyThrows
    public PrintWriter getWriter() {
        if (Objects.nonNull(streamUsed) && (streamUsed != servletGzipOs)) {
            throw new IllegalStateException();
        }
        if (Objects.isNull(printWriter)) {
            servletGzipOs = new GZIPServletOutputStream(getResponse().getOutputStream());
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(servletGzipOs, getResponse().getCharacterEncoding());
            printWriter = new PrintWriter(outputStreamWriter);
            streamUsed = printWriter;
        }
        return printWriter;
    }
}
