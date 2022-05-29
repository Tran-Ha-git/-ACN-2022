<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class='center'>
	<div class='right-panel-active container' id="container">
		<div class='form-container sign-up-container'>
			<form:form action="/auth/signup" modelAttribute="user">
				<h1>Create Account</h1>
				<div class='social-container'>
					<a href="#" class='social'><i class="fab fa-facebook-f"></i></a> <a
						href="#" class='social'><i class="fab fa-google-plus-g"></i></a> <a
						href="#" class='social'><i class="fab fa-linkedin-in"></i></a>
				</div>
				<form:input  path="username"  placeholder="Username" />
				<form:errors path="username" cssClass="error"/>
				<form:input  path="email" type="email" placeholder="Email" />
				<form:errors path="email" cssClass="error"/>
				<form:input  path="password" type="password" placeholder="Password" />
				<form:errors path="password" cssClass="error"/> 
				<form:input  path="fullname" placeholder="Full name" />
				<form:errors path="fullname" cssClass="error"/>
				<form:input  path="birthday" type="date" placeholder="Birthday" /> 
				<form:errors path="birthday" cssClass="error"/>  
				<form:input  path="address" placeholder="Address" /> 
				<form:errors path="address" cssClass="error"/>  
				<form:input  path="phone" placeholder="Phone" /> 
				<form:errors path="phone" cssClass="error"/> 
				<button>Sign Up</button>
			</form:form>
		</div>
		<div class='form-container sign-in-container'>
			<form action="/auth/login" method="POST">
				<h1>Sign in</h1>
				<div class='social-container'>
					<a href="#" class='social'><i class="fab fa-facebook-f"></i></a> <a
						href="#" class='social'><i class="fab fa-google-plus-g"></i></a> <a
						href="#" class='social'><i class="fab fa-linkedin-in"></i></a>
				</div>
				<span>or use your account</span> 
				<span>${error}</span>
				<input type="text"
					name="username"
					placeholder="Username" value="${userLogin.username}" required/> 
				<input type="password"
					name="password"
					placeholder="Password" value="${userLogin.password}" required/> 
				<a href="#">Forgot your password?</a>
				<button>Sign In</button>
			</form>
		</div>
		<div class='overlay-container'>
			<div class='overlay'>
				<div class='overlay-panel overlay-left'>
					<h1>Welcome Back!</h1>
					<p>To keep connected with us please login with your personal
						info</p>
					<button class='ghost' onclick="toggleForm()" id="signIn">Sign In</button>
				</div>
				<div class='overlay-panel overlay-right'>
					<h1>Hello, Friend!</h1>
					<p>Enter your personal details and start journey with us</p>
					<button class='ghost' onclick="toggleForm()" id="signUp">Sign Up</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script>

function toggleForm() {
	const container = document.getElementById('container');
    container.classList.toggle('right-panel-active');
}
</script>
