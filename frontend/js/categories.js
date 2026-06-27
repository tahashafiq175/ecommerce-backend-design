async function loadCategories(){


    let res = await apiCall("/product/Get");


    let data = await res.json();


    let div = document.getElementById("categories");


    div.innerHTML = "";



    let alreadyAdded = [];



    data.forEach(product => {



        if(!alreadyAdded.includes(product.category)){


            alreadyAdded.push(product.category);



            div.innerHTML += `


            <div class="card"
            onclick="openProducts('${product.category}')">


                <h2>
                    ${product.category}
                </h2>


                <img 
                src="images/${product.image}">


            </div>


            `;


        }



    });

}



function openProducts(category){


    window.location.href =
    "products.html?category="
    + encodeURIComponent(category);


}


loadCategories();