package General;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@WebFilter(filterName = "General.ServletDBFilter")
public class ServletDBFilter implements Filter {
    public ServletDBFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("General.ServletDBFilter init!");
    }

    @Override
    public void destroy() {
        System.out.println("General.ServletDBFilter destroy!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String servletPath = req.getServletPath();

        System.out.println("#INFO " + new Date()
                + " - ServletPath :" + servletPath + ", URL =" + req.getRequestURL());

        try {
            DBManager.OpenConnection();


        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


        // Go to the next element (filter or target) in chain.
        chain.doFilter(request, response);
    }

}
