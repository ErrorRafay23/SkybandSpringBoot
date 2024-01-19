<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@include file="sale_request.jsp" %>

<tr>
	<a href="/sale">reset</a>
    </form><br/>
         
	      <br><c:out    value="===========Responce==========="/>        </br>
          <br> <c:out   value="STATUS      :${sale.overallResult}"/> </br>
          <br> <c:out   value="RRN         :${sale.rrn}"/>           </br>
	</td>
</tr>