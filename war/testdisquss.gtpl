<% include '/WEB-INF/includes/header.gtpl' %>
	<form action="disquss.groovy" method="post">
		<input name="disqusskey" id="blourl"/><br/>
		<input name="disqussshortname" id="bloguser"/><br/>
		<button type="submit" value="submit" name="submit" id="submit"></button>
	</form>
<% include '/WEB-INF/includes/footer.gtpl' %>