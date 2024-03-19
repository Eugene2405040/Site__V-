function formatEnglishURL(russianText){
    if(russianText.length < 1) return null;
    const regEx = /[^a-z^0-9]+/gm;
    return translateRuToEn(russianText).toLowerCase().replaceAll(regEx, "-")
}
function translateRuToEn(russianText){
    let translateUrl = "https://translate.googleapis.com/translate_a/single?format=text&client=gtx&sl=" + "ru" + "&tl=" + "en" + "&dt=t&q=" + russianText;
    // sl – язык оригинала, tl – язык для перевода, originalText – текст запроса (можно использовать результат string.match(/.{1,2000}(?=\.)/gi))

    function httpGet(url) {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open("GET", url, false);
        xmlHttp.send(null);
        return xmlHttp.responseText;
    }
    return JSON.parse(httpGet(translateUrl))[0][0][0]
}