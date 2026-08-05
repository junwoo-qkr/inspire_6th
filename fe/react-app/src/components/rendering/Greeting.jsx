import UserGreeting from "./UserGreeting";
import GuestGreeting from "./GuestGreeting";

const Greeting = (props) => {
    {
        return props.flag ? <UserGreeting /> : <GuestGreeting />
    }
}

export default Greeting;