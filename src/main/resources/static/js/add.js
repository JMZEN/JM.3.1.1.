function addNewUser() {
    const addUserURL = 'http://localhost:8080/admin/add'
    console.log("addNewUser activated")
    fetch(addUserURL, {
        method: 'POST',
        body: JSON.stringify({
            firstName: $('#newUserName').val(),
            lastName: $('#newUserLastname').val(),
            department: $('#newUserDepartment').val(),
            email: $('#newUserEmail').val(),
            username: $('#newUserLogin').val(),
            password: $('#newUserPassword').val(),
            roles: [
                {
                    id: parseInt($('#newUserRole').val()[0])
                }
            ]
        }),
        headers: {'Content-type': 'application/json; charset=UTF-8'}
    })
}