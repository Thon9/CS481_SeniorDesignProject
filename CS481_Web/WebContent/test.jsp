<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hidden test page</title>
</head>
<body>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script type="text/javascript" src="JS/jquery-1.4.2.min.js"></script>
    <script src="JS/jquery.autocomplete.js"></script>
</head>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%
    String countries[] = {
                            "Afghanistan",
                            "Albania",
                            "Algeria",
                            "Andorra",
                            "Angola",
                            "Antigua and Barbuda",
                            "Argentina",
                            "Armenia",
                            "Yemen",
                            "Zambia",
                            "Zimbabwe"
                            };
 
    String query = (String)request.getParameter("q");
    //System.out.println("1"+request.getParameterNames().nextElement());
    response.setHeader("Content-Type", "text/html");
    int cnt=1;
    for(int i=0;i<countries.length;i++)
    {
        if(countries[i].toUpperCase().startsWith(query.toUpperCase()))
        {
            out.print(countries[i]+"\n");
            if(cnt>=10){
                break;
            }
            cnt++;
        }
    }
%>

<body>
    <div style="width: 300px; margin: 50px auto;">
        <b>Country</b>   : <input type="text" id="country" name="country" class="input_text"/>
    </div>
 
</body>
<script>
    jQuery(function(){
        $("#country").autocomplete("list.jsp");
    });
</script>
</html>