<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <jsp:include page="../resources/css/bootstrap.css" />
    <jsp:include page="../resources/js/bootstrap.min.js" />
    <jsp:include page="../resources/js/jquery-3.3.1.min.js" />
    <jsp:include page="../resources/img/carousel/bkgrnd1.jpg" />
    <jsp:include page="../resources/img/carousel/bkgrnd2.jpg" />
    <jsp:include page="../resources/img/carousel/bkgrnd3.jpg" />

    <!-- If IE use the latest rendering engine -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Set the page to the width of the device and set the zoon level -->
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <title>Rent Your Dream</title>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    <style>
        .jumbotron{
            background-color:#1F1E1E;
            color:white;
            font-family: 'Lato';
        }

        /* Adds borders for tabs */
        .tab-content {
            border-left: 1px solid #ddd;
            border-right: 1px solid #ddd;
            border-bottom: 1px solid #ddd;
            padding: 10px;
        }
        .nav-tabs {
            margin-bottom: 0;
        }

        .red-style {
            background-color: #720400;
            color: white;
        }

        .red-style a {
            color: white;
        }

        /* Carousel Styling */
        .slide1{
            background-image: url('img/carousel/bkgrnd1.jpg');
            height: 500px;
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
        }
        .slide2{
            background-image: url('img/carousel/bkgrnd2.jpg');
            height: 500px;
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
        }
        .slide3{
            background-image: url('img/carousel/bkgrnd3.jpg');
            height: 500px;
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
        }
        .carousel-caption h1{
            font-size: 4em;
            font-family: 'Impact';
            padding-bottom: .1em;
        }
        .carousel-caption p{
            font-size: .1em;
        }

        .top-line {
            background-color: #1e1e1e;
            color: white;
            height: auto;
        }

        .top-line a {
            color: #a3a3a3;
        }

        .logo {
            color: red;
        }

        .logo a {
            color: #a00000;
            border: 2px solid #a00000;
            font-weight: bold;
        }

        .main-container {
            margin-top: 50px;
        }

        .error {
            color: red;
        }

        footer {
            margin-top: 100px;
            color: #a3a3a3;
            font-size: 12px;
        }

    </style>

</head>
<body>

<div class="top-line">
    <div class="container">
        <div class="row p-3 clearfix">

            <div class="col-md-3 col-sm-6 col-6 my-auto logo">
                <a class="btn" href="${pageContext.request.contextPath}/">RD</a>
            </div>

            <div class="col-md-3 col-sm-6 col-6 ml-auto text-right my-auto">
                <div class="dropdown show">
                    <a class="dropdown-style rounded p-2" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="d-md-inline d-none"><c:if test="${pageContext.request.userPrincipal.name != null}">${pageContext.request.userPrincipal.name}</c:if> </span><i class="fa fa-user"></i>
                    </a>

                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">
                        <c:if test="${pageContext.request.userPrincipal.name == null}"><a class="dropdown-item" href="${pageContext.request.contextPath}/login">Login</a></c:if>
                        <c:if test="${pageContext.request.userPrincipal.name == null}"><a class="dropdown-item" href="${pageContext.request.contextPath}/register">Register</a></c:if>
                        <security:authorize access="hasRole('ADMIN')">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/panel">Admin panel</a>
                        </security:authorize>
                        <security:authorize access="hasRole('USER')">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/cars">My cars</a>
                        </security:authorize>
                        <c:if test="${pageContext.request.userPrincipal.name != null}"><a class="dropdown-item" href="<c:url value="/logout" />">Logout</a></c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container main-container">

    <h1 class="mb-5">Cars found:</h1>

    <c:forEach var="tempCar" items="${cars}">
        <div class="row border mt-4">
            <div class="col-md-3 my-auto">
                <img class="img-fluid" src="/img/examples/${tempCar.brandName}-${tempCar.model}.jpg" alt="">
            </div>
            <div class="col-md-3 border-left p-3">
                <h2>${tempCar.brandName} ${tempCar.model}</h2>
                <h4>${tempCar.year}</h4>
                <dl class="">
                    <dd>Type: ${tempCar.type}</dd>
                    <dd>Engine capacity: ${tempCar.engineCapacity} cm<sup>3</sup></dd>
                    <dd>${tempCar.horsePower} HP</dd>
                    <dd>Number of cylinders: ${tempCar.numberOfCylinders}</dd>
                    <dd>Price for one day: ${tempCar.priceForOneDay} PLN</dd>
                </dl>
            </div>
            <div class="col-md-6 p-3">
                <div class="row">
                    <p class="m-3">${car.description}</p>

                    <a class="btn btn-primary m-3" href="${pageContext.request.contextPath}/admin/update/car?carId=${car.id}">Update</a>
                    <form:form action="/admin/delete/car?carId=${car.id}" method="DELETE">
                        <input type="submit" class="btn btn-danger m-3" value="Delete"/>
                    </form:form>
                </div>

                <c:if test="${account != null}">
                    <div class="border-top">
                        <p class="mt-3">Rented by ${account.firstName} ${account.lastName} (${account.email})</p>
                        <p>
                            <form:form action="/admin/return" method="POST">
                                <input type="hidden" name="accountId" value="${account.id}">
                                <input type="hidden" name="carId" value="${car.id}">
                                <input type="submit" class="btn btn-primary" value="Returned" />
                            </form:form>
                        </p>

                    </div>
                </c:if>
            </div>
        </div>
    </c:forEach>

</div>

<footer>
    <div class="container text-center">
        <hr>
        <p class="mt-2">Made by Rafal Ciesla</p>
        <p>Sources of images used in this application:</p>

        <div class="sources mt-2">

            <div class="row">
                <div class="col-md-6">
                    <p>Carousel:</p>

                    <dl>
                        <dd>Dodge Demon: hagerty.com</dd>
                        <dd>Lamborghini Aventador: bozhuwallpaper.com</dd>
                        <dd>Ford Mustang: pixelstalk.net</dd>
                    </dl>
                </div>

                <div class="col-md-6">
                    <p>Offer:</p>
                    <dl>
                        <dd>Ford Mustang: autoblog.com</dd>
                    </dl>
                </div>
            </div>

        </div>
    </div>
</footer>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

</body>
</html>