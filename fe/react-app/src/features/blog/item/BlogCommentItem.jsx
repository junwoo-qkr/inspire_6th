import styled from "styled-components";
import Button from "../../../components/styled/Button";
import TextInput from "../../../components/styled/TextInput";
import { useEffect, useState } from "react";

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

const CommentText = styled.p`
    font-size: 16px;
    white-space: pre-wrap;
`;

const BlogCommentItem = ({comment, deletionHandler, updateHandler}) => {
    const user = localStorage.getItem("user");
    const [isEdit, setIsEdit] = useState(false);
    const [mention, setMention] = useState("");

    const updateMentionHandler = (e) => {
        if (!isEdit) {
            setIsEdit(true);
        } else {
            updateHandler(comment.id, mention);
            setIsEdit(false);
        }
    }

    useEffect(() => {
        setMention(comment.comment);
    }, []);

    return (
        <Wrapper>
            {
                isEdit ? 
                    <TextInput
                        height={16} 
                        value={mention} 
                        handler={(e) => setMention(e.target.value)}
                        disabled={!isEdit}    
                    />
                :
                    <CommentText>{mention}</CommentText>
            }
            {
                user === comment.commentEmail &&
                <Button
                    title="삭제"
                    onClick={(e) => deletionHandler(e, comment.id)}
                />
            }
            {
                <Button
                    title={isEdit ? "수정완료" : "수정"}
                    onClick={(e) => updateMentionHandler(e)}
                />
            }
            
        </Wrapper>
    )
}

export default BlogCommentItem;
