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
        // Create a default Marker and add it to the map.
        const marker = new mapboxgl.Marker()
            .setLngLat([lng, lat])
            .addTo(map);
        const marker1 = new mapboxgl.Marker()
            .setLngLat([106.89833, 11.53665])
            .addTo(map);
        const marker2 = new mapboxgl.Marker()
            .setLngLat([107.22226, 11.81162])
            .addTo(map);
        const marker3 = new mapboxgl.Marker()
            .setLngLat([107.46565, 11.94731])
            .addTo(map);
        var directions = new MapboxDirections({
            accessToken: mapboxgl.accessToken,
            width: 100,
            hight: 100
        });
        map.addControl(directions);

        map.on('load', function () {
            directions.setOrigin([107.51303, 11.99674]); // can be address in form setOrigin("12, Elm Street, NY")
            directions.setDestination([106.71045, 10.81508]); // can be address
        })
    } catch (error) {
        if (error.code === error.PERMISSION_DENIED) {
            console.error("User denied geolocation access. Provide an alternative experience.");
        } else {
            console.error("Error getting location: " + error.message);
        }
    }
}
maps();