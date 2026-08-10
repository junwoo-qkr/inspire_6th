import { useEffect, useState } from "react";
import "../css/weather.css";
import WeatherBox from "../ui/WeatherBox";
import WeatherButton from "../ui/WeatherButton";
import KakaoMap from "../ui/KakaoMap";

const WeatherPage = () => {
    const key = process.env.REACT_APP_WEATHER_API_KEY;
    const cities = ["서울", "부산", "대전", "인천"];

    const [city, setCity] = useState("");
    const [weather, setWeather] = useState({});
    const [moveTo, setMoveTo] = useState({});
    
    const getCurrentLocation = () => {
        navigator.geolocation.getCurrentPosition((pos) => {
            let lat = pos.coords.latitude;
            let lon = pos.coords.longitude;
            getCurrentWeather(lat, lon);
        })
    }

    const getCurrentWeather = async (lat, lon) => {
        let endPoint = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&units=metric&appid=${key}`;
        await fetch(endPoint)
        .then(response => {
            return response.json();
        })
        .then(data => {
            setWeather(data);
            return data;
        })
        .catch()
    }
    useEffect(() => {
        getCurrentLocation();
    }, []);

    const getCityWeather = async (city) => {
        let endPoint = `https://api.openweathermap.org/data/2.5/weather?q=${city}&units=metric&appid=${key}`;
        await fetch(endPoint)
        .then(response => {
            return response.json();
        })
        .then(data => {
            setWeather(data);
            return data;
        })
        .catch(err => console.log(err))
    }

    const getWeatherByCoords = async (lat, lon) => {
        console.log(`debug >>>> getWeatherByCoords lat, lon : ${lat}, ${lon} `);
        let endPoint = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&units=metric&appid=${key}`;
        await fetch(endPoint)
            .then( response => {
                console.log(`debug >>>> fetch response ` , response); 
                return response.json() ; 
            })
            .then( data => {
                console.log(`debug >>>> fetch response data ` , data);  
                setWeather(data);
            })
            .catch( error => {
                console.log(`debug >>>> fetch error ` , error);  
            }) ;
    };

    const getCoordsByCity = (cityname) => {
        const geocoder = new window.kakao.maps.services.Geocoder();
        geocoder.addressSearch(cityname, (result, status) => {
            if (status === window.kakao.maps.services.Status.OK) {
                console.log(`result =`, result);
                const lat = parseFloat(result[0].y);
                const lon = parseFloat(result[0].x);
                setMoveTo({lat, lon, time: Date.now()});
                getCurrentWeather(lat, lon);
            } else {
                console.log("failed");
            }
        });
    }

    useEffect(() => {
        if (city !== "") {
            getCityWeather(city);
        }
    }, [city]);

    const cityHandler = (e, city) => {
        setCity(city);
        getCoordsByCity(city);
    }

    return (
        <div className="container">
            <KakaoMap setWeatherByCoords={getWeatherByCoords} moveTo={moveTo} />
            <WeatherBox weather={weather}/>
            <WeatherButton 
                cities={cities}
                city={city}
                handler={cityHandler}
            />
        </div>
    );
}

export default WeatherPage;
