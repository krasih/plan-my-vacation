window.onload = function () {

    let langCookie = getCookie("lang").toUpperCase();
    // alert("Language: " + lang);
    // document.getElementById(lang).setAttribute("text", lang);
    document.getElementById("lang").innerText = langCookie;

};

function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "en";
}