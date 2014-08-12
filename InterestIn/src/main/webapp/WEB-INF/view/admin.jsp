<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=Utf8">
<link rel="stylesheet"
	href="<c:url value="dist/admin/css/bootstrap.min.css" />"
	type="text/css" media="screen, projection" />
<link rel="stylesheet" href="<c:url value="dist/admin/css/admin.css" />"
	type="text/css" media="screen, projection" />
<title>Admin page - InterestIn</title>
</head>
<body>
	<div id="page">
		<div id="header">
			<h1>Admin page</h1>
		</div>
		<div id="container">
			<div id="countriesBlock">
				<h6>Countries</h6>
				<form action="countries" method="POST" enctype="multipart/form-data">
					<input type="file" accept="text/xml" name="file"> <input
						type="submit" value="Upload">
				</form>
			</div>
			<div id="hobbiesBlock">
				<h6>Hobbies</h6>
				<form action="hobbies" method="POST" enctype="multipart/form-data">
					<input type="file" accept="text/xml" name="file"> <input
						type="submit" value="Upload">
				</form>
			</div>

		</div>
	</div>
</body>
</html>