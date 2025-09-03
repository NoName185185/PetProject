document.addEventListener("DOMContentLoaded", function () {
  const allCards = document.querySelectorAll(".item-card");

  allCards.forEach(card => {
    const spec = card.dataset.spec?.toLowerCase();
    const basePrice = parseFloat(card.dataset.basePrice);
    const priceSpan = card.querySelector(".item-price span");
    const weightButtons = card.querySelectorAll(".item-weight-text p");

    // Проверка: есть ли смысл пересчитывать цену (если весов больше одного)
    const hasMultipleWeights = weightButtons.length > 1;

    weightButtons.forEach(p => {
      const rawWeight = parseFloat(p.dataset.weight);
      let displayWeight;

      // Отображаем граммы, если масса < 1
      if (rawWeight < 1) {
        displayWeight = (rawWeight * 1000).toFixed(0) + " г";
      } else {
        displayWeight = rawWeight + " кг";
      }
      p.textContent = displayWeight;

      p.addEventListener("click", () => {
        // Убираем стили со всех
        weightButtons.forEach(btn => btn.style.border = "none");

        // Подсветка выбранного
        p.style.boxSizing = "border-box";
        p.style.border = "1.7px solid orange";

        if (!hasMultipleWeights) return; // если масса только одна — не менять цену

        const selectedWeight = parseFloat(p.dataset.weight);
        let newPrice = 0;

        if (spec === "консервы") {
          newPrice = (basePrice * (10 * selectedWeight)).toFixed(2);
        } else {
          newPrice = (basePrice * selectedWeight).toFixed(2);
        }

        priceSpan.textContent = `${newPrice} ₸`;
      });
    });
  });
});
