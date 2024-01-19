<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>

</head>
<body>
<h2>Till Demo</h2>

 <form action="sale"  method="post">
    	<input type="submit" value="Sale" name="sale" />
    </form><br/>
    
     <form action="activateDevice" method="post">
    	<input type="submit" value="ActivateDevice" name="activateDevice" />
    </form><br/>
    
     <form action="deactivateDevice" method="post">
    	<input type="submit" value="DeactivateDevice" name="deactivateDevice" />
    </form><br/>
    
     <form action="#" >
    <input type="text" name="mobileno" id="result" readonly style="border:none">
    </form><br/>
</body>
</html>