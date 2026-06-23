let params = new URLSearchParams(location.search);

let category = params.get("category");


async function loadProducts(){


    let res = await apiCall(
        "/ProductDetail/getAllDetails/" + category
    );


    let products = await res.json();


    let div = document.getElementById("products");

    div.innerHTML = "";


    products.forEach(product => {


        let img1 = product.images[0];
        let img2 = product.images[1];


        div.innerHTML += `

        <div class="card"
        onclick="openProduct('${product.id}')">


            <div class="product-info">


                <img class="main-image"
                src="images/${img1}">


                <h2>${product.name}</h2>


                <p class="price">
                Rs ${product.price}
                </p>


                <p class="description">
                ${product.description}
                </p>


                <p class="stock">
                Stock: ${product.stock}
                </p>


                <button>
                View Product
                </button>


            </div>



            <img class="side-image"
            src="images/${img2}">


        </div>

        `;

    });

}


function openProduct(id){

    window.location.href =
    "product.html?id="+id;

}


loadProducts();