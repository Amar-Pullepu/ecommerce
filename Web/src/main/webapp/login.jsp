<%@include file="Common/Header.jsp"%>
<main>
	<div class="container">
		<section class="mb-4">
			<div class="row wow fadeIn">
				<div class='col-6 offset-3'>
					<h1>Sign In</h1>

					<p>
						If you have not created an account yet, then please <a
							href="/account/register">sign up</a> first.
					</p>
					<form action='/account/login' method='post' class="form-horizontal">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> <div><h3>Account Login</h3></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" style="aligin:left">
								Username: </label>
							<div class="col-sm-12">
								<input class="form-control" id="username" type="text" name="username"
									placeholder="Username" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" style="aligin:left">
								Password: </label>
							<div class="col-sm-12">
								<input class="form-control" id="password" type="password"
									name="password" placeholder="Password" required>
							</div>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">Sign in</button>
						</div>
					</form>
				</div>
			</div>
		</section>
	</div>
</main>
<%@include file="Common/Scripts.jsp"%>
<%@include file="Common/Footer.jsp"%>