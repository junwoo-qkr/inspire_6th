import Button from "react-bootstrap/Button";
import "bootstrap/dist/css/bootstrap.min.css";
import { useEffect, useState } from "react";
import api from "../../api/axios";
import { useNavigate } from "react-router-dom";

const EventPage = () => {
    const [email, setEmail] = useState("");
    const [pswd, setPswd] = useState("");
    const moveURL = useNavigate();

    const signInHandler = async (e, email, pswd) => {
        e.preventDefault();
        await api.get(`/users?email=${email}&pswd=${pswd}`)  // 인증 과정
            .then(response => {
                console.log(`response:`, response);
                let result = response.data;
                if (result.length > 0) {
                    let user = result[0];
                    localStorage.setItem("userName", user.name);
                    moveURL("/success", {
                        state: {
                            user,
                            from: "SignIn"
                        }
                    });
                } else {
                    moveURL("/error?category=react&sort=latest");
                }
            })
            .catch(err => {
                console.log(`err: ${err}`);
            });
    }

    useEffect(() => {
        console.log(`email: ${email}`);
        console.log(`pswd: ${pswd}`);
    }, [email, pswd]);

    return(
        <div className='container'>
            <div class="mb-3 mt-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value={email} onChange={(e) => setEmail(e.target.value)}/>
            </div>
            <div class="mb-3">
                <label for="pwd" class="form-label">Password:</label>
                <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pswd" value={pswd} onChange={(e) => setPswd(e.target.value)} />
            </div>
            <div class="form-check mb-3">
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox" name="remember" /> Remember me
                </label>
            </div>
            <Button variant="primary" onClick={(e) => signInHandler(e, email, pswd)}>Sign In</Button>
        </div>
    )
}

export default EventPage;