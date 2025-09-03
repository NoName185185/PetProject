function openRegistrationModal() {
  sessionStorage.setItem("previousPath", window.location.pathname);

  const modalHTML = `
    <div class="modal-overlay" id="registrationModal">
      <div class="modal" style="height:55%">
        <button class="close-btn" onclick="closeModal()">×</button>
        <h2>Тіркелу</h2>

        <input type="text" id="nick_name" placeholder="Сіздің никнейміңіз">

        <input type="text" id="username" placeholder="example@gmail.com">

        <div class="password-wrapper">
          <input type="password" id="password1" placeholder="Құпия сөзіңізді жазыңыз">
          <span class="toggle-password" onclick="togglePassword('password1', this)">👁️</span>
        </div>

        <div class="password-wrapper">
          <input type="password" id="password2" placeholder="Құпия сөзді қайта жазыңыз">
          <span class="toggle-password" onclick="togglePassword('password2', this)">👁️</span>
        </div>

        <button class="submit-btn" onclick="handleSubmit()">Растау</button>
        <a href="#" class="login-link">Менің есептік жазбам бар</a>
      </div>
    </div>
  `;

  document.getElementById("modal-container").innerHTML = modalHTML;

  document.getElementById("registrationModal").addEventListener("click", function (e) {
    if (e.target === this) closeModal();
  });
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
function closeModal() {
  document.getElementById("modal-container").innerHTML = "";
}

function validateEmail(email) {
  const emailRegex = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;
  return emailRegex.test(email);
}

function validatePassword(password) {
  const hasMinLength = password.length >= 8;
  const noSpecialForbidden = !/[!@#$]/.test(password);
  const hasUpper = /[A-Z]/.test(password);
  const hasLower = /[a-z]/.test(password);
  return hasMinLength && noSpecialForbidden && hasUpper && hasLower;
}

function handleSubmit() {
  const username = document.getElementById("username").value.trim();
  const nickName = document.getElementById("nick_name").value.trim();
  const password1 = document.getElementById("password1").value;
  const password2 = document.getElementById("password2").value;

  if (!username || !nickName || !password1 || !password2) {
    alert("Барлық өрістер толтырылуы тиіс!");
    return;
  }

  if (!validateEmail(username)) {
    alert("Электрондық пошта форматы дұрыс емес (тек gmail.com рұқсат етілген).");
    return;
  }

  if (!validatePassword(password1)) {
    alert("Құпия сөз шарттарға сәйкес келмейді.");
    return;
  }

  if (password1 !== password2) {
    alert("Құпия сөздер сәйкес келмейді!");
    return;
  }

  const userAva = "https://www.svgrepo.com/show/444846/person-boy.svg";

  const registrationData = {
    username: username,
    nick_name: nickName,
    password: password1,
    user_ava: userAva
  };

  fetch("/api/register", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(registrationData)
  })
  .then(async response => {
    const data = await response.json();
    if (!response.ok) {
      throw new Error(data.message || "Тіркелу кезінде қате пайда болды");
    }
    if (!data.userId) {
      throw new Error("Қолданушы ID қайтарылмады");
    }
    window.location.href = `/${data.userId}/main_registered`;
  })
  .catch(error => {
    alert(error.message);
  });
}


function registerUser(username, password) {
fetch("/api/register", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  body: JSON.stringify(registrationData)
})
.then(async response => {
  const data = await response.json();
  if (!response.ok) {
    throw new Error(data.message || "Тіркелу кезінде қате пайда болды");
  }
  if (!data.userId) {
    throw new Error("Қолданушы ID қайтарылмады");
  }
  window.location.href = `/${data.userId}/main_registered`;
})
.catch(error => {
  alert(error.message);
});


}
