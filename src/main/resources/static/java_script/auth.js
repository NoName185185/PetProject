function openLoginModal() {
  const modalHTML = `
    <div class="modal-overlay" id="loginModal">
      <div class="modal">
        <button class="close-btn" onclick="closeModal()">×</button>
        <h2>Авторизация</h2>
        <input type="text" id="login-username" placeholder="Атыңызды жазыңыз">
        <div class="password-wrapper">
          <input type="password" id="password1" placeholder="Құпия сөзіңізді жазыңыз">
          <span class="toggle-password" onclick="togglePassword('password1', this)">👁️</span>
        </div>
        <button class="submit-btn" onclick="handleLogin()">Кіру</button>
        <a href="#" class="login-link" onclick="closeModal(); openRegistrationModal();">Менде есеп жоқ</a>
      </div>
    </div>
  `;
  document.getElementById("modal-container").innerHTML = modalHTML;
  document.getElementById("loginModal").addEventListener("click", function (e) {
    if (e.target === this) closeModal();
  });
}

function openRegistrationModal() {
  const modalHTML = `
    <div class="modal-overlay" id="registrationModal">
      <div class="modal">
        <button class="close-btn" onclick="closeModal()">×</button>
        <h2>Тіркелу</h2>
        <input type="text" id="register-email" placeholder="Email жазыңыз">
        <input type="password" id="register-password" placeholder="Құпия сөзді жазыңыз">
        <button class="submit-btn" onclick="handleRegistration()">Тіркелу</button>
        <a href="#" class="login-link" onclick="closeModal(); openLoginModal();">Менде тіркелгі бар</a>
      </div>
    </div>
  `;
  document.getElementById("modal-container").innerHTML = modalHTML;
  document.getElementById("registrationModal").addEventListener("click", function (e) {
    if (e.target === this) closeModal();
  });
}

function closeModal() {
  document.getElementById("modal-container").innerHTML = "";
}

function togglePassword(inputId, iconElement) {
  const input = document.getElementById(inputId);
  if (input.type === "password") {
    input.type = "text";
    iconElement.textContent = "🙈";
  } else {
    input.type = "password";
    iconElement.textContent = "👁️";
  }
}

function handleLogin() {
  const username = document.getElementById("login-username").value.trim();
  const password = document.getElementById("password1").value; // Исправлено

  fetch("/api/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ username, password })
  })
  .then(response => {
    if (!response.ok) throw new Error("Қате жауап");
    return response.text();
  })
  .then(userId => {
    window.location.href = `/${userId}/main_registered`;
  })
  .catch(error => {
    alert("Кіру кезінде қате шықты.");
    console.error(error);
  });
}

function handleRegistration() {
  const email = document.getElementById("register-email").value.trim();
  const password = document.getElementById("register-password").value;

  const emailRegex = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;
  const hasUpper = /[A-Z]/.test(password);
  const hasLower = /[a-z]/.test(password);
  const forbiddenSymbols = /[!@#$]/;

  if (!emailRegex.test(email)) {
    alert("Email тек @gmail.com форматында болу керек.");
    return;
  }

  if (password.length < 8) {
    alert("Құпия сөз кемінде 8 символдан тұруы керек.");
    return;
  }

  if (forbiddenSymbols.test(password)) {
    alert("Құпия сөзде !@#$ таңбалары болмауы керек.");
    return;
  }

  if (!hasUpper || !hasLower) {
    alert("Құпия сөзде кемінде бір бас және бір кіші әріп болу керек.");
    return;
  }

  fetch("/api/check-user", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email })
  })
  .then(res => res.json())
  .then(data => {
    if (data.exists) {
      alert("Бұл email арқылы тіркелген қолданушы бар.");
    } else {
      fetch("/api/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
      })
      .then(response => {
        if (!response.ok) throw new Error("Тіркелу сәтсіз аяқталды.");
        alert("Сәтті тіркелдіңіз!");
        closeModal();
        openLoginModal();
      })
      .catch(error => {
        alert("Сервер қателігі.");
        console.error(error);
      });
    }
  })
  .catch(err => {
    alert("Қолданушыны тексеру кезінде қате шықты.");
    console.error(err);
  });
}
