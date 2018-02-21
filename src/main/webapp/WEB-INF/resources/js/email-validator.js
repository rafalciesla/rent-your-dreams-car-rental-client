// var err = document.getElementById('taken');
//
// function valid() {
//     var form = document.getElementById('registration');
//     var mail = document.getElementById('mail');
//     var submitButton = document.getElementById('submi');
//
//     var XHR = new XMLHttpRequest();
//     XHR.open("POST", "http://localhost:8282/account/mailcheck", false);
//
//     var FD = new FormData(form);
//
//     XHR.onreadystatechange = function () {
//         if(XHR.readyState == 4 && XHR.status == 200) {
//             form.addEventListener("submit", function (event) {
//                 event.preventDefault();
//             });
//
//
//             submitButton.disabled = false;
//             mail.disabled = true;
//         } else {
//             err.innerText = 'Email already taken';
//         }
//     };
//
//     XHR.setRequestHeader("Content-type", "application/text");
//
//     XHR.send(mail.value);
//
// }
