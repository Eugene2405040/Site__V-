let translit = {
"а" : "a",
"б" : "b",
"в" : "v",
"г" : "g"
//, д, е, ё, ж, з, и, й, к, л, м, н, о, п, р, с, т, у, ф, х, ц, ч, ш, щ, ъ, ы, ь, э, ю, я
}
const abcRu = "а, б, в, г, д, е, ё, ж, з, и, й, к, л, м, н, о, п, р, с, т, у, ф, х, ц, ч, ш, щ, ъ, ы, ь, э, ю, я"
                .replaceAll(" ", "").split(",")
const abcEn = "a,b,v,g,d,e,yo,zh,z,i,j,k,l,m,n,o,p,r,s,t,u,f,h,ts,ch,sh,sch,',y,',e,yu,ya"
                .split(",")
let jsonRuEn = {" " : "-"}

abcRu.forEach((l) => {
        jsonRuEn[l] = abcEn[abcRu.indexOf(l)]
    })

console.log(jsonRuEn)

function translitChar(russianChar){
    return jsonRuEn[russianChar]
}
function translitText(russianText){
    let result = ""
    for (let i = 0; i < russianText.length; i++){
        result += translitChar(russianText.charAt(i))
    }
    return result
}

console.log(translitText("привет цветочки васельки"))
