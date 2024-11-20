console.log("Hallo allemaal in ello primo testero!");
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('checkButton').addEventListener('click', function() {
        fetch('/priem')
            .then(response => response.text())
            .then(data => {
                document.getElementById('response').value = data;
            })
            .catch(error => {
                console.error('Error fetching data:', error);
                document.getElementById('response').innerText = 'Error loading data';
            });
        const number = document.getElementById('priemKandidaat').value;
        fetch(`/priem`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({number: number})
        })
            .then(response => response.json())
            .then(data => {
                const resultDiv = document.getElementById('priem-result');
                if (data.isPrime) {
                    resultDiv.innerText = `${number} is een priemgetal`;
                } else {
                    resultDiv.innerText = `${number} is GEEN priemgetal`;
                }
            })
            .catch(error => {
                console.error('Error fetching data:', error);
                document.getElementById('result').innerText = 'Error loading data';
            })
    })
})