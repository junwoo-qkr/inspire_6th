import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const Wrapper = styled.article`
    width: 100%;
    padding: 24px;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: center;
    border: 1px solid #e4e8ee;
    border-radius: 16px;
    cursor: pointer;
    background: #ffffff;
    box-shadow: 0 8px 24px rgba(32, 41, 56, 0.06);
    transition: border-color 0.2s, box-shadow 0.2s, transform 0.2s;

    &:hover {
        border-color: #cfd6df;
        box-shadow: 0 12px 32px rgba(32, 41, 56, 0.1);
        transform: translateY(-2px);
    }
`;

const TitleText = styled.h2`
    margin: 0;
    color: #202938;
    font-size: 20px;
    font-weight: 700;
    line-height: 1.4;
`;

const CategoryBadge = styled.span`
    display: inline-flex;
    align-items: center;
    height: 24px;
    padding: 0 10px;
    margin-bottom: 8px;
    border-radius: 999px;
    background: #eef2ff;
    color: #6366f1;
    font-size: 12px;
    font-weight: 600;
    line-height: 1;
`;

const BlogItem = ({post}) => {
    const moveURL = useNavigate();

    return (
        <Wrapper onClick={(e) => {moveURL(`/blog/read/${post.id}`);}}>
            {post.category && <CategoryBadge>{post.category}</CategoryBadge>}
            <TitleText>{post.title}</TitleText>
        </Wrapper>
    );
};

export default BlogItem;
