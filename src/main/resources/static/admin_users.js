fetch('rest/users').then(value => {
    return value.json();
}).then((users => {
    for (let user of users) {
        let temp = `<tr id="row-${user.userId}">
                    <td>${user.userId}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${user.rolesAsString}</td>
                    </tr>`
        $('#admin-all-users-table').append(temp)
    }
}))