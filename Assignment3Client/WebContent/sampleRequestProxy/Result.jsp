<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleRequestProxyid" scope="session" class="com.mcds5510.request.RequestProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleRequestProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleRequestProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleRequestProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        com.mcds5510.request.Request getRequest10mtemp = sampleRequestProxyid.getRequest();
if(getRequest10mtemp == null){
%>
<%=getRequest10mtemp %>
<%
}else{
        if(getRequest10mtemp!= null){
        String tempreturnp11 = getRequest10mtemp.toString();
        %>
        <%=tempreturnp11%>
        <%
        }}
break;
case 13:
        gotMethod = true;
        String id_1id=  request.getParameter("id16");
            java.lang.String id_1idTemp = null;
        if(!id_1id.equals("")){
         id_1idTemp  = id_1id;
        }
        String nameOnCard_2id=  request.getParameter("nameOnCard18");
            java.lang.String nameOnCard_2idTemp = null;
        if(!nameOnCard_2id.equals("")){
         nameOnCard_2idTemp  = nameOnCard_2id;
        }
        String cardNumber_3id=  request.getParameter("cardNumber20");
            java.lang.String cardNumber_3idTemp = null;
        if(!cardNumber_3id.equals("")){
         cardNumber_3idTemp  = cardNumber_3id;
        }
        String unitPrice_4id=  request.getParameter("unitPrice22");
            java.lang.String unitPrice_4idTemp = null;
        if(!unitPrice_4id.equals("")){
         unitPrice_4idTemp  = unitPrice_4id;
        }
        String quantity_5id=  request.getParameter("quantity24");
            java.lang.String quantity_5idTemp = null;
        if(!quantity_5id.equals("")){
         quantity_5idTemp  = quantity_5id;
        }
        String expDate_6id=  request.getParameter("expDate26");
            java.lang.String expDate_6idTemp = null;
        if(!expDate_6id.equals("")){
         expDate_6idTemp  = expDate_6id;
        }
        java.lang.String update13mtemp = sampleRequestProxyid.update(id_1idTemp,nameOnCard_2idTemp,cardNumber_3idTemp,unitPrice_4idTemp,quantity_5idTemp,expDate_6idTemp);
if(update13mtemp == null){
%>
<%=update13mtemp %>
<%
}else{
        String tempResultreturnp14 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(update13mtemp));
        %>
        <%= tempResultreturnp14 %>
        <%
}
break;
case 28:
        gotMethod = true;
        String id_7id=  request.getParameter("id31");
            java.lang.String id_7idTemp = null;
        if(!id_7id.equals("")){
         id_7idTemp  = id_7id;
        }
        java.lang.String delete28mtemp = sampleRequestProxyid.delete(id_7idTemp);
if(delete28mtemp == null){
%>
<%=delete28mtemp %>
<%
}else{
        String tempResultreturnp29 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(delete28mtemp));
        %>
        <%= tempResultreturnp29 %>
        <%
}
break;
case 33:
        gotMethod = true;
        String id_8id=  request.getParameter("id36");
            java.lang.String id_8idTemp = null;
        if(!id_8id.equals("")){
         id_8idTemp  = id_8id;
        }
        String nameOnCard_9id=  request.getParameter("nameOnCard38");
            java.lang.String nameOnCard_9idTemp = null;
        if(!nameOnCard_9id.equals("")){
         nameOnCard_9idTemp  = nameOnCard_9id;
        }
        String cardNumber_10id=  request.getParameter("cardNumber40");
            java.lang.String cardNumber_10idTemp = null;
        if(!cardNumber_10id.equals("")){
         cardNumber_10idTemp  = cardNumber_10id;
        }
        String unitPrice_11id=  request.getParameter("unitPrice42");
            java.lang.String unitPrice_11idTemp = null;
        if(!unitPrice_11id.equals("")){
         unitPrice_11idTemp  = unitPrice_11id;
        }
        String quantity_12id=  request.getParameter("quantity44");
            java.lang.String quantity_12idTemp = null;
        if(!quantity_12id.equals("")){
         quantity_12idTemp  = quantity_12id;
        }
        String expDate_13id=  request.getParameter("expDate46");
            java.lang.String expDate_13idTemp = null;
        if(!expDate_13id.equals("")){
         expDate_13idTemp  = expDate_13id;
        }
        java.lang.String create33mtemp = sampleRequestProxyid.create(id_8idTemp,nameOnCard_9idTemp,cardNumber_10idTemp,unitPrice_11idTemp,quantity_12idTemp,expDate_13idTemp);
if(create33mtemp == null){
%>
<%=create33mtemp %>
<%
}else{
        String tempResultreturnp34 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(create33mtemp));
        %>
        <%= tempResultreturnp34 %>
        <%
}
break;
case 48:
        gotMethod = true;
        String id_14id=  request.getParameter("id51");
            java.lang.String id_14idTemp = null;
        if(!id_14id.equals("")){
         id_14idTemp  = id_14id;
        }
        java.lang.String view48mtemp = sampleRequestProxyid.view(id_14idTemp);
if(view48mtemp == null){
%>
<%=view48mtemp %>
<%
}else{
        String tempResultreturnp49 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(view48mtemp));
        %>
        <%= tempResultreturnp49 %>
        <%
}
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>