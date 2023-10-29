async function getLocation() {
    return new Promise((resolve, reject) => {
        if ("geolocation" in navigator) {
            navigator.geolocation.getCurrentPosition(resolve, reject);
        } else {
            reject(new Error("Geolocation is not available in your browser."));
        }
    });
}

async function maps() {
    try {
        const position = await getLocation();
        const lat = position.coords.latitude;
        const lng = position.coords.longitude;

        mapboxgl.accessToken = 'pk.eyJ1IjoiZWFsZmxtIiwiYSI6ImNsNmEyYTl0NTBjeXkzanFmajV1ZmFkcXEifQ.ajO7MarYAHOccS4yrY4cPg';
        const map = new mapboxgl.Map({
            container: 'map', // container ID
            style: 'mapbox://styles/mapbox/streets-v12', // style URL
            center: [lng, lat], // starting position [lng, lat]
            zoom: 9 // starting zoom
        });
        var geocoder = new MapboxGeocoder({
            accessToken: mapboxgl.accessToken,
            mapboxgl: mapboxgl
        });
    
        document.getElementById('geocoder').appendChild(geocoder.onAdd(map));
        geocoder.on('result', function (e) {
            var coordinates = e.result.center;
            var placeName = e.result.place_name;
        
            // Tạo một đối tượng chứa dữ liệu bạn muốn gửi về backend
            var data = {
                coordinates: coordinates,
                placeName: placeName
            };
        
            // Gửi dữ liệu lên backend bằng AJAX hoặc Fetch API
            fetch("/xe/getData")
    .then(response => response.json())
    .then(data => {
        console.log(data); // Lấy trường message từ JSON
        // Xử lý dữ liệu ở đây
    })
    .catch(error => {
        console.error("Lỗi:", error);
    });

        });
        
    } catch (error) {
        if (error.code === error.PERMISSION_DENIED) {
            console.error("User denied geolocation access. Provide an alternative experience.");
        } else {
            console.error("Error getting location: " + error.message);
        }
    }
}
maps();