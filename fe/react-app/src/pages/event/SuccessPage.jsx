import { Link } from "react-router-dom";
import { useLocation } from "react-router-dom";

const SuccessPage = () => {
    const location = useLocation();
    const {user, from} = location.state || {};
    const name = localStorage.getItem("userName");
    return (
        <div>
            <center>HI, {user.name} or {name}</center>&nbsp;&nbsp;&nbsp;
            {/* <a href="/">메인화면으로</a> */}
            <Link to="/read/1">상세페이지로</Link>&nbsp;&nbsp;&nbsp;
            <Link to="/">메인화면으로</Link>
        </div>
    )
}

export default SuccessPage;