async function signup(){


let user={

userName:username.value,

email:email.value,

password:password.value,

roles:["USER"]

};


await fetch(
"http://localhost:8080/Public/signup",
{
method:"POST",

headers:{
"Content-Type":"application/json"
},

body:JSON.stringify(user)

});


alert("Account created");


location="index.html";

}