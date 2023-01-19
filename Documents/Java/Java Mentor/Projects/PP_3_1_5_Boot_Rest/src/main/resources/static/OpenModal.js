async function openAndFillInTheModal(form, modal, id){
    modal.show();
    let user = await getUser(id);
    form.id.value = user.id;
    form.username.value = user.username;
    form.surname.value = user.surname;
    form.email.value = user.email;
    form.roles.value = user.roles[0].id;
}