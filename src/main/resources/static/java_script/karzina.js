 function addToCart(button) {
        const itemCard = button.closest('.item-card');
        const userId = window.USER_ID;
        const name = itemCard.querySelector('.item-name').innerText;
        const itemImg = itemCard.querySelector('img').getAttribute('src');
        const price = itemCard.querySelector('.price').getAttribute('data-base-price');

        const payload = {
            userId: userId,
            name: name,
            itemImg: itemImg,
            price: parseFloat(price),
            count: 1
        };
        console.log(payload)
        fetch('/api/karzina/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        })
        .then(res => {
            if (res.ok) {
                alert('Товар добавлен в корзину!');
            } else {
                alert('Ошибка при добавлении в корзину');
            }
        });
    }