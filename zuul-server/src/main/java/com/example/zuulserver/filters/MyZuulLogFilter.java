package com.example.zuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;



@Component
public class MyZuulLogFilter extends ZuulFilter
{
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public String filterType() {
// pre : contiendra les filtres exécutés avant le routage d’une requête (règles de pré-routage).
        return "pre";
    }
    @Override
    public int filterOrder() { return 1; }
    @Override
    public boolean shouldFilter() { return true; }
    @Override
    public Object run() throws ZuulException {
        HttpServletRequest req = RequestContext.getCurrentContext().getRequest();
        log.info("**** MyZuulLogFilter : Requête interceptée ! L'URL est : {} ", req.getRequestURL());
        return null;
    }
    }
