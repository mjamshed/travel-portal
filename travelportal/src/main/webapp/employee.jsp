<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Tour Details</title>
</head>
<body bgcolor="silver">
	<form method="post" action="webapi/approval">

		<table border="0" width="50%" cellpadding="3">
			<thead>
				<tr>
					<th colspan="2">Tour Details</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Purpose of Tour</td>
					<td><input type="text" name="tourPurpose" value="" /></td>
				</tr>
				<tr>
					<td>Travel Start Date</td>
					<td><input type="date" name="startDate" /></td>
				</tr>
				<tr>
					<td>Travel End Date</td>
					<td><input type="date" name="endDate" /></td>
				</tr>
				<tr>
					<td>Mode of Travel</td>
					<td><select name="travelMode">
							<option value="flight">Flight</option>
							<option value="road">Road</option>
					</select></td>
				</tr>
				<tr>
					<td>Ticket Cost</td>
					<td><input type="text" name="cost" value="" /></td>
				</tr>
				<tr>
					<td>Cost of Airport Cab at home city</td>
					<td><input type="text" name="cach" value="" /></td>
				</tr>
				<tr>
					<td>Cost of Airport Cab at destication city</td>
					<td><input type="text" name="cadh" value="" /></td>
				</tr>
				<tr>
					<td>Hotel Cost</td>
					<td><input type="text" name="hcost" value="" /></td>
				</tr>
				<tr>
					<td>Local Conveyance at tour location</td>
					<td><input type="text" name="localc" value="" /></td>
				</tr>
				

				<tr>
					<td><input type="submit" value="Submit for Approval" /></td>
					<td><input type="reset" value="Reset" /></td>
				</tr>
				
			</tbody>
		</table>

	</form>
</body>
</html>


