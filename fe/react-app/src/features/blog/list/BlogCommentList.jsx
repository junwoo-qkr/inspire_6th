import styled from "styled-components";
import BlogCommentItem from "../item/BlogCommentItem";

const Wrapper = styled.div`
    width: 100%;
    display: flex;
    flex-direction: column;
    gap: 16px;
`;

const BlogCommentList = ({comments, deletionHandler}) => {
    return (
        <Wrapper>
            {
                comments.map((comment, idx) => {
                    return <BlogCommentItem key={idx} comment={comment} deletionHandler={deletionHandler} />
                })
            }
        </Wrapper>
    );
}

export default BlogCommentList;
