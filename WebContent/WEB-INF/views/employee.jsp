<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Page</title>
    </head>
    <body align="center">
        <h3>List of Employees</h3>
        <table border="1" align="center">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Designation</th>
                <th>Salary</th>
            </tr>
            <c:forEach items="${employee}" var="emp">
                <tr>
                    <td>
                        <c:out value="${emp.empId}" />
                    </td>
                    <td>
                        <c:out value="${emp.name}" />
                    </td>
                    <td>
                        <c:out value="${emp.designation}" />
                    </td>
                    <td>
                        <c:out value="${emp.salary}" />
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>