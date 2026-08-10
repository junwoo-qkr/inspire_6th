import "../css/weather.css";

const WeatherBox = ({weather}) => {
    return (
        <div className="weather-box">
            <div className="weather-city">{weather.name}</div>
            <div className="weather-temp">{weather.main?.temp}</div>
            <div className="weather-desc">{weather.weather?.[0]?.description}</div>
        </div>
    );
}

export default WeatherBox;