showAllUsers()

function showAllUsers() {
    console.log("showAllUsers activated")
    const allUsersURL = 'rest/users'
    fetch(allUsersURL)
        .then(response => response.json())
        .then(result => {
            for (let user of result) {
                let temp = `<tr id="row-${user.userId}">
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
            }
        })
}

function cleanTable() {
    $('#someTable tr:gt(0)').remove()
    showAllUsers()
}
