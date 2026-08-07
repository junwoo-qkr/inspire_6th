import styled from "styled-components";

import Button from "../../../components/styled/Button";
import BlogList from "../list/BlogList";
import { useEffect, useMemo, useState } from "react";
import api from "../../../api/axios";
import { useNavigate } from "react-router-dom";

const Page = styled.main`
    min-height: 100vh;
    padding: 40px 20px;
    box-sizing: border-box;
    display: flex;
    justify-content: center;
    background: #f6f8fb;
`;

const PageContainer = styled.div`
    width: 100%;
    max-width: 960px;
    display: flex;
    flex-direction: column;
    gap: 24px;
`;

const Header = styled.header`
    display: flex;
    align-items: flex-end;
    justify-content: space-between;
    gap: 24px;
    padding: 32px;
    box-sizing: border-box;
    border: 1px solid #e4e8ee;
    border-radius: 16px;
    background: #ffffff;
    box-shadow: 0 12px 32px rgba(32, 41, 56, 0.08);

    @media (max-width: 600px) {
        align-items: stretch;
        flex-direction: column;
        padding: 24px;
    }
`;

const HeaderContent = styled.div`
    display: flex;
    flex-direction: column;
    gap: 8px;
`;

const Title = styled.h1`
    margin: 0;
    color: #202938;
    font-size: 30px;
    line-height: 1.3;
`;

const WelcomeMessage = styled.p`
    margin: 0;
    color: #697386;
    font-size: 15px;
`;

const WriteButtonArea = styled.div`
    & > button {
        padding: 13px 20px;
        border: 0;
        border-radius: 8px;
        background: #4f46e5;
        color: #ffffff;
        font-size: 16px;
        font-weight: 700;
        cursor: pointer;
        transition: background 0.2s, transform 0.2s;
    }

    & > button:hover {
        background: #4338ca;
    }

    & > button:active {
        transform: translateY(1px);
    }

    @media (max-width: 600px) {
        & > button {
            width: 100%;
        }
    }
`;

const CategoryFilter = styled.nav`
    display: flex;
    align-items: center;
    gap: 10px;
    padding: 20px;
    border: 1px solid #e4e8ee;
    border-radius: 16px;
    background: #ffffff;
    flex-wrap: wrap;
    box-shadow: 0 8px 24px rgba(32, 41, 56, 0.06);
`;

const FilterButton = styled.button`
    padding: 9px 16px;
    border: 1px solid ${({ $active }) => ($active ? "#4f46e5" : "#cfd6df")};
    border-radius: 999px;
    background: ${({ $active }) => ($active ? "#4f46e5" : "#ffffff")};
    color: ${({ $active }) => ($active ? "#ffffff" : "#697386")};
    font-size: 14px;
    font-weight: 600;
    cursor: pointer;
    transition: border-color 0.2s, background 0.2s, color 0.2s;

    &:hover {
        border-color: #4f46e5;
        color: ${({ $active }) => ($active ? "#ffffff" : "#4f46e5")};
    }

    &:focus-visible {
        outline: 3px solid rgba(79, 70, 229, 0.2);
        outline-offset: 2px;
    }
`;

const BlogIndexPage = () => {
    const user = localStorage.getItem("user");
    const [posts, setPosts] = useState([]);
    const CATEGORIES = ["전체", "개발", "생활", "취미", "일상"]
    const [selectedCategory, setSelectedCategory] = useState("전체");
    // const filteredPosts = selectedCategory === "전체" ? posts : posts.filter((post) => post.category === selectedCategory);
    const filteredPosts = useMemo(() => {
        return selectedCategory === "전체" ? posts : posts.filter((post) => post.category === selectedCategory);
    }, []);

    const loadData = async () => {
        await api.get("/posts")
            .then(response => {
                console.log(response);
                if (response.status === 200) {
                    setPosts(response.data);
                }
            })
            .catch(
                console.log("err")
            )
    }

    useEffect(() => {
        loadData();
    }, []);

    const moveURL = useNavigate();

    const writeHandler = (e) => {
        moveURL("/blog/write");
    }

    return (
        <Page>
            <PageContainer>
                <Header>
                    <HeaderContent>
                        <Title>블로그</Title>
                        {user && <WelcomeMessage>{user}님 환영합니다.</WelcomeMessage>}
                    </HeaderContent>
                    <WriteButtonArea>
                        <Button title="글쓰기" onClick={(event) => writeHandler(event)} />
                    </WriteButtonArea>
                </Header>
                <CategoryFilter aria-label="게시글 카테고리 필터">
                    {CATEGORIES.map((value, idx) => (
                        <FilterButton
                            type="button"
                            $active={value === selectedCategory}
                            onClick={() => setSelectedCategory(value)}
                        >{value}</FilterButton>
                    ))}
                </CategoryFilter>
                <BlogList posts={posts || []} />
            </PageContainer>
        </Page>
    );
};

export default BlogIndexPage;
