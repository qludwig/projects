<%-- 
    Document   : home
    Created on : Oct 22, 2015, 3:28:29 PM
    Author     : Suzanne Ludwig
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Share+Tech+Mono' rel='stylesheet' type='text/css'>
        <link href="${pageContext.request.contextPath}/css/machinestyles.css" rel="stylesheet">
        <title>Vending Machine</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div id="itemContent"></div>
                </div>


                <div class="col-md-6">
                    <div class="row">
                        <div id="totalDiv"></div>
                    </div>
                    <div class="row">
                        <div class="col-md-offset-2 col-md-2">
                            <p id="nickel-button" class="coin-button">5&#162;</p>
                        </div>
                        <div class="col-md-2">
                            <p id="dime-button" class="coin-button">10&#162;</p>
                        </div>
                        <div class="col-md-2">
                            <p id="quarter-button" class="coin-button">25&#162;</p>
                        </div>
                        <div class="col-md-2">
                            <p id="dollar-button" class="coin-button">$1</p>
                        </div>
                    </div>
                    <div id="changeReturnSection">

                        <div class="row">
                            <button id="change-return-button" class="btn btn-default">
                                Get Change</button>
                        </div>
                        <div class="row">
                            <div id="changeReturnDiv"></div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>


    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/machine.js"></script>
</html>
