<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="main.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Tour Details</title>
</head>
<body bgcolor="silver">
	<form method="post" action="webapi/toursubmit">

		<table border="0" width="50%" cellpadding="3">
			<thead>
				<tr>
					<th colspan="2">Tour Details - Hello <b>${loginedUser.userName}</b></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Report Name*</td>
					<td><input type="text" id="reportname" name="reportname" value="" /></td>
				</tr>
				<tr>
					<td>Purpose of Tour*</td>
					<td><textarea id="tourPurpose" name="tourPurpose"
							style="overflow: auto; resize: none" rows="2" cols="20"></textarea></td>
				</tr>
				<tr>
					<td>Travel Start Date*</td>
					<td><input type="date" id="startDate" name="startDate"/></td>
				</tr>
				<tr>
					<td>Travel End Date*</td>
					<td><input type="date"  id="endDate" name="endDate"/></td>
				</tr>
				<tr>
					<td>Mode of Travel*</td>
					<td><select id="travelMode" name="travelMode">
							<option value="flight">Flight</option>
							<option value="road">Road</option>
					</select></td>
				</tr>
				<tr>
					<td>Ticket Cost*</td>
					<td><input type="text" id="cost" name="cost" value="" /></td>
				</tr>
				<tr>
					<td>Cost of Airport Cab at home city</td>
					<td><input type="text" name="cach" value="" /></td>
				</tr>
				<tr>
					<td>Cost of Airport Cab at destination city</td>
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
					<td><input type="submit" value="Submit for Approval"
						style="width: 350px" onclick='return validation();'/></td>
					<td><input type="reset" value="Reset" style="width: 350px" /></td>
				</tr>

			</tbody>
		</table>

	</form>
</body>
</html>


