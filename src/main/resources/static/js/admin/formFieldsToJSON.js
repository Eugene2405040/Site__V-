function formFieldsToJSON(){
    let formElements = document.querySelectorAll(".form-data.category")
    let categoryData = {}
    for (let i = 0; i < formElements.length; i++){
        categoryData[formElements[i].id] = formElements[i].value
    }

    formElements = document.querySelectorAll(".form-data.page")
    let pageData = {}
    for (let i = 0; i < formElements.length; i++){
        pageData[formElements[i].id] = formElements[i].value
    }

    let postData = categoryData;
    postData["page"] = pageData

    return postData;
}