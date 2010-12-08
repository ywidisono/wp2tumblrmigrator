<% include '/WEB-INF/includes/header.gtpl' %>
	<form action="worpress.groovy" method="post">
		<input name="blogurl" id="blourl"/><br/>
		<input name="bloguser" id="bloguser"/><br/>
		<input name="blogpass" id="blogpass"/><br/>
		<button type="submit" value="submit" name="submit" id="submit"></button>
	</form>
<% include '/WEB-INF/includes/footer.gtpl' %>