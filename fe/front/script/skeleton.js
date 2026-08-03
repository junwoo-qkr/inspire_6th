const container = document.getElementById("card-container");

const showSkeletonUI = () => {
    const skeletons = Array.from({length: 6}, renderSkeletonCard).join("");
    container.innerHTML = skeletons;
}

const renderSkeletonCard = (item) => {
    return `
        <div class="skeleton-card">
            <img class="skeleton-img">
            <div class="skeleton-body">
                <div class="skeleton-line short"></div>
                <div class="skeleton-line title"></div>
                <div class="skeleton-line text"></div>
                <div class="skeleton-line text"></div>
            </div>
        </div>
    `;
}

const delay = (ms) => {
    return new Promise((resolve) => setTimeout(resolve, ms));
}

const renderCard = (item) => {
    return `
        <div class="card">
            <img src="${item.image}" alt="${item.title}" class="card-img">
            <div class="card-body">
                ${item.tag ? `<span class="card-tag">${item.tag}</span>` : ""}
                <h3 class="card-title">${item.title}</h3>
                <p class="card-desc">${item.description}</p>
                <div class="card-price">${item.price.toLocaleString()}원</div>
            </div>
        </div>
    `;
}

const renderCards = (items) => {
    console.log(container);
    container.innerHTML = items.map(renderCard).join("");
}

const loadData = async () => {
    showSkeletonUI();
    
    const [response] = await Promise.all([
        axios.get("../server/data.json"),
        delay(3000),
    ]);

    console.log(response)
    renderCards(response.data);
}

loadData();