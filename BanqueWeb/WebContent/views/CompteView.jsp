<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id ="CompteView">
 <form action="/BanqueWeb/CompteController" method="post">
      <table>

  <tbody>
     <tr>
      <td>Proprietaire</td>
      <td><input type="Text" name="proprietaire" value="${proprietaire}" > </td>
       </tr>
       <tr>
         <td>Code Compte</td>
     
         <td><select name="categorie">

						<option value="C">Courant</option>
						<option value="E">Epargne</option>
					
				</select>
				</td>
				    <td>${errProprietaire}</td>
       
    </tr>
  <tr>  <td><input type="submit" name="action" value="Ajouter"></td></tr>
 
   
  </tbody>
</table>
<table style="border: 1px solid black;">
  <thead>
     <tr>
   
      <td>Code Compte</td>
      <td><input type="number" name="code" value="${compte.code}" > </td>
         <td>${errCode }</td>
         <td><input type="submit" name="action" value="Consulter"></td>
         <td><input type="submit" name="action" value="Tous les Comptes"></td>
    </tr>
    <tr>
      <th>Code Compte</th>
      <th>Proprietaire</th>
       <th>categorie</th>
       <th>solde</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${Comptes}" var="c">
  
    <tr>
    <td>${c.getNumero()}</td> 
    <td>${c.getProprietaire()}</td> 
    <td>${c.getCategorie()}</td> 
    <td>${c.getSolde()}</td> 
    </tr>
    </c:forEach>
</table>
      
</form>
</div>
 
</body>
</html>