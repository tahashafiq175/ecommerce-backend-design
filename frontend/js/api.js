const BASE_URL="http://localhost:8080";


function getToken(){

    return localStorage.getItem("token");

}


async function apiCall(url, options={}){


    options.headers = {

        "Content-Type":"application/json",

        ...options.headers

    };


    const token=getToken();


    if(token){

        options.headers["Authorization"] =
        "Bearer " + token;

    }


    let response = await fetch(
        BASE_URL + url,
        options
    );


    return response;

}