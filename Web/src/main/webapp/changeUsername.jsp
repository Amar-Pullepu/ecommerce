<%@include file="Common/Header.jsp"%>

<main>
	<div class="container">
		<section class="mb-4">
			<div class="row wow fadeIn">
				<div class='col-6 offset-3'>

					<form action='/account/change-username' method='post'
						onSubmit="return validate()" class="form-horizontal">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> <span><h3>Change Username</h3></span>
						<div class="form-group">
							<label class="col-sm-6 control-label" align="left">
								Current Username: </label>
							<div class="col-sm-12">
								<input class="form-control" id="currusername" type="text"
									name="currusername"
									value=<%out.print(request.getUserPrincipal().getName());%>
									readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-6 control-label" align="left"> New
								Username: </label>
							<div class="col-sm-12">
								<input class="form-control" id="username" type="text"
									name="username" placeholder="New Username" required>
							</div>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">Change
								Username</button>
						</div>
					</form>
				</div>
			</div>
		</section>
	</div>
</main>

<%@include file="Common/Scripts.jsp"%>
<script type="text/javascript" src="/Static/JavaScripts/userCheck.js"></script>
<script type="text/javascript" src="/Static/JavaScripts/usernameValidation.js"></script>
<%@include file="Common/Footer.jsp"%>