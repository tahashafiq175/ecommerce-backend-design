async function loadCategories() {

    try {

        let response = await apiCall("/product/Get");

        if (!response.ok) {

            alert("Unable to load categories.");

            return;
        }

        let categories = await response.json();

        let container = document.getElementById("categories");

        container.innerHTML = "";

        let alreadyAdded = [];

        categories.forEach(category => {

            if (!alreadyAdded.includes(category.category)) {

                alreadyAdded.push(category.category);

                container.innerHTML += `

                    <div class="card"
                         onclick="openCategory('${category.category}')">

                        <img src="images/${category.image}"
                             alt="${category.category}">

                        <h3>${category.category}</h3>

                    </div>

                `;

            }

        });

    }
    catch (error) {

        console.error(error);

        alert("Server Error");

    }

}

function openCategory(category) {

    window.location.href =
        "admin-products.html?category=" +
        encodeURIComponent(category);

}

function logout() {

    localStorage.removeItem("token");
    localStorage.removeItem("role");

    window.location.href = "admin-login.html";

}

loadCategories();