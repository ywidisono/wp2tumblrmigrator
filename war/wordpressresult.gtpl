<% include '/WEB-INF/includes/header.gtpl' %>
	<%
		def results = request.getAttribute("results")
		results.each{
		println it
	%><br/>
	<%}%>
testnich
<% include '/WEB-INF/includes/footer.gtpl' %>