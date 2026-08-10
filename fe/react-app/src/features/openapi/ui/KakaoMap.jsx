import { useEffect, useRef } from "react";

const KakaoMap = ({ setWeatherByCoords, moveTo }) => {
    const mapRef = useRef(null);
    const markerRef = useRef(null);

    useEffect(() => {
        window.kakao.maps.load(() => {
            navigator.geolocation.getCurrentPosition((position) => {
                let lat = position.coords.latitude;
                let lon = position.coords.longitude;
                
                const container = document.getElementById("map");

                const centerPosition = new window.kakao.maps.LatLng(37.5665, 126.9780);
                const map = new window.kakao.maps.Map(container, {
                    center : centerPosition,
                    level : 3 
                });
                const marker = new window.kakao.maps.Marker({
                    position : centerPosition
                });
                marker.setMap(map); 

                mapRef.current = map;
                markerRef.current = marker;

                window.kakao.maps.event.addListener(map, "click", function (mouseEvent) {
                const lat = mouseEvent.latLng.getLat();
                const lon = mouseEvent.latLng.getLng();

                marker.setPosition(new window.kakao.maps.LatLng(lat, lon));
                setWeatherByCoords(lat, lon);
                });
            });
        });
    }, []);
    
    useEffect(() => {
        if (!moveTo) return;
        if (!mapRef.current || !markerRef.current) return;

        const lat = parseFloat(moveTo.lat);
        const lon = parseFloat(moveTo.lon);

        const position = new window.kakao.maps.LatLng(lat, lon);

        console.log("지도 이동:", lat, lon);

        mapRef.current.setCenter(position);
        markerRef.current.setPosition(position);
    }, [moveTo]);

    return (
        <div id="map" style={ {width: "100%", height: "400px"} }></div>
    );
}

export default KakaoMap;