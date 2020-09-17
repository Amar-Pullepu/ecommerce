<%@include file="Common/Header.jsp"%>

<main>
	<div class="container">
		<section class="mb-4">
			<div class="row wow fadeIn">
				<div class='col-6 offset-3'>

					<form action='/account/change-password' method='post' 
						onSubmit="return validate()" class="form-horizontal">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> 
						<div><h3>Change Password</h3></div>
						<div class="form-group">
							<label class="col-sm-6 control-label" style="aligin:left">
								Current Password: </label>
							<div class="col-sm-12">
								<input class="form-control" id="oldPasswd" type="password" name="oldPasswd"
									placeholder="Current Password" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-6 control-label" style="aligin:left">
								New Password: </label>
							<div class="col-sm-12">
								<input class="form-control" id="password" type="password"
									name="password" placeholder="New Password" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-6	 control-label" style="aligin:left">
								Re-enter New Password: </label>
							<div class="col-sm-12">
								<input class="form-control" id="repassword" type="password"
									name="repassword" placeholder=" Reenter New Password" required>
							</div>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">Change Password</button>
						</div>
					</form>
				</div>
			</div>
		</section>
	</div>
</main>

<%@include file="Common/Scripts.jsp"%>
<script type="text/javascript" src="/Static/JavaScripts/passwdCheck.js"></script>
<script type="text/javascript" src="/Static/JavaScripts/passwdValidation.js"></script>
<%@include file="Common/Footer.jsp"%>