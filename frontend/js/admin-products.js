// ======================================
// Global Variables
// ======================================

let params = new URLSearchParams(window.location.search);

let category = params.get("category");

let allProducts = [];

let currentProductId = null;


// ======================================
// Load Products
// ======================================

async function loadProducts() {

    try {

        document.getElementById("loader").style.display = "block";

        document.getElementById("categoryTitle").innerText =
            category + " Products";

        const response = await apiCall("/product/GetById/" + category);

        if (!response.ok) {

            document.getElementById("loader").style.display = "none";

            alert("Unable to load products.");

            return;

        }

        allProducts = await response.json();

console.log(allProducts);
console.log(allProducts[0]);
console.log(allProducts[0].id);
console.log(typeof allProducts[0].id);

        displayProducts(allProducts);

        document.getElementById("loader").style.display = "none";

    }

    catch (error) {

        console.error(error);

        document.getElementById("loader").style.display = "none";

        alert("Server Error");

    }

}



// ======================================
// Display Products
// ======================================

function displayProducts(products) {

    const div = document.getElementById("products");

    div.innerHTML = "";

    if(products.length===0){

        div.innerHTML = `
            <h2>No Products Found</h2>
        `;

        return;

    }

    products.forEach(product => {
//         console.log(product);
// console.log("ID =", product.id);
// console.log("Type =", typeof product.id);

        div.innerHTML += `

        <div class="card">

            <h2>${product.name}</h2>

            <div class="price">
                Rs ${product.price}
            </div>

            <div class="stock">
                Stock : ${product.stock}
            </div>

            <div class="description">
                ${product.description}
            </div>

            <div class="card-buttons">

                <button
                    class="update-btn"
                    onclick="openUpdateModal('${product.id}')">

                    Update

                </button>

                <button
                    class="delete-btn"
                    onclick="openDeleteModal('${product.id}')">

                    Delete

                </button>

            </div>

        </div>

        `;

    });

}



// ======================================
// Search Products
// ======================================

function searchProducts(){

    let keyword = document
        .getElementById("search")
        .value
        .toLowerCase();

    let filtered = allProducts.filter(product =>

        product.name.toLowerCase().includes(keyword)

    );

    displayProducts(filtered);

}



// ======================================
// Logout
// ======================================

function logout(){

    localStorage.removeItem("token");

    localStorage.removeItem("role");

    window.location.href = "admin-login.html";

}



// ======================================
// Temporary Functions
// (Part 2 will replace them)
// ======================================

// ======================================
// ADD PRODUCT MODAL
// ======================================


function openAddModal(){

     currentProductId = null;

     document.getElementById("modalTitle")
        .innerText="Add Product";


    clearForm();


    document.getElementById("productModal")
        .style.display="flex";

}




// ======================================
// UPDATE PRODUCT MODAL
// ======================================
function openUpdateModal(id){


    currentProductId = id;


    let product =
        allProducts.find(
            p => String(p.id) === String(id)
        );


    if(!product){

        alert("Product not found");

        return;

    }



    document.getElementById("modalTitle")
        .innerText="Update Product";



    document.getElementById("name")
        .value = product.name || "";


    document.getElementById("price")
        .value = product.price || "";


    document.getElementById("stock")
        .value = product.stock || "";


    document.getElementById("description")
        .value = product.description || "";



    if(product.images && product.images.length > 0){


        document.getElementById("image1")
            .value = product.images[0] || "";


        document.getElementById("image2")
            .value = product.images[1] || "";

    }
    else{

        document.getElementById("image1")
            .value="";

        document.getElementById("image2")
            .value="";

    }



    document.getElementById("productModal")
        .style.display="flex";

}



// ======================================
// SAVE PRODUCT
// ADD + UPDATE
// ======================================


async function saveProduct(){



    let product={


        name:
        document.getElementById("name").value.trim(),


        price:
        Number(document.getElementById("price").value),


        stock:
        Number(document.getElementById("stock").value),


        description:
        document.getElementById("description").value.trim(),



        category:category,



        images:[

            document.getElementById("image1").value.trim(),

            document.getElementById("image2").value.trim()

        ]

    };



    if(product.name==="" ||
       !product.price ||
       !product.stock){
        alert("Please fill required fields");
        return;

    }

    let token =
        localStorage.getItem("token");
    try{


        let url;

        let method;



        // ADD

        if(currentProductId===null){
            url=
            "http://localhost:8080/admin/postDetail/"
            +
            category;


            method="POST";


        }

        // UPDATE

        else{


            url=
            "http://localhost:8080/admin/ChangeDetail/"
            +
            currentProductId;


            method="PUT";


        }




        let response =
            await fetch(url,{


                method:method,


                headers:{


                    "Content-Type":"application/json",


                    "Authorization":
                    "Bearer "+token


                },


                body:
                JSON.stringify(product)


            });



        if(!response.ok){


            alert("Operation failed");

            return;

        }



        alert(
            currentProductId?
            "Product Updated":
            "Product Added"
        );



        closeModal();


        loadProducts();



    }


    catch(error){


        console.log(error);

        alert("Server Error");

    }



}




// ======================================
// CLEAR FORM
// ======================================


function clearForm(){


    document.getElementById("name").value="";

    document.getElementById("price").value="";

    document.getElementById("stock").value="";

    document.getElementById("description").value="";

    document.getElementById("image1").value="";

    document.getElementById("image2").value="";

}


// ======================================
// DELETE PRODUCT MODAL
// ======================================

function openDeleteModal(id){

    console.log("Delete ID =", id);

    currentProductId = id;

    document.getElementById("deleteModal").style.display="flex";
}





function closeDeleteModal(){


    document.getElementById("deleteModal")
        .style.display="none";


}





// ======================================
// DELETE PRODUCT
// ======================================


async function deleteProduct(){



    if(currentProductId===null)
        return;



    let token =
        localStorage.getItem("token");



    try{


        document.getElementById("loader")
            .style.display="block";



        let response =
        await fetch(

            "http://localhost:8080/admin/DeleteDetail/"
            +
            currentProductId,


            {


                method:"DELETE",


                headers:{


                    "Authorization":
                    "Bearer "+token


                }


            }

        );




        document.getElementById("loader")
            .style.display="none";




        if(!response.ok){


            alert(
                "Delete failed"
            );


            return;

        }




        alert(
            "Product Deleted Successfully"
        );



        closeDeleteModal();



        currentProductId=null;



        loadProducts();



    }



    catch(error){


        console.log(error);


        document.getElementById("loader")
            .style.display="none";


        alert(
            "Server Error"
        );


    }


}


// ======================================
// CLOSE MODAL
function closeModal(){

    document.getElementById("productModal").style.display = "none";

}
// =====================================

// ======================================
// Start Page
// ======================================

loadProducts();