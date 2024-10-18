const form = document.getElementById('myForm');
const resetButton = document.getElementById('removeBtn')

resetButton.addEventListener('click', function () {
    form.reset();
    console.log('reset complete!!!');
})
