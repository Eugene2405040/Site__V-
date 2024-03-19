function postFormData(){

    let postData = formFieldsToJSON();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            onResponseFunction(this.responseText);
        } else if (this.readyState == 4) {
            alert('Something went wrong: \n' + this.responseText);
        }
    }
    xhttp.open("POST", "#" /*"/admin/create"*/, true);
    xhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhttp.send(JSON.stringify(postData));

    function onResponseFunction(responseTxt) {
        console.log(responseTxt);
        alert(responseTxt)
        //TODO: think about redirecting location:
//        window.location.href = '/admin'
    }
}
let requiredFormElements = document.querySelectorAll("[required]")
requiredFormElements.forEach((element) => {
    element.addEventListener("click", () => {
        element.style.background = "#fff"
        })
    }
)
function checkFormFields(){

    let result = true
    for(element of requiredFormElements){
        if (element.value.length < 1) {
            element.style.background = "#ffeeee"
            result = false
        }
    }
    if (!result) alert('Fill in the required fields')
    return result
}

document.querySelector(".post-button").onclick = (event) => {
    event.preventDefault();
    if (checkFormFields()) postFormData()
}