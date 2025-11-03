import "../Login.css";

function loginUser() {
  const form = document.getElementById("loginForm");

  form.addEventListener("submit", (e) => {
    e.preventDefault();

    const username = data.get().toLowerCase();
  });
}

export default function Login() {
  return (
    <div class="login-container">
      <img src="../Assets/YUCircleLogo.png" />
      <h2>Login/Register</h2>
      <form id="loginForm">
        <input
          type="text"
          id="usernameInput"
          placeholder="Username or Email"
          required
        />
        <input
          type="password"
          id="passwordInput"
          placeholder="Password"
          required
        />
        <button type="submit">Sign In</button>
      </form>
    </div>
  );
}

try {
  const response = await fetch("http://localhost:8080/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });

  const result = await response.json();
} catch (err) {}
