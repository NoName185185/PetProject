document.addEventListener("DOMContentLoaded", () => {
  const rangeInput = document.getElementById("weight-range");
  const floatValue = document.getElementById("range-float");
  const filterButtons = document.querySelectorAll(".filter-btn");
  const cards = document.querySelectorAll(".item-card");

  let currentFilter = "all";

  // Плавающий индикатор
  function updateFloat() {
    const value = parseFloat(rangeInput.value);
    floatValue.textContent = value + " кг";

    const min = parseFloat(rangeInput.min);
    const max = parseFloat(rangeInput.max);
    const percent = (value - min) / (max - min);
    const offset = percent * rangeInput.offsetWidth;

    floatValue.style.left = `${offset}px`;
  }

  // Основная фильтрация
  function applyFilters() {
    const selectedWeight = parseFloat(rangeInput.value);

    cards.forEach(card => {
      const cardSpec = card.getAttribute("data-spec")?.toLowerCase();
      const weightTags = card.querySelectorAll("[data-weight]");

      let hasValidWeight = false;

      weightTags.forEach(w => {
        const val = parseFloat(w.dataset.weight);
        if (!isNaN(val) && val >= selectedWeight) {
          hasValidWeight = true;
        }
      });

      const matchesSpec = currentFilter === "all" || cardSpec === currentFilter;

      if (matchesSpec && hasValidWeight) {
        card.classList.remove("hidden");
      } else {
        card.classList.add("hidden");
      }
    });
  }

  // Обновление фильтра по кнопкам
  filterButtons.forEach(button => {
    button.addEventListener("click", () => {
      currentFilter = button.getAttribute("data-filter").toLowerCase();

      if (currentFilter === "all") {
        rangeInput.value = 0.05;  // Сбрасываем значение слайдера
        updateFloat();           // Обновляем плавающий индикатор
      }

      applyFilters();
    });
  });

  // Обновление при отпускании ползунка (mouseup / touchend)
  rangeInput.addEventListener("input", updateFloat);
  rangeInput.addEventListener("change", applyFilters); // change работает при отпускании мыши
  rangeInput.addEventListener("touchend", applyFilters); // для мобилки

  updateFloat(); // При загрузке страницы
});
