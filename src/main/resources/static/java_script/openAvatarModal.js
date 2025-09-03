
    function openAvatarModal() {
        document.getElementById("avatarModal").style.display = "block";
    }

    function closeAvatarModal() {
        document.getElementById("avatarModal").style.display = "none";
    }

    function submitAvatarUrl() {
        const url = document.getElementById("newAvatarUrl").value;

        fetch(`/user/${userId}/update-avatar`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ avatarUrl: url })
        }).then(response => {
            if (response.ok) {
                location.reload(); // Перезагрузим страницу для обновления аватарки
            } else {
                alert("Ошибка обновления аватара");
            }
        });
    }

    // Закрытие модального окна при клике вне него
    window.onclick = function(event) {
        const modal = document.getElementById("avatarModal");
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }