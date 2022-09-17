newUser();

function newUser() {

    const form = document.forms["formRegistrationUser"];

    console.log(form)
    form.addEventListener('submit', ev => {
        ev.preventDefault();
        let newUserRoles = [{
            id: 2,
            name: 'ROLE_USER'
        }]
        fetch("http://localhost:8080/registration", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstName: form.firstName.value,
                lastName: form.lastName.value,
                age: form.age.value,
                email: form.email.value,
                password: form.password.value,
                roles: newUserRoles
            })
        }).then(() => {
            form.reset();
            $('#registrationUserModal').modal('hide');
        })
    })
}