<% include '/WEB-INF/includes/header.gtpl' %>
	<form id="migrator" name="migrator" action="wizard.groovy?a=tumblr" method="post">
			<fieldset>	
			 <legend>Tumblr Detail</legend>
            <p>
              <label for="tburl">Tumblr URL</label><br>
              <input type="text" class="text" name="tburl" id="tburl" value='${if(session != null && session.getAttribute("tburl") !=null) session.getAttribute("tburl"); else ""}' required="required" title="example xxxx.tumblr.com">
            </p>
            <p>
              <label for="tbuser">Tumblr User</label><br>
              <input type="text" class="text" name="tbuser" id="tbuser" value='${if(session != null && session.getAttribute("tbuser") !=null) session.getAttribute("tbuser"); else ""}' required="required" title="tumblr username/email">
            </p>
            <p>
              <label for="tbpass">Tumblr Password</label><br>
              <input type="password" class="text" name="tbpass" id="tbpass" value='${if(session != null && session.getAttribute("tbpass") !=null) session.getAttribute("tbpass"); else ""}' required="required" title="tumblr password">
            </p>
            <p>
              <input type="checkbox" id="usedisquss" name="usedisquss" value='true' title="using tumblr for comment"> Use disquss
            </p>
			</fieldset>
              <input type="submit" value="Back" name="backbutton" id="backbutton">
              <input type="submit" value="Next" name="nextbutton" id="nextbutton">
	</form>
	<script src="js/tooltips.js" type="text/javascript"></script>
<% include '/WEB-INF/includes/footer.gtpl' %>

