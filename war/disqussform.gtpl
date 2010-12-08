<% include '/WEB-INF/includes/header.gtpl' %>
	<form id="migrator" name="migrator" action="wizard.groovy?a=dq" method="post">
			<fieldset>	
			 <legend>Disquss Detail</legend>
            <p>
              <label for="tburl">Disquss User Key</label><br>
              <input type="text" class="text" name="dqkey" id="dqkey" value='${if(session != null && session.getAttribute("dqkey") !=null) session.getAttribute("dqkey"); else ""}' required="required" title="http://disqus.com/api/get_my_key/ (login first)">
            </p>
            <p>
              <label for="tbuser">Disquss Shortname</label><br>
              <input type="text" class="text" name="dqname" id="dqname" value='${if(session != null && session.getAttribute("dqname") !=null) session.getAttribute("dqname"); else ""}' required="required" title="Disquss Shortname">
            </p>
			</fieldset>
              <input type="submit" value="Back" name="backbutton" id="backbutton">
              <input type="submit" value="Next" name="nextbutton" id="nextbutton">
	</form>
	<script src="js/tooltips.js" type="text/javascript"></script>
<% include '/WEB-INF/includes/footer.gtpl' %>

