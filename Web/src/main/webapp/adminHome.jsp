<%@include file="Common/adminHeader.jsp"%>
<div class="breadcrumbs">
	<a href="/admin/">Home</a> &rsaquo; Home

</div>







<!-- Content -->
<div id="content" class="colMS">

	<h1>Entities administration</h1>

	<div id="content-main">



		<div class="app-home module">
			<table class="table">
				<caption>
					<a href="/admin/" class="section"
						title="Models in the Home application">Home</a>
				</caption>
				<% Object[] entities = (Object[])request.getAttribute("entities");%>
				<% for(int itr = 0; itr < entities.length; itr++) {%>
				<tr class="model">

					<th scope="row" style="width: 60%"><a href="/admin/<% out.print(entities[itr]); %>"><% out.print(entities[itr]); %></a></th>



					<td	style="width: 5%"><a href="/admin/<% out.print(entities[itr]); %>/add/" class="addlink">Add</a></td>




					<td style="width: 5%"><a href="/admin/<% out.print(entities[itr]); %>/" class="changelink">Change</a></td>


				</tr>
				<% } %>
			</table>
		</div>


	</div>


	<br class="clear">
</div>
<%@include file="Common/adminFooter.jsp"%>