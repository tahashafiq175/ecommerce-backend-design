let params = new URLSearchParams(location.search);

let category = params.get("category");


async function loadProducts() {

    let res = await apiCall("/product/GetById/" + category);

    let products = await res.json();

    let div = document.getElementById("products");

    div.innerHTML = "";

    products.forEach(product => {

        div.innerHTML += `

        <div class="card" onclick="openProduct('${product.id}')">

            <div class="product-info">

                <img src="images/${product.images[0]}"
                     alt="${product.name}">

                <h2>${product.name}</h2>

                <h3>Rs ${product.price}</h3>

                <p>${product.description}</p>

                <p><strong>Stock:</strong> ${product.stock}</p>

                <button>View Product</button>

            </div>

            <div class="side-image">

                <img src="images/${product.images[1]}"
                     alt="${product.name}">

            </div>

        </div>

        `;
    });

}


function openProduct(id){
    window.location.href = "product.html?id=" + id;
}


loadProducts();