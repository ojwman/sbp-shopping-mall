function login(ev) {
	ev.preventDefault();
	
	var oData = {
		id: document.getElementById("loginId").value,
		password: document.getElementById("loginPassword").value
	};
	const formData = new URLSearchParams();
	formData.append("id", oData.id);
	formData.append("password", oData.password);
	
	var oOption = {
        method: "POST",
        headers: {
//            "Content-Type": "application/json"
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        /* body data type must match "Content-Type" header */
//        body: JSON.stringify(oData)
        body: formData
    };
	
	fetch("/user/login", oOption).then((res) => {
		if (res.status === 200) {
			res.text().then((text) => {
				var oResult = JSON.parse(text);
				
				if (oResult.bUser === true) {
					alert("is User");
					
					var token = res.headers.get("token");
					window.localStorage.setItem("token", token);
				} else {
					alert("no User");
				}
			});	
		} else {
			alert(res.statusText);	
		}
	}).catch(err => alert(err));
}

function getToken() {
	var token = window.localStorage.getItem("token");
	document.getElementById("token").innerText = token;
}

function removeToken() {
	window.localStorage.removeItem("token");
	document.getElementById("token").innerText = "";
}

function getMyInfo() {
	var token = window.localStorage.getItem("token");
	
	var oOption = {
        headers: {
            "token": token
        }
    };
	
	fetch("/user/auth/myinfo/ojw", oOption).then((res) => {
		if (res.status === 200) {
			res.text().then((text) => {
				alert(text);
			});	
		} else {
			alert(res.status);	
		}
	}).catch(err => alert(err));
}





























