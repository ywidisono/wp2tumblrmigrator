<% include '/WEB-INF/includes/header.gtpl' %>
<di id="cont1">
	<h2>Worpress Detail</h2>
	<p>
		URL 		: ${if(session != null && session.getAttribute("wpurl") !=null) session.getAttribute("wpurl"); else ""}<br/>
		USER		: ${if(session != null && session.getAttribute("wpurl") !=null) session.getAttribute("wpuser"); else ""}
	</p>
</div><div id="conte2">
	<h2>tumblr Detail</h2>
	<p>
		URL 		: ${if(session != null && session.getAttribute("wpurl") !=null) session.getAttribute("tburl"); else ""}<br/>
		USER		: ${if(session != null && session.getAttribute("wpurl") !=null) session.getAttribute("tbuser"); else ""}
	</p>
	<h2>disquss Detail</h2>
	<p>
		shortname	: ${if(session != null && session.getAttribute("dqname") !=null) session.getAttribute("dqname"); else ""}
	</p>
<div>
<form id="migrator" name="migrator" action="migrate.groovy" method="post">
	 <p>
              <input type="submit" value="Migrate" name="migratebutton" id="migratebutton">
              <input type="submit" value="Cancel" name="cancelbutton" id="cancelbutton">
            </p>
</form>
</div>
<% include '/WEB-INF/includes/footer.gtpl' %>