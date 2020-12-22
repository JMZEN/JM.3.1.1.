$('#btnAddUser').click(() => {
    addNewUser()
        .then(refreshPage)
        .then(function () {
            document.getElementById('newUserForm').reset();
        }).then($('#userTableLink').click())
})

function addNewUser() {
    const dfd = new $.Deferred();
    event.preventDefault()
    const addUserURL = 'rest/users'
    console.log("addNewUser activated")
    const requestOptions = createPOSTRequestBody();

    fetch(addUserURL, requestOptions)
        .then(dfd.resolve)
    return dfd.promise();
}

function refreshPage() {
    showAllUsers();
}

function createPOSTRequestBody() {
    return {
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
        headers: {'Content-type': 'application/json; charset=UTF-8'},
    };
}