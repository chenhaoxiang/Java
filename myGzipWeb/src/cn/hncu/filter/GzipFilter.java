package cn.hncu.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GzipFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		MyResponse resp = new MyResponse((HttpServletResponse) response);
		// 用增强版的resp放行到servlet中去用(让后台把数据 写到 baout中 )

		chain.doFilter(request, resp);// 放行--让后台去写

		// 从增强版的resp的baout中(存放的是源字节数据)，把数据取出来进行压缩，
		// 然后再压缩后的数据用原生的response输出到客户端
		ByteArrayOutputStream baout = resp.getBaout();
		byte bs[] = baout.toByteArray();// 源字节数据
		System.out.println("压缩前大小:" + bs.length);
		
		ByteArrayOutputStream baout2 = new ByteArrayOutputStream();
		GZIPOutputStream gout = new GZIPOutputStream(baout2);
		gout.write(bs);// 把数据压缩到baout中
		gout.close();
		
		bs = baout2.toByteArray();
		System.out.println("压缩后大小:" + bs.length);
		// 输出之前告诉客户端:我们的数据是gzip格式，然后输出
		HttpServletResponse httpResp = (HttpServletResponse) response;
		httpResp.setHeader("Content-Encoding", "gzip");
		httpResp.setContentLength(bs.length);
		OutputStream out = httpResp.getOutputStream();
		out.write(bs);
		
	}

	@Override
	public void destroy() {
	}

}

class MyResponse extends HttpServletResponseWrapper {
	private ByteArrayOutputStream baout = new ByteArrayOutputStream();

	public MyResponse(HttpServletResponse response) {
		super(response);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return new MyOutputStream(baout);
	}

	public ByteArrayOutputStream getBaout() {
		if(pw!=null){
			pw.flush();
			//这里很重要，如果不flush或close，不把字符流刷出去，baout中是不会有数据的.
		}
		return baout;
	}
	
	private PrintWriter pw;
	@Override
	public PrintWriter getWriter() throws IOException {
		pw = new PrintWriter(new OutputStreamWriter(baout, "utf-8"),true);
		return pw;
	}

}

class MyOutputStream extends ServletOutputStream {
	private ByteArrayOutputStream baout = null;

	public MyOutputStream(ByteArrayOutputStream baout) {
		this.baout = baout;
	}

	@Override
	public void write(int b) throws IOException {
		baout.write(b);
	}
}
