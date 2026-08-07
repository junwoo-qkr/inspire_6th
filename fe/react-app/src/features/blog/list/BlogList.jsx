import styled from "styled-components";
import BlogItem from "../item/BlogItem";

const Wrapper = styled.div`
    width: 100%;
    display: flex;
    flex-direction: column;
    gap: 16px;
`;

const EmptyMessage = styled.p`
    margin: 0;
    padding: 40px 24px;
    border: 1px solid #e4e8ee;
    border-radius: 16px;
    background: #ffffff;
    color: #697386;
    font-size: 15px;
    text-align: center;
    box-shadow: 0 8px 24px rgba(32, 41, 56, 0.06);
`;

const BlogList = (props) => {
    return (
        <Wrapper>
            {props.posts && props.posts.length > 0 ? (
                props.posts.map((post, idx) => (
                    <BlogItem key={idx} post={post} />
                ))
            ) : (
                <EmptyMessage>등록된 글이 없습니다.</EmptyMessage>
            )}
        </Wrapper>
    );
};

export default BlogList;
