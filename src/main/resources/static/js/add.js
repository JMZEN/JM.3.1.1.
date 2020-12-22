// // $(document).ready(() => { .then(refreshPage)
$('#btnAddUser').click(() => {
    addNewUser()
        // .then(refreshPage)
        .then(function () {
        document.getElementById('newUserForm').reset();
    }).then()

})

function refreshPage() {
    showAllUsers();
}

function addNewUser() {
    const dfd = new $.Deferred();
    event.preventDefault()
    const addUserURL = 'rest/users'
    console.log("addNewUser activated")
    const requestOptions = createPOSTRequestBody();

    fetch(addUserURL, requestOptions)

        .then(response => response.json())
        .then(user => {
            let temp = createNewUserRow(user);
            $('#mainTableBodyUsers').append(temp)
        })

        .then(dfd.resolve)
    return dfd.promise();
}

function createNewUserRow(user) {
    let newUserRow = `<tr id="row-${user.userId}">
                    <td id="idEdit">${user.userId}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${user.rolesAsString}</td>

                    <td>
                        <button class="btn btn-info" onclick="func('${user.userId}')"
                        type="button" data-toggle="modal" data-target=#modalEditView>Edit</button>
                    </td>
                    <td>
                        <button class="btn btn-danger" onclick="showDeleteUser('${user.userId}')"
                        type="button" data-toggle="modal" data-target=#modalDeleteView>Delete</button>
                    </td>
                </tr>`
    return newUserRow;
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
        // success:
    };
}




//     .then(document.getElementById("btnAddUser").onclick = function () {
//         //     window.location.hash = "#usersTable";
//         // })
//             // .then(window.location.hash = "#usersTable")
//

//
//     // });