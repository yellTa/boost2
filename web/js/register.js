const form = document.getElementById('myForm');
const resetButton = document.getElementById('removeBtn')
const submitBtn = document.getElementById('submitBtn');

resetButton.addEventListener('click', function () {
    form.reset();
    console.log('reset complete!!!');
});

form.addEventListener('submit', (event) => {
    // 유효성 검사
    if (!form.checkValidity()) {
        alert('모든 필드를 올바르게 입력해 주세요.');
        event.preventDefault();
        return;
    }

    event.preventDefault();

    // FormData 객체 생성
    const formData = new FormData(form);
    const formObject = {};
    formData.forEach((value, key) => {
        formObject[key] = value;
    });

    console.log('폼 데이터:', formObject);

    fetch('http://localhost:8080/task', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formObject)
    })
        .then(response => {
            if (!response.ok) {
                alert('error');
                throw new Error('Network response was not ok.');
            }
            return response.text().then(text => {
                console.log('success!');
                form.reset();
                return text ? JSON.parse(text) : {};
            });
        })

        .catch(error => {
            console.error('error occurred', error);
            alert('error caught');
        });
});

