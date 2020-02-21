/**
 * 
 */
function signUp(ev) {
	var oData = {
		id: document.getElementById("id").value,
		name: document.getElementById("name").value,
		password: document.getElementById("password").value,
		passwordCheck: document.getElementById("passwordCheck").value,
		phone: document.getElementById("phone").value,
	};
//	window.alert(JSON.stringify(oData));
	
	var oOption = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        /* body data type must match "Content-Type" header */
        body: JSON.stringify(oData)
    };
	
	fetch("/user/sign-up", oOption).then((res) => {
		if (res.status === 201) {
			alert("sign up ok");
		} else {
			alert("sign up failed");
		}
	}).catch((err) => {alert(err);});
}