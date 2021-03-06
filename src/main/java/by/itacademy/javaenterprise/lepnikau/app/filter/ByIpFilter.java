package by.itacademy.javaenterprise.lepnikau.app.filter;

import by.itacademy.javaenterprise.lepnikau.app.service.IpAddrService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class ByIpFilter extends HttpFilter {
    private IpAddrService ipAddrService;

    @Override
    public void init() throws ServletException {
        ipAddrService = new IpAddrService();
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String remoteAddr = req.getRemoteAddr();

        if (ipAddrService.isExist(remoteAddr)) {
            chain.doFilter(req, res);
        } else {
            res.getWriter().write("Ip address " + remoteAddr + " not allowed");
        }
    }
}
