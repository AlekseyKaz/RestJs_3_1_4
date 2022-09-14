const Url = 'http://localhost:8080/api/admin'
async function sendRequest(method, url) {
    return  fetch(url).then(response => {
        return response.json()
    })
}
sendRequest('GET', Url )
    .then(data => console.log(data))
    .catch(err => console.log(err))
