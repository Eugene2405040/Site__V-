document.querySelector("#logout")
    .onclick = (event) => {
        event.preventDefault();
        logout()
    }
function logout(){
    fetch('/log-out', {
        method: 'GET'
    }).then(response => {
        console.log("Status: " + response.status + ", Ok: " + response.ok)
//        alert("Status: " + response.status + ", Ok: " + response.ok)
//        location.reload()
    }).catch(error => {
        alert(error);
    });
}