<%@include file="Common/Header.jsp"%>
<main>
	<div class="container">
		<section class="mb-4">
			<div class="row wow fadeIn">
				<div class='col-6 offset-3'>
					<h1>Sign Up</h1>
					<p>
						Already have an account? Then please <a href='/account/login'>sign
							in</a>
					</p>

					<form action='/account/register' method='post'
						onSubmit="return beforeSubmit()" class="form-horizontal"
						enctype="multipart/form-data">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div class="form-group">
							<label class="col-sm-12 control-label requiredField"><strong>UserName:
							</strong></label>
							<div class="col-sm-12">
								<input class="form-control" id="username" type="text"
									name="username" placeholder="Username" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-12 control-label requiredField"><strong>Password:
							</strong></label>
							<div class="col-sm-12">
								<input class="form-control" id="password" type="password"
									name="password" placeholder="Password" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-12 control-label requiredField"><strong>Confirm
									Password: </strong></label>
							<div class="col-sm-12">
								<input class="form-control" id="repassword" type="password"
									name="repassword" placeholder="Confirm Password" required>
							</div>
						</div>
						<button id="submit" type="submit"
							class="btn btn-primary waves-effect waves-light">
							Register</button>

					</form>
				</div>
			</div>
		</section>
	</div>
</main>
<%@include file="Common/Scripts.jsp"%>
<%@include file="Common/Footer.jsp"%>