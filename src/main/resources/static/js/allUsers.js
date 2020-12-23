showAllUsers();

function showAllUsers() {
    const allUsersURL = 'rest/users'

    fetch(allUsersURL)
        .then(response => {
            if (response.ok) {
                return response.json()
            }
        }).then(result => {
        const tableBody = $('#fillTableAllUsers tbody');
        tableBody.empty();

        for (let user of result) {
            let temp = `<tr id="row-${user.userId}">
                    <td id="idEdit">${user.userId}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${user.rolesAsString}</td>
                    
                    <td> 
                        <button class="btn btn-info" onclick="updateUserModal('${user.userId}')" 
                        type="button" data-toggle="modal" data-target=#modalEditView>Edit</button>
                    </td>
                    <td>
                        <button class="btn btn-danger" onclick="showDeleteUser('${user.userId}')" 
                        type="button" data-toggle="modal" data-target=#modalDeleteView>Delete</button>
                    </td>
                </tr>`

            $(tableBody).append(temp)
        }
    })
}