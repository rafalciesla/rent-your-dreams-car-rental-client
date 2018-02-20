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

         /*Carousel Styling */
        .slide1{
            background-image: url('/img/carousel/bkgrnd1.jpg');
            height: 500px;
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
        }

        .slide2 {
            background-image: url('/img/carousel/bkgrnd2.jpg');
            height: 500px;
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
        }

        .slide3{
            background-image: url('/img/carousel/bkgrnd3.jpg');
            height: 500px;
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
        }

        .slide4{
            background-image: url('/img/carousel/bkgrnd4.jpg');
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

        footer {
            margin-top: 100px;
            color: #a3a3a3;
            font-size: 12px;
        }

        .btn-group.special {
            display: flex;
        }

        .special .btn {
            flex: 1;
        }

        .btn-carmenu {
            background-color: #666666;
            color: white;
        }

        .btn-carmenu:hover {
            background-color: #8e8e8e;
            color: #1e1e1e;
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
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/account/">My account</a>
                        </security:authorize>
                        <c:if test="${pageContext.request.userPrincipal.name != null}"><a class="dropdown-item" href="<c:url value="/logout" />">Logout</a></c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="theCarousel" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#theCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#theCarousel" data-slide-to="1"></li>
        <li data-target="#theCarousel" data-slide-to="2"></li>
        <li data-target="#theCarousel" data-slide-to="3"></li>
    </ol>

    <div class="carousel-inner" role="listbox">

        <div class="carousel-item active">
            <div class="slide1"></div>
            <div class="carousel-caption">
                <h1>World's fastest cars</h1>
            </div>
        </div>

        <div class="carousel-item">
            <div class="slide2"></div>
            <div class="carousel-caption">
                <h1>Exotic cars</h1>
            </div>
        </div>

        <div class="carousel-item">
            <div class="slide3"></div>
            <div class="carousel-caption">
                <h1>American dream</h1>
            </div>
        </div>

        <div class="carousel-item">
            <div class="slide4"></div>
            <div class="carousel-caption">
                <h1>Luxury cars</h1>
            </div>
        </div>

    </div>

    <a class="carousel-control-prev" href="#theCarousel" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>

    <a class="carousel-control-next" href="#theCarousel" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>


<div class="container main-container">

    <div class="row mb-5">
        <div class="col-md-12 text-center">

            <h4 class="d-md-block d-none mb-3">Search for car by type</h4>
            <div class="btn-group special car-type-menu d-md-flex d-none">
                <a href="${pageContext.request.contextPath}/" class="btn btn-lg btn-carmenu">Most popular</a>
                <a href="${pageContext.request.contextPath}/cars/exotic" class="btn btn-lg btn-carmenu">Exotic</a>
                <a href="${pageContext.request.contextPath}/cars/sports" class="btn btn-lg btn-carmenu">Sports</a>
                <a href="${pageContext.request.contextPath}/cars/muscle" class="btn btn-lg btn-carmenu">Muscle</a>
                <a href="${pageContext.request.contextPath}/cars/luxury" class="btn btn-lg btn-carmenu">Luxury</a>
                <a href="${pageContext.request.contextPath}/cars/classic" class="btn btn-lg btn-carmenu">Classic</a>
                <a href="${pageContext.request.contextPath}/cars/other" class="btn btn-lg btn-carmenu">Other</a>
            </div>

            <div class="d-md-none">

                <div class="dropdown show mt-3">
                    <a class="btn btn-carmenu" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Search for a car by type
                    </a>

                    <div class="dropdown-menu dropdown-menu text-center" aria-labelledby="dropdownMenuLink">
                        <a href="${pageContext.request.contextPath}/" class="dropdown-item">Most popular</a>
                        <a href="${pageContext.request.contextPath}/cars/exotic" class="dropdown-item">Exotic</a>
                        <a href="${pageContext.request.contextPath}/cars/sports" class="dropdown-item">Sports</a>
                        <a href="${pageContext.request.contextPath}/cars/muscle" class="dropdown-item">Muscle</a>
                        <a href="${pageContext.request.contextPath}/cars/luxury" class="dropdown-item">Luxury</a>
                        <a href="${pageContext.request.contextPath}/cars/classic" class="dropdown-item">Classic</a>
                        <a href="${pageContext.request.contextPath}/cars/other" class="dropdown-item">Other</a>
                    </div>
                </div>

            </div>

        </div>
    </div>

    <c:choose>
        <c:when test="${type != null}">
            <h1 class="mb-5">Our ${type} Cars:</h1>

            <c:forEach var="tempCar" items="${cars}">
                <c:if test="${type eq tempCar.type}">
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

                            <p>${tempCar.description}</p>

                            <security:authorize access="hasRole('USER')">
                                <c:choose>
                                    <c:when test="${loggedAccount.rentedCarId != null}">
                                        <a class="btn btn-primary mb-2 disabled" href="${pageContext.request.contextPath}/account/rent?carId=${tempCar.id}">Request for a rent</a> <span class="error">You already have rented a car</span>
                                    </c:when>
                                    <c:when test="${tempCar.availability == false}">
                                        <a class="btn btn-primary mb-2 disabled" href="${pageContext.request.contextPath}/account/rent?carId=${tempCar.id}">Request for a rent</a> <span class="error">This car is not available at this moment</span>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn btn-primary mb-2" href="${pageContext.request.contextPath}/account/rent?carId=${tempCar.id}">Request for a rent</a>
                                    </c:otherwise>
                                </c:choose>
                            </security:authorize>

                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <h1 class="mb-5">Most Popular Cars:</h1>

            <c:forEach var="tempCar" items="${popularCars}" begin="0" end="3">
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

                        <p>${tempCar.description}</p>

                        <security:authorize access="hasRole('USER')">
                            <c:choose>
                                <c:when test="${loggedAccount.rentedCarId != null}">
                                    <a class="btn btn-primary mb-2 disabled" href="${pageContext.request.contextPath}/account/rent?carId=${tempCar.id}">Request for a rent</a> <span class="error">You already have rented a car</span>
                                </c:when>
                                <c:when test="${tempCar.availability == false}">
                                    <a class="btn btn-primary mb-2 disabled" href="${pageContext.request.contextPath}/account/rent?carId=${tempCar.id}">Request for a rent</a> <span class="error">This car is not available at this moment</span>
                                </c:when>
                                <c:otherwise>
                                    <a class="btn btn-primary mb-2" href="${pageContext.request.contextPath}/account/rent?carId=${tempCar.id}">Request for a rent</a>
                                </c:otherwise>
                            </c:choose>
                        </security:authorize>

                    </div>
                </div>
            </c:forEach>

        </c:otherwise>
    </c:choose>


</div>


</div>


<footer>
    <div class="container text-center">
        <hr>
        Made by Rafal Ciesla.<br><br>
        Sources of images used in this application:<br><br>
        Carousel:<br><br>
        Dodge Demon: https://www.hagerty.com/articles-videos/articles/2017/04/12/dodge-demon-debut<br>
        Lamborghini Aventador: http://bozhuwallpaper.com/lamborghini-aventador-backgrounds/<br>
        Ford Mustang: https://www.pixelstalk.net/mustang-wallpaper-hd/red-ford-mustang-wallpaper-front/ <br>
    </div>
</footer>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

</body>
</html>