<% include '/WEB-INF/includes/header.gtpl' %>
	<%
		//clear session if new request not coming from back
		if((params.backbutton==null) && (session != null)){
			session.setAttribute("wpurl",null);session.setAttribute("wpuser",null);session.setAttribute("wppass",null)
			session.setAttribute("tburl",null);session.setAttribute("tbuser",null);session.setAttribute("tbpass",null)
			session.setAttribute("usedisquss",null);session.setAttribute("dqkey",null);session.setAttribute("dqname",null)
		}
	%>
	<form id="migrator" name="migrator" action="wizard.groovy?a=wp" method="post">
			 <fieldset>
			 <legend>Wordpress Detail</legend>
			<p>
              <label for="wpurl">Wordpress URL</label><br>
              <input type="text" class="text" name="wpurl" id="wpurl" value='${if(session != null && session.getAttribute("wpurl") !=null) session.getAttribute("wpurl"); else ""}' required="required" title="example xxxx.wordpress.com">
            </p>
            <p>
              <label for="wpuser">Wordpress User</label><br>
              <input type="text" class="text" name="wpuser" id="wpuser" value='${if(session != null && session.getAttribute("wpuser") !=null) session.getAttribute("wpuser"); else ""}' required="required" title="wordpress username">
            </p>
            <p>
              <label for="wppass">Wordpress Password</label><br>
              <input type="password" class="text" name="wppass" id="wppass" value='${if(session != null && session.getAttribute("wppass") !=null) session.getAttribute("wppass"); else ""}' required="required" title="wordpress password">
            </p>
         	 </fieldset>
            <p>
              <input type="submit" value="next" name="nextbutton" id="nextbutton">
            </p>
	</form>
	<script src="js/tooltips.js" type="text/javascript"></script>
<% include '/WEB-INF/includes/footer.gtpl' %>

