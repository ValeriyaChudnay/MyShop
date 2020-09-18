package filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            request.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
    public void init(FilterConfig fConfig) throws ServletException {
        encoding = fConfig.getInitParameter("encoding");
    }

}