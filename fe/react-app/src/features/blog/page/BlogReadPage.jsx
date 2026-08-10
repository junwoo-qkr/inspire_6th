import { useNavigate, useParams } from "react-router-dom";
import styled, {keyframes} from "styled-components";
import api from "../../../api/axios";
import { useEffect, useState } from "react";
import Button from "../../../components/styled/Button";
import TextInput from "../../../components/styled/TextInput";
import BlogCommentList from "../list/BlogCommentList";

// const Page = styled.main`
//     min-height: 100vh;
//     padding: 40px 20px;
//     box-sizing: border-box;
//     display: flex;
//     align-items: flex-start;
//     justify-content: center;
//     background: #f6f8fb;
// `;

// const ReadCard = styled.article`
//     width: 100%;
//     max-width: 800px;
//     padding: 40px;
//     box-sizing: border-box;
//     border: 1px solid #e4e8ee;
//     border-radius: 16px;
//     background: #ffffff;
//     box-shadow: 0 12px 32px rgba(32, 41, 56, 0.08);

//     @media (max-width: 600px) {
//         padding: 32px 24px;
//     }
// `;

// const Title = styled.h1`
//     margin: 0 0 24px;
//     padding-bottom: 24px;
//     border-bottom: 1px solid #e4e8ee;
//     color: #202938;
//     font-size: 30px;
// `;

// const ContentPlaceholder = styled.p`
//     margin: 0;
//     color: #697386;
//     font-size: 16px;
//     line-height: 1.8;
// `;

const Wrapper = styled.div`
    padding: 16px;
    width: calc(100% - 32px);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
`;

const Container = styled.div`
    width: 100%;
    max-width: 720px;

    & > * {
        :not(:last-child) {
            margin-bottom: 16px;
        }
    }
`;

const PostContainer = styled.div`
    padding: 8px 16px;
    border: 1px solid grey;
    border-radius: 8px;
`;

const TitleText = styled.p`
    font-size: 28px;
    font-weight: 500;
`;

const ContentText = styled.p`
    font-size: 20px;
    line-height: 32px;
    white-space: pre-wrap;
`;

const CommentLabel = styled.p`
    font-size: 16px;
    font-weight: 500;
`;

const spin = keyframes`
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
`;

const Spinner = styled.div`
  border: 6px solid #f3f3f3;
  border-top: 6px solid #3498db;
  border-radius: 50%;
  width: 48px;
  height: 48px;
  animation: ${spin} 1s linear infinite;
  margin: 100px auto;
`;

const WelcomeMessage = styled.div`
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 16px;
    color: #333;
`;

const BlogReadPage = () => {
    const {postId} = useParams();
    const user = localStorage.getItem("user");
    const [post, setPost] = useState({});
    const moveURL = useNavigate();
    const [comments, setComments] = useState([]);
    const [comment, setComment] = useState("");

    // 댓글이 없을 때
    // const loadData = async () => {
    //     await api.get(`/posts/${postId}`)
    //         .then(response => {
    //             // console.log(response);
    //             if (response.status === 200) {
    //                 setPost(response.data);
    //             }
    //         })
    //         .catch(err => {
    //             console.log(err);
    //         })
    // }

    // 댓글이 있을 때
    const loadData = async () => {
        await api.get(`/posts/${postId}?_embed=comments`)
            .then(response => {
                // console.log(response);
                if (response.status === 200) {
                    setPost(response.data);
                    setComments(response.data.comments);
                    // console.log(comments);
                }
            })
            .catch(err => {
                console.log(err);
            })
    }

    const commentHandler = async () => {
        await api.post("/comments", {
            comment,
            postId: Number(postId),
            commentEmail: user
        })
            .then(response => {
                console.log(response);
                if (response.status === 201) {
                    setComments(arr => [...arr, response.data]);
                    setComment("");
                }
            })
            .catch()
    }

    // status 204
    // 프론트에서는 삭제된 댓글과 동일한 아이디만 빼버리기
    const commentDeleteHandler = async (e, id) => {
        await api.delete(`/comments/${id}`)
            .then(response => {
                console.log(response);
                if (response.status === 200) {
                    setComments(comments.filter((c) => {
                        return c.id !== id
                    }))
                }
            })
            .catch()
    }

    const commentUpdateHandler = async (id, mention) => {
        // console.log(id, mention);
        await api.patch(`/comments/${id}`, {
            comment: mention
        })
            .then(response => {
                console.log(response)
                if (response.status === 200) {
                    setComments(arr => {
                        return arr.map(comment => {
                            return comment.id === id ? {...comment, comment : mention} : comment;
                        })
                    })
                }  
            })
            .catch()
    }

    useEffect(() => {
        loadData();
    }, []);

    return (
        <Wrapper>
            {!post.id && <Spinner />}
            {post.id && 
                <Container>
                    {user && <WelcomeMessage>Hi, {user}</WelcomeMessage>}
                    <Button title="메인 페이지로" onClick={() => moveURL("/blog/index")} />
                    
                    {/* post title, content */}
                    <PostContainer>
                        <TitleText>{post.title}</TitleText>
                        <ContentText>{post.content}</ContentText>
                    </PostContainer>

                    {/* comment list */}
                    <CommentLabel>작성된 댓글 목록</CommentLabel>
                    <BlogCommentList
                        comments={comments || []}
                        deletionHandler={commentDeleteHandler}
                        updateHandler={commentUpdateHandler}
                    />

                    {/* input comment, event */}
                    <TextInput height={14} value={comment} handler={(e) => setComment(e.target.value)} />
                    <Button title="댓글 작성" onClick={commentHandler} />
                </Container>
            }
        </Wrapper>
    );
};

export default BlogReadPage;
