const button = document.querySelector("form button")
button.innerText = "Update"
button.onclick = (event) =>
{
    event.preventDefault();

    let putData = formFieldsToJSON()
    const setCurrentDate = confirm("Set current date ?");
    if(setCurrentDate === true){
        let date = new Date().toISOString().slice(0, 10)
        console.log(date);
        putData["modified"] = date
    }
//    console.log(putData)

    fetch('', {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(putData)
    }).then(response => {
      console.log("Status: " + response.status + ", Ok: " + response.ok + ", Body: " + response)
//      alert("Status: " + response.status + ", Ok: " + response.ok)
//      return response.json();
        return response.text();
    }).then(function(response) {
            console.log(response);
            window.location.href = '/admin/update/' + response
    }).catch(error => {
        alert(error);
    });
}