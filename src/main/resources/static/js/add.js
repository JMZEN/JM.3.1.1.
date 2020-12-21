$(document).ready(() => {
    $('#btnAddUser').click(() => {
        addNewUser();
    });

    function addNewUser() {
        const addUserURL = 'rest/users'
        console.log("addNewUser activated")
        const requestOptions = createPOSTRequestBody();

        fetch(addUserURL, requestOptions)
            .then(response => response.json())
            .then(user => {
                let temp = `<tr id="row-${userId}">
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
                $('#mainTableBodyUsers').append(temp)
                console.log('done row')
            }).then(function () {
            document.location.reload();
        })
    }
})

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

// function (response)
//
// {
//     if (response.status == '200') {
//         showAllUsers();
//     }

// function createUserTableRow() {
//     let temp = `<tr id="row-${userId}">
//                     <td id="idEdit">${user.userId}</td>
//                     <td>${user.firstName}</td>
//                     <td>${user.lastName}</td>
//                     <td>${user.age}</td>
//                     <td>${user.email}</td>
//                     <td>${user.rolesAsString}</td>
//
//                     <td>
//                         <button class="btn btn-info" onclick="func('${user.userId}')"
//                         type="button" data-toggle="modal" data-target=#modalEditView>Edit</button>
//                     </td>
//                     <td>
//                         <button class="btn btn-danger" onclick="showDeleteUser('${user.userId}')"
//                         type="button" data-toggle="modal" data-target=#modalDeleteView>Delete</button>
//                     </td>
//                 </tr>`
//     $('#mainTableBodyUsers').append(temp)
// }