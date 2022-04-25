<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class='center'>
	<div class='right-panel-active container' id="container">
		<div class='form-container sign-up-container'>
			<form action="#">
				<h1>Create Account</h1>
				<!-- <div class='social-container'>
					<a href="#" class='social'><i class="fab fa-facebook-f"></i></a> <a
						href="#" class='social'><i class="fab fa-google-plus-g"></i></a> <a
						href="#" class='social'><i class="fab fa-linkedin-in"></i></a>
				</div> -->
				<span>or use your email for registration</span> <input type="text"
					placeholder="Name" /> 
				<input type="email" placeholder="Email" />
				<input type="password" placeholder="Password" />
				<button>Sign Up</button>
			</form>
		</div>
		
		<div class='form-container sign-in-container'>
			<form action="/a/login" method="get">
				<h1>Sign in</h1>
				
				<!-- <div class='social-container'>
					<a href="#" class='social'><i class="fab fa-facebook-f"></i></a> 
					<a href="#" class='social'><i class="fab fa-google-plus-g"></i></a> 
					<a href="#" class='social'><i class="fab fa-linkedin-in"></i></a>
				</div> 
				<span>or use your account</span> 
				<div class='social-container'>
				<span>
					<c:if test="${empty param.error }">
						Đăng nhập không thành công <br> Vui lòng kiểm tra lại
						username và password.
					</c:if>
					
					
				</span>
				</div>
				-->
				<input type="username" placeholder="Username" name="username" /> <input
					type="password" placeholder="Password" name="password" /> 
					<a href="#">Forgot your password?</a>
				<button type="submit">Sign In</button>
			</form>
		</div>
		<div class='overlay-container'>
			<div class='overlay'>
				<div class='overlay-panel overlay-left'>
					<h1>Welcome Back!</h1>
					<p>To keep connected with us please login with your personal
						info</p>
					<button class='ghost' onclick="toggleForm()" id="signIn">Sign
						In</button>
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
    const toggleForm = () => {
      const container = document.getElementById('container');
      container.classList.toggle('right-panel-active');
    };
  </script>
