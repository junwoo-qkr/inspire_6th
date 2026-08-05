import { Link } from "react-router-dom";
import { useSearchParams } from "react-router-dom";

const ErrorPage = () => {
    const [searchParams] = useSearchParams();
    const category = searchParams.get("category");
    const sort = searchParams.get("sort");
    return (
            <div>
                <center>Error({category}, {sort})</center>
                <Link to="/">메인화면으로</Link>
            </div>
        )
}

export default ErrorPage;