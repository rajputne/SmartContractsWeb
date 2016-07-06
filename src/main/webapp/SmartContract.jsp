<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sales List</title>
    </head>
    <body>
        <form name="form1" action="ElasticSearch">
            Select a file to upload:
            <input type="file" id="myFile" size="50">

            <p>Click the button below do the display the file path of the file upload button above (you must select a file first).</p>
            <button type="submit" name="check" id="check">Check The File</button>
            <input type="hidden" name="hiddenPath" value=""/>  
            <c:set var="hiddenPath" value="getHiddenPath()"/>
            <input type="hidden" name="hiddenFileName" value=""/>  
            <input type="hidden" name="page" value="1"/>
            <input type="hidden" name="action" value="getFile"/>
            <p id="demo" name="demo"></p>
            

        </form>

        <script>
            $(document).ready(function () {

                $("#check").click(function () {
                    var x = document.getElementById("myFile").value;
                    var y = x.replace(/^.*[\\\/]/, '');
                    var val = y.split('.');
                    document.getElementById("demo").innerHTML = x;
                    var path = x.substring(0, x.lastIndexOf("\\") + 1);
                    document.form1.hiddenPath.value = path;
                    document.form1.hiddenFileName.value = y;
                    for (var i = 0; i < val.length; i++)
                    {
                        if (val[i].length != 1) {
                            if (i == 1)
                            {
                                if (val[i] == 'pdf')
                                {
                                    alert(val[i]);
                                } else
                                {
                                    return false;
                                }
                            }
                        }
                    }
                });
            });

        </script>
    </body>
</html>
