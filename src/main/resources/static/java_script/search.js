document.getElementById("searchInput").addEventListener("input", function () {
  const query = this.value.trim();
  const container = document.getElementById("suggestions");

  if (query.length === 0) {
    container.innerHTML = "";
    return;
  }

  fetch(`/api/search?query=${encodeURIComponent(query)}`)
    .then(response => response.json())
    .then(data => {
      container.innerHTML = "";
      data.forEach(item => {
        const div = document.createElement("div");
        div.textContent = item.name;
        div.addEventListener("click", () => {
          window.location.href = item.url;
        });
        container.appendChild(div);
      });
    });
});