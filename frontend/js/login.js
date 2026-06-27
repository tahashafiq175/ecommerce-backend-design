async function login() {

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
            alert("Invalid username or password.");
            return;
        }

        const token = await response.text();

        localStorage.setItem("token", token);

        window.location.href = "categories.html";

    } catch (error) {
        console.error(error);
        alert("Unable to connect to the server.");
    }
}async function login() {

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
            alert("Invalid username or password.");
            return;
        }

        const token = await response.text();

        localStorage.setItem("token", token);

        window.location.href = "categories.html";

    } catch (error) {
        console.error(error);
        alert("Unable to connect to the server.");
    }
}