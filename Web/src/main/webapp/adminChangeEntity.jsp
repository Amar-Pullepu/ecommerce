<%@page import="com.ecommerce.models.Account.User"%>
<%@page import="com.ecommerce.models.Items.Order"%>
<%@page import="com.ecommerce.models.Items.OrderItem"%>
<%@page import="com.ecommerce.models.Items.Item"%>
<%@page import="com.ecommerce.models.Items.SubCategory"%>
<%@page import="com.ecommerce.models.Items.Category"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@include file="Common/adminHeader.jsp"%>

<div class="breadcrumbs">
	<a href="/admin/">Home</a>&rsaquo; <a
		href="/admin/${entity}">${entity}</a> &rsaquo; Change
	${entity}
</div>
<!-- Content -->
<%
	HashMap<String, String> dataTypes = (HashMap<String, String>) request.getAttribute("dataTypes");
	HashMap<String, String> dataValues = (HashMap<String, String>) request.getAttribute("dataValues");
%>
<div id="content" class="colM">

	<h1>Add ${entity}</h1>
	<div id="content-main">
		<form enctype="multipart/form-data" action="/admin/${entity}/${id}/change"
			method="post" id="category_form" novalidate>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<fieldset class="module aligned ">
				<%
					for (String field : dataTypes.keySet()) {
				%>
				<div class="form-row">
					<div>
						<label class="required" for="id_<%out.print(field);%>"> <%
 	out.print(field);
 %>:
						</label>
						<%
							if (field.equals("ImageUrl")) {
						%>
						<p class="file-upload">
							Currently: <a href="<%out.print(dataValues.get(field));%>"><% out.print(dataValues.get(field)); %></a><br>
							Change: <input type="<%out.print(dataTypes.get(field));%>"
								name="<%out.print(field);%>" accept="image/*" required
								id="id_<%out.print(field);%>">
						</p>
						<%
							} else if (dataTypes.get(field).equals("select")) {
						%>
						<div class="related-widget-wrapper">
							<select name="<%out.print(field);%>" required
								id="id_<%out.print(field);%>">
								<option value="" selected>---------</option>
								<%
									Iterator oItr = (Iterator) request.getAttribute(field);
								%>
								<%
									while (oItr.hasNext()) {
									Object obj = oItr.next();
								%>
								<%
									if (obj instanceof Category) {
								%>
								<option value="<%out.print(((Category) obj).getId());%>" <% out.print(((Category) obj).getId().toString().equals(dataValues.get(field))?"selected":""); %>>
									<%
										out.print(((Category) obj).toString());
									%>
								</option>
								<%
									} else if (obj instanceof SubCategory) {
								%>
								<option value="<%out.print(((SubCategory) obj).getId());%>" <% out.print(((SubCategory) obj).getId().toString().equals(dataValues.get(field))?"selected":""); %>>
									<%
										out.print(((SubCategory) obj).toString());
									%>
								</option>
								<%
									} else if (obj instanceof Item) {
								%>
								<option value="<%out.print(((Item) obj).getId());%>" <% out.print(((Item) obj).getId().toString().equals(dataValues.get(field))?"selected":""); %>>
									<%
										out.print(((Item) obj).toString());
									%>
								</option>
								<%
									} else if (obj instanceof Order) {
								%>
								<option value="<%out.print(((Order) obj).getId());%>" <% out.print(((Order) obj).getId().toString().equals(dataValues.get(field))?"selected":""); %>>
									<%
										out.print(((Order) obj).toString());
									%>
								</option>
								<%
									} else if (obj instanceof OrderItem) {
								%>
								<option value="<%out.print(((OrderItem) obj).getId());%>" <% out.print(((OrderItem) obj).getId().toString().equals(dataValues.get(field))?"selected":""); %>>
									<%
										out.print(((OrderItem) obj).toString());
									%>
								</option>
								<%
									} else if (obj instanceof User) {
								%>
								<option value="<%out.print(((User) obj).getId());%>" <% out.print(((User) obj).getId().toString().equals(dataValues.get(field))?"selected":""); %>>
									<%
										out.print(((User) obj).toString());
									%>
								</option>
								<%
									}
								}
								%>

							</select>
							<%
								if (!field.equals("user")) {
							%>
							<a
								href="/admin/<%out.print(field.substring(0, 1).toUpperCase() + field.substring(1));%>/add"><img
								src="/Static/admin/img/icon-addlink.svg" alt="Add"></a>
							<%
								}
							%>
						</div>
						<%
							} else {
						%>
						<input type="<%out.print(dataTypes.get(field));%>"
							name="<%out.print(field);%>"
							<%if(dataTypes.get(field).equals("checkbox")) {%>
							<%out.print("checked"); %>
							<%}else {%>
							value="<%out.print(dataValues.get(field)); %>"
							<%} %>
							<%if (dataTypes.get(field).equals("number"))%> step="any"
							id="id_<%out.print(field);%>">
						<%
							}
						%>
					</div>
				</div>
				<%
					}
				%>
			</fieldset>
			<div class="submit-row">
			<p class="deletelink-box"><input type="submit" onclick='this.form.action="/admin/${entity}/${id}/delete";' class="deletelink" value = "Delete" /></p>
				<input type="submit" class="default">
			</div>
			<script type="text/javascript" src="/Static/admin/js/change_form.js"
				data-model-name="category">
				
			</script>
			<script type="text/javascript"
				src="/Static/admin/js/prepopulate_init.js"
				data-prepopulated-fields="[]">
				
			</script>
	</div>
	</form>
</div>

<%@include file="Common/adminFooter.jsp"%>