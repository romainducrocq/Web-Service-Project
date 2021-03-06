<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<title>Rental Project</title>

	<!-- https://getbootstrap.com/docs/4.0/getting-started/introduction/ -->
	<link href="https://fonts.googleapis.com/css2?family=Ubuntu:wght@300&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.0/normalize.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/skeleton/2.0.4/skeleton.min.css"/>

	<style>
		body {
			background-image: url("https://images.unsplash.com/photo-1568605117036-5fe5e7bab0b7?ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80");
	  		background-attachment: fixed;
	  		background-position: 0px 0px;
	  		background-repeat: no-repeat; 
	  		background-size: cover; 
	  		font-family: "Ubuntu", sans-serif;
	  		padding-bottom: 40px;
		}
	
		.navtag-big { 
			font-weight: bold;
			font-size: 32px;
  			color: #171C8F;
		}
		
		.navtag-medium { 
			font-weight: bold;
			font-size: 24px;
		}
		
		#main {
			margin: 50px auto auto auto;
		}
		
		.home {
			text-align: center;
		}
		
		.container {
		  padding: 40px;
		  border: 5px solid;
		  background-color: #f2f2f2;
		}
		
		.container-title{
		 	font-weight: bold;
			font-size: 40px;
		}
		
		.container-body{
			font-size: 24px;
		}
	
	</style>

</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #f2f2f2;">
      <a class="navbar-brand nav-link disabled" href="#">
        <img
          src="https://www.univ-gustave-eiffel.fr/fileadmin/templates/UGE/assets/img/logo/logoRS-UGE.png"
          width="50"
          height="50"
          class="d-inline-block align-top"
          alt=""
        />
       </a>
      <div class="navtag-big">RENTAL PROJECT&nbsp;&nbsp;</div>
      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">

        <div class="navbar-text ml-auto">
          <span class="navtag-medium">@AT, NG, RD</span>
        </div>
      </div>
    </nav>
	
	<div id="main" class="home">
		<div class='container'>
			<div class='row'>
				<div class='twelve columns'>
					<div class="container-title">Return a vehicle:</div>
					
					<br>
					<form action="<%= request.getContextPath() %>/return" method="post">
  						
	  					<span class="container-body">
	  						<label for="vehicleid">Vehicle identifier:</label>
							<input type="number" id="vehicleid" name="vehicleid" min="0">
							<label for="note">Score (0 to 5):</label>
							<input type="number" id="note" name="note" min="0" max="5">
							<label for="note">Condition of return:</label>
							<textarea name="condition" id="condition" style="width:100%; height:100px;"></textarea>
							<br>
							<input type="submit" value="RETURN">
						</span>
					
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>