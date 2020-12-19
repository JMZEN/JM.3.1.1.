function addNewUser() {
    const newUserURL = 'rest/users/'
    console.log("addNewUser activated")
    fetch(newUserURL, {
        method: 'POST',
        body: JSON.stringify({
            email: $('#newUserEmail').val(),
            password: $('#newUserPassword').val(),
            firstName: $('#newUserName').val(),
            lastName: $('#newUserLastname').val(),
            age: $('#newUserAge').val(),
            roles: [
                {
                    nameOfRole: $('#newUserRole').val()
                }
            ]
        }),
        headers: {'Content-type': 'application/json; charset=UTF-8'}
    })
}

