<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
/* $(document).ready(function(){
	$("#txnStatus").hide();
	$("#txnRRN").hide()
    $("#submit").click(function(){    	
        $.post("/saleRequest",
        {
          amount: $('#amount').val(),
          mrn: $('#mrn').val(),
		  mobileno: $('#mobileno').val(),
        },
        function(data,status){
            //alert("Data: " + data + "\nStatus: " + status);
            var resultString = String(data).split(":")
            $("#txnStatus").show();
			$("#txnRRN").show();
			$("#amount").val("");
			$("#mrn").val("");
			$("#mobileno").val("");
            $('#txnStatus').append(resultString[0]);
            $('#txnRRN').append(resultString[1]);
        });
    });
}); */




</script>
</head>
<body>
	<table>
	
      <form action="saleRequest" method="post" commandName="note" id="note" >
		<tr>
			<td>Amount</td><td>:</td><td><input type="text" id="amount" name="amount"></td>
		</tr>
		<tr>
			<td>MRN</td><td>:</td><td><input type="text"  id="merchantReferenceNumber" name="merchantReferenceNumber"></td>
		</tr>
		<tr>
			<td>Mobile Number</td><td>:</td><td><input type="text"  id="customerMobile" name="customerMobile"></td>
		</tr>
		<tr><td><br/></td></tr>
		<tr>
		<td>
			<button id="submit" value="submit">Submit</button>
	 </form><br/>
	 
	 <a href="/index">Back</a>
		</td>
		</tr>
		
		
	</table>


</body>
</html>
