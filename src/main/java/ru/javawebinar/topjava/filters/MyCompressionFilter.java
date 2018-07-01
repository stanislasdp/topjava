package ru.javawebinar.topjava.filters;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class MyCompressionFilter extends BaseFilter {

    private ServletContext context;
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        this.context = filterConfig.getServletContext();
        context.log(filterConfig.getFilterName() + " initialized");
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String validEncodings = request.getHeader("Accept-Encoding");
        if (validEncodings.contains("gzip")) {
            CompressionResponseWrapper wrappedResponse = new CompressionResponseWrapper(response);
            wrappedResponse.setHeader("Content-Encoding", "gzip");

            chain.doFilter(request, wrappedResponse);

            GZIPOutputStream gzipOutputStream = wrappedResponse.getServletGzipOs();
            gzipOutputStream.finish();

            context.log(filterConfig.getFilterName() + ": finished the request");
        } else {
            context.log(filterConfig.getFilterName() + " no encoding performed");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        context = null;
        filterConfig = null;
    }
}
