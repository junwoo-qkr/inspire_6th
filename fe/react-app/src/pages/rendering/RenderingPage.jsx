import { useState } from "react";
import Greeting from "../../components/rendering/Greeting";
import LoginButton from "../../components/rendering/LoginButton";
import LogoutButton from "../../components/rendering/LogoutButton";

const RenderingPage = () => {
    const [flag, setFlag] = useState(false);

    return (
        <div>
            <Greeting flag={flag} />
            {
                flag ? <LogoutButton isLogin={setFlag}/> : <LoginButton isLogin={setFlag}/>
            }
        </div>
    )
}

export default RenderingPage;