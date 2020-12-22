showAllUsers()

function showAllUsers() {
    console.log("showAllUsers activated")
    fetch('rest/users')
        .then(value => value.json())
        .then((users => {
            for (let user of users) {
                let temp = `<tr id="row-${user.userId}">
                    <td id="uId">${user.userId}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${user.rolesAsString}</td>
                    
                    <td>
                        <button class="btn btn-info" onclick="updateUserModal(document.getElementById('uId').innerText)" type="button" data-toggle="modal" data-target=#modalEditView>Edit</button>
                    </td>
                   
<!--                    <td>-->
<!--                    <button class="btn btn-info" onclick=getUserForUpdate(${user.userId})-->
<!--                    type="button" data-toggle="modal" data-target="#editUserModal">${user.userId}</button>-->
<!--                    </td>-->

                    <td>
                    <button class="btn btn-danger" onclick="showDeleteUser(document.getElementById('uId').innerText)" type="button" data-toggle="modal" data-target=#modalDeleteView>Delete</button>
                    </td>
                    
<!--                    <td><button class="btn btn-danger" data-toggle="modal" -->

<!--                    data-target="#modalDeleteView" onclick="showDeleteUser()">Delete</button></td>-->
                    </tr>
`
                $('#mainTableBodyUsers').append(temp)
            }
        }))
}
