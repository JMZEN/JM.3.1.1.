function addNewUser() {
    const addUserURL = 'rest/users'
    console.log("addNewUser activated")
    fetch(addUserURL, {
        method: 'POST',
        body: JSON.stringify({
            email: $('#newUserEmail').val(),
            password: $('#newUserPassword').val(),
            firstName: $('#newUserName').val(),
            lastName: $('#newUserLastname').val(),
            age: $('#newUserAge').val(),
            roles: [
                {
                    nameOfRole: $('#newUserRole').val()[0]
                }
            ]
        }),
        headers: {'Content-type': 'application/json; charset=UTF-8'}
    }).then(function (response) {
        return response.text();
    }).then(function (text) {
        console.log(text);
    })
}