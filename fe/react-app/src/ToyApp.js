import { BrowserRouter, Route, Routes } from "react-router-dom";
import SignUpPage from "./features/user/page/SignUpPage";
import SignInPage from "./features/user/page/SignInPage";
import BlogIndexPage from "./features/blog/page/BlogIndexPage";
import BlogWritePage from "./features/blog/page/BlogWritePage";

const ToyApp = () => {
    return (
        <BrowserRouter>
            <Routes>
                {/* user */}
                <Route path="/" element={<SignUpPage />} />
                <Route path="/users/signIn" element={<SignInPage />} />

                {/* blog */}
                <Route path="/blog/index" element={<BlogIndexPage />} />
                <Route path="/blog/write" element={<BlogWritePage />} />
                <Route path="/blog/read/:postId" element={<BlogIndexPage />} />
                
                {/* comment */}
            </Routes>
        </BrowserRouter>
    )
}

export default ToyApp;