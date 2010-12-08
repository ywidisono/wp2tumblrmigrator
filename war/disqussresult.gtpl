<% include '/WEB-INF/includes/header.gtpl' %>
	<%
		def results = request.getAttribute("results")
		results.each{
		println it
	%><br/>
	<%}%>
testnich disquss
<% include '/WEB-INF/includes/footer.gtpl' %>