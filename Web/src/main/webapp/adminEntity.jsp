<%@page import="com.ecommerce.models.Items.Order"%>
<%@page import="com.ecommerce.models.Items.OrderItem"%>
<%@page import="com.ecommerce.models.Items.Item"%>
<%@page import="com.ecommerce.models.Items.SubCategory"%>
<%@page import="com.ecommerce.models.Items.Category"%>
<%@page import="java.util.Iterator"%>
<%@include file="Common/adminHeader.jsp"%>
<div class="breadcrumbs">
	<a href="/admin/">Home</a>
	&rsaquo; ${title}
</div>

<%
	Iterator oItr = (Iterator) request.getAttribute("itr");
%>





<!-- Content -->
<div id="content" class="flex">

	<h1>Select category to change</h1>

	<div id="content-main">

		<ul class="object-tools">





			<li><a href="/admin/${title}/add/" class="addlink"> Add
					${title} </a></li>




		</ul>


		<div class="module" id="changelist">









			<form id="changelist-form" method="post" novalidate>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />




				<div class="actions">

					<button type="submit" class="button" name="delete_selected">Delete
						Selected</button>

					<span class="action-counter" data-actions-icnt="2"></span>




				</div>




				<div class="results">
					<table id="result_list">
						<thead>
							<tr>
								<th scope="col" class="action-checkbox-column">

									<div class="text">
										<span><input type="checkbox" id="action-toggle"></span>
									</div>
									<div class="clear"></div>
								</th>
								<th scope="col" class="column-__str__">

									<div class="text">
										<span>${title}</span>
									</div>
									<div class="clear"></div>
								</th>
							</tr>
						</thead>
						<tbody>

							<%
								int oLen = 1;
							for (; oItr.hasNext(); oLen++) {
								Object obj = oItr.next();
							%>
							<tr class="row" <%out.print(oLen); %>>
								<%
									if (obj instanceof Category){
								%>
								<td class="action-checkbox"><input type="checkbox"
									name="_selected_action"
									value="<%out.print(((Category) obj).getId());%>"
									class="action-select"></td>
								<th class="field-__str__"><a
									href="/admin/${title}/<% out.print(((Category)obj).getId()); %>/change/">
										<%
											out.print(((Category) obj).toString());
										%>
								</a></th>
								<%
									}else if (obj instanceof Item){
								%>
								
								<td class="action-checkbox"><input type="checkbox"
									name="_selected_action"
									value="<%out.print(((Item) obj).getId());%>"
									class="action-select"></td>
								<th class="field-__str__"><a
									href="/admin/${title}/<% out.print(((Item)obj).getId()); %>/change/">
										<%
											out.print(((Item) obj).toString());
										%>
								</a></th>
								<%
									}else if (obj instanceof SubCategory){
								%>
								<td class="action-checkbox"><input type="checkbox"
									name="_selected_action"
									value="<%out.print(((SubCategory) obj).getId());%>"
									class="action-select"></td>
								<th class="field-__str__"><a
									href="/admin/${title}/<% out.print(((SubCategory)obj).getId()); %>/change/">
										<%
											out.print(((SubCategory) obj).toString());
										%>
								</a></th>
								<%
									}else if (obj instanceof OrderItem){
								%>
								<td class="action-checkbox"><input type="checkbox"
									name="_selected_action"
									value="<%out.print(((OrderItem) obj).getId());%>"
									class="action-select"></td>
								<th class="field-__str__"><a
									href="/admin/${title}/<% out.print(((OrderItem)obj).getId()); %>/change/">
										<%
											out.print(((OrderItem) obj).toString());
										%>
								</a></th>
								<%
									}else if (obj instanceof Order){
								%>
								<td class="action-checkbox"><input type="checkbox"
									name="_selected_action"
									value="<%out.print(((Order) obj).getId());%>"
									class="action-select"></td>
								<th class="field-__str__"><a
									href="/admin/${title}/<% out.print(((Order)obj).getId()); %>/change/">
										<%
											out.print(((Order) obj).toString());
										%>
								</a></th>
								<%
									}
								%>
								
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
				</div>






				<p class="paginator">
					<%
						out.print(oLen);
					%>
					&nbsp; ${title}
				</p>

			</form>
		</div>
	</div>


	<br class="clear">
</div>


<%@include file="Common/adminFooter.jsp"%>