package com.zhuermu.gzh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhuermu.gzh.common.util.SignUtil;

/**
 * receive request from wechat gzh 
 * @author lxw
 *
 */
@SuppressWarnings("serial")
public class WxGzhServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ΢�ż���ǩ��
        String signature = request.getParameter("signature");
        // ʱ��¾
        String timestamp = request.getParameter("timestamp");
        // �����
        String nonce = request.getParameter("nonce");
        // ����ַ���
        String echostr = request.getParameter("echostr"); 
         
        PrintWriter out = response.getWriter();
        // ͨ������ signature ���������У�飬��У��ɹ���ԭ������ echostr����ʾ����ɹ����������ʧ��
       if(SignUtil.checkSignature(signature, timestamp, nonce)){
           out.print(echostr);
       }
 
       out.close();
       out = null; 
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	public void init() throws ServletException {
    }
}
