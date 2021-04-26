<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<!-- CSS FILES -->
		<link rel="stylesheet" href="css/style.css">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		
		<!-- JS FILES -->
		<script type="text/javascript" src="js/script.js"></script>
		
		<title>UNIX TS Converter</title>
	</head>
	
	<body>
		<header>
			<div class="center">
				<h2>UNIX TimeStamp Converter</h2>
				
				<p>A tool to convert UNIX TimeStamp into Date Format</p>
			</div>
		</header>
		
		<div><hr></div>
		
		<main>
			
			<div class="col-sm-12 text-right">
					<button class="btn btn-warning" id="reset" onclick="reset()">RESET</button>
			</div>
			
			<div class="row col-sm-6">
				<div class="col-sm-4 text-right">
					<label class='inner-block' for='timeStamp'>UNIX TimeStamp : </label>
				</div>
				
				<div class="col-sm-6 text-center">
					<input 	class="form-control"
							type='number' 
							id='timeStamp' 
							name='timeStamp' 
							min='0' 
							max='100000000000000' 
							required>
				</div>
			</div>
				
			<br/>
			
			<div class="row col-sm-6">
				<div class="col-sm-4 text-right">
					<label for='timeZone'>TimeZone : </label>
				</div>
				
				<div class="col-sm-6 text-center">
					<select class="form-control" id="timeZone" name="timeZone"></select>				
				</div>
			</div>
			
			<br/>
			
			<div class="row col-sm-6">
				<div class="col-sm-4 text-right">
					<label for='dateFormat'>Date Format : </label>
				</div>
				
				<div class="col-sm-6 text-center">
					<select  class="form-control" id="dateFormat" name="dateFormat">
					<option value="yyyy'-'MM'-'dd HH':'mm':'SSS">YYYY-MM-DD HH-MM-SSS</option>
					<option value="yyyy'-'MM'-'dd HH':'mm">YYYY-MM-DD HH-MM</option>
					<option value="yyyy'-'MM'-'dd">YYYY-MM-DD</option>
				</select>
				</div>
			</div>
			
			<br/>
			
			<div id="getDateDiv" class="center">
				<button class="btn btn-info" id="getDate" onclick="getDate()">GET DATE</button>
			</div>
			
			<br/>
			
			<div class="container display-none" id="resultDiv">
				<p class="well" id="result"></p>
			</div>
		</main>
		
		<footer>
		
		</footer>
	</body>
</html>