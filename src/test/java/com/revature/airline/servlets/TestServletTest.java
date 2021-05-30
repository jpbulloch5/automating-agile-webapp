package com.revature.airline.servlets;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


public class TestServletTest extends Mockito {

    @Test
    public void testServletTest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        when(request.getParameter("testParameter")).thenReturn("test");

        new InitServlet().doPost(request, response);

        verify(request, atLeastOnce()).getParameter("testParameter");
        writer.flush(); // it may not have been flushed yet...
        Assert.assertTrue(stringWriter.toString().contains("test"));
    }




}
