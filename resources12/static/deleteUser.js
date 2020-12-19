document.getElementById("deleteUserModal").addEventListener("submit", deletePost)

function deletePost(e) {
    e.preventDefault();

    let id = document.getElementById("idDelete").value;
    let name = document.getElementById("nameDelete").value;
    let character = document.getElementById("characterDelete").value;
    let iq = document.getElementById("IQDelete").value;
    let roles = setRoles(Array.from(document.getElementById("roleDelete").selectedOptions)
        .map(option => option.value));

    fetch("http://localhost:8088/deleteUser", {
        method: "DELETE",
        headers: {
            "Accept": "application/json, text/plain, */*",
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            id: id,
            name: name,
            character: character,
            iq: iq
        })
    }).finally(() => {
        document.getElementById(id).remove()
        $('#deleteUser').modal("hide")
        getHeader()
    });

    function setRoles(someRoles) {
        let roles = [];
        if (someRoles.indexOf("ROLE_USER") >= 0) {
            roles.push({"role": "ROLE_USER"});
        }
        if (someRoles.indexOf("ROLE_ADMIN") >= 0) {
            roles.push({"role": "ROLE_ADMIN"});
        }
        return roles;
    }
}

function inputRolesIntoDelete() {
    fetch("http://localhost:8088/allRoles").then((res) => res.json())
        .then((data) => {
            let output = "";
            data.forEach(function (role) {
                output += `<option id="delete${role.role}">${role.role}</option>`;
            });
            document.getElementById("roleDelete").innerHTML = output;
        })
}

inputRolesIntoDelete()

function modalWindowDelete(id) {
    document.getElementById("idDelete").value = id;
    document.getElementById("nameDelete").value = $("#name" + id).text();
    document.getElementById("characterDelete").value = $("#character" + id).text();
    document.getElementById("IQDelete").value = $("#iq" + id).text();
}
