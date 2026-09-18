import { BrowserRouter, Route, Routes } from "react-router-dom";
import SignUpPage from "./features/user/page/SignUpPage";
import SignInPage from "./features/user/page/SignInPage";
import BlogIndexPage from "./features/blog/page/BlogIndexPage";
import BlogWritePage from "./features/blog/page/BlogWritePage";
import BlogReadPage from "./features/blog/page/BlogReadPage";
import ForcastPage from "./features/forcast/page/ForcastPage";
import ForcastList from "./features/forcast/list/ForcastList";

const ToyApp = () => {
    return (
        <BrowserRouter>
            <Routes>
                {/* user */}
                <Route path="/" element={<SignUpPage />} />
                <Route path="/user/signIn" element={<SignInPage />} />

                {/* blog */}
                <Route path="/blog/index" element={<BlogIndexPage />} />
                <Route path="/blog/write" element={<BlogWritePage />} />
                <Route path="/blog/read/:postId" element={<BlogReadPage />} />

                {/* openAPI */}
                <Route path="/openapi/index" element={<ForcastPage />} />
                <Route path="/openapi/result" element={<ForcastList />} />
            </Routes>
        </BrowserRouter>
    )
}

export default ToyApp;
