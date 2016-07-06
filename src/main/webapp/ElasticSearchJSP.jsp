<%-- 
    Document   : ElasticSearchJSP
    Created on : Jun 29, 2016, 10:10:26 PM
    Author     : neera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="SearchWords">
            <input type="text"><br>
            <input type="radio" name="ExactSearch" value="ExactSearch"> Exact Search<br>
            <input type="radio" name="DefinedTerm" value="definedTerm"> Defined Term Search<br>
            <input type="radio" name="SemanticSearch" value="SemanticSearch"> Semantic Search<br>
            <input type="radio" name="SynonymnSearch" value="SynonymnSearch"> Synonymn Search<br>
            <input type="Submit" value="Submit">
        </form>
    </body>
</html>
