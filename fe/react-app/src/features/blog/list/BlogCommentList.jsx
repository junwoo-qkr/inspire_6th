import styled from "styled-components";
import BlogCommentItem from "../item/BlogCommentItem";

const Wrapper = styled.div`
    width: 100%;
    display: flex;
    flex-direction: column;
    gap: 16px;
`;

const BlogCommentList = ({comments, deletionHandler, updateHandler}) => {
    return (
        <Wrapper>
            {
                comments.map((comment, idx) => {
                    return <BlogCommentItem
                        // ket={idx} 
                        key={comment.id}
                        comment={comment}
                        deletionHandler={deletionHandler}
                        updateHandler={updateHandler}
                    />
                })
            }
        </Wrapper>
    );
}

export default BlogCommentList;
