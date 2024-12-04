<!doctype html>
<%@page import="com.ty.entity.Student"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap demo</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">

		<h1>Update Details</h1>
		
		<form action="update" method="post">
			<%
			Student student=(Student)request.getAttribute("stu");
			%>
			<div class="mb-3">
				
				<input type="hidden" name="id" class="form-control"
					id="exampleFormControlInput4" value="<%=student.getId() %>"placeholder="name@123" required>
			</div>
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">Name</label>
				<input type="text" name="name" class="form-control"
					id="exampleFormControlInput1" placeholder="name" value="<%=student.getName() %>"required>
			</div>

			<div class="mb-3">
				<label for="exampleFormControlInput2" class="form-label">Email
					address</label> <input type="email" name="email" class="form-control"
					id="exampleFormControlInput2" placeholder="name@example.com" value="<%=student.getEmail() %>"
					required>
			</div>

			<div class="mb-3">
				<label for="exampleFormControlInput3" class="form-label">Phone</label>
				<input type="number" name="phone" class="form-control"
					id="exampleFormControlInput3" placeholder="1234567890" value="<%=student.getPhone() %>" required>
			</div>

			

			<button type="submit" class="btn btn-primary">Update</button>

		</form>

		<a href="home">Home</a>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>