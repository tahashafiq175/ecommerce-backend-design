async function adminLogin() {

    const user = {

        userName: document.getElementById("username").value.trim(),

        password: document.getElementById("password").value

    };

    if (user.userName === "" || user.password === "") {

        alert("Please enter username and password.");

        return;

    }

    try {

        const response = await fetch(
            "http://localhost:8080/Public/login",
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(user)
            }
        );

        if (!response.ok) {

            alert("Invalid admin username or password.");

            return;

        }

        const token = await response.text();

        localStorage.setItem("token", token);

        localStorage.setItem("role", "ADMIN");

        window.location.href = "admin-categories.html";

    }
    catch (error) {

        console.error(error);

        alert("Unable to connect to the server.");

    }

}