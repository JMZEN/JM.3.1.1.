document.getElementById("newUser").addEventListener("submit", addNewUser);

function addNewUser(e) {
    e.preventDefault();

    let name = document.getElementById("name").value;
    let character = document.getElementById("character").value;
    let iq = document.getElementById("iq").value;
    let password = document.getElementById("password").value;
    let roles = setRoles(Array.from(document.getElementById("roles").selectedOptions).map(option => option.value));

    fetch("http://localhost:8088/addUser", {
        method: 'POST',
        headers: {
            "Accept": "application/json, text/plain, */*",
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            id: null,
            name: name,
            password: password,
            character: character,
            iq: iq,
            roles: roles
        })
    })
        .finally(() => {
            inputUserAfterAdd(name);
            document.getElementById("allUsersTable").click();
            document.getElementById("addUser").reset();
        })

}

function inputRolesIntoAdd() {
    fetch("http://localhost:8088/allRoles").then((res) => res.json())
        .then((data) => {
            let output = "";
            data.forEach(function (role) {
                output += `<option id="${role.role}">${role.role}</option>`;
            });
            document.getElementById("roles").innerHTML = output;
        })
}

inputRolesIntoAdd()

