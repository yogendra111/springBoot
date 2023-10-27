<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>IT Helpdesk</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
<div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
<div class="card mb-3" style="max-width: 720px;">
  <div class="row g-0">
    <div class="col-md-4">
      <img src="https://www.electric.ai/wp-content/uploads/SEO-Infographic-What-Is-IT-Support-859x1024.jpg" class="img-fluid rounded-start" alt="...">
    </div>
    <div class="col-md-8">
      <div class="card-body">
        <h5 class="card-title mb-3">IT HelpDesk</h5>
	<form method="post" action="submit">
		<div class="mb-3">
			<input type="text" class="form-control" id="empId" placeholder="Employee ID" aria-describedby="emailHelp">
		</div>
		<div class="mb-3">
			<select class="form-select" aria-label="select example">
  				<option selected>Select Category</option>
  				<option value="1">Hardware Issue</option>
  				<option value="2">Software Issue</option>
  				<option value="3">Access Request</option>
  				<option value="4">Other</option>
			</select>
		</div>
		<div class="mb-3">
			<input type="text" class="form-control" id="comp" placeholder="Complain" aria-describedby="complain">
		</div>
		<div class="mb-3">
		  <textarea class="form-control" id="textArea" placeholder="Detailed Complain" rows="3"></textarea>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
        <p class="card-text"><small class="text-muted">We will update you soon.</small></p>
      </div>
    </div>
  </div>
  </div>
</div>
</body>
</html>