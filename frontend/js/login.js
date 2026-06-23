async function login(){


let user={
username:document.getElementById("userName").value,
password:document.getElementById("password").value
};


let res=await fetch(
"http://localhost:8080/Public/login",
{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify(user)

});


let token=await res.text();


localStorage.setItem("token",token);


window.location="categories.html";

}