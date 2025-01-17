package com.training.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCountListener
 *
 */
@WebListener
public class SessionCountListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent se)  {     	
    	ServletContext sCtx = se.getSession().getServletContext();
    	Integer sessionCounter = (Integer) sCtx.getAttribute("sessionCounter");
    	if(sessionCounter == null)
    		sessionCounter = 0;
    	
    	sessionCounter ++;
    	sCtx.setAttribute("sessionCounter", sessionCounter);
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	ServletContext sCtx = se.getSession().getServletContext();
    	Integer sessionCounter = (Integer) sCtx.getAttribute("sessionCounter");
    	sessionCounter --;
    	sCtx.setAttribute("sessionCounter", sessionCounter);

    }
	
}
