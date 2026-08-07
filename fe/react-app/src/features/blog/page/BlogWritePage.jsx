import styled from "styled-components";
import Button from "../../../components/styled/Button";
import TextInput from "../../../components/styled/TextInput";
import { useNavigate } from "react-router-dom";

const Page = styled.main`
    min-height: 100vh;
    padding: 40px 20px;
    box-sizing: border-box;
    display: flex;
    align-items: center;
    justify-content: center;
    background: #f6f8fb;
`;

const WriteCard = styled.section`
    width: 100%;
    max-width: 720px;
    padding: 40px;
    box-sizing: border-box;
    border: 1px solid #e4e8ee;
    border-radius: 16px;
    background: #ffffff;
    box-shadow: 0 12px 32px rgba(32, 41, 56, 0.08);

    @media (max-width: 600px) {
        padding: 32px 24px;
    }
`;

const Header = styled.header`
    margin-bottom: 32px;
`;

const Title = styled.h1`
    margin: 0 0 8px;
    color: #202938;
    font-size: 30px;
`;

const WelcomeMessage = styled.p`
    margin: 0;
    color: #697386;
    font-size: 15px;
`;

const WriteForm = styled.div`
    display: flex;
    flex-direction: column;
    gap: 20px;
`;

const Field = styled.div`
    display: flex;
    flex-direction: column;
    gap: 8px;
`;

const FieldLabel = styled.label`
    color: #344054;
    font-size: 14px;
    font-weight: 600;
`;

const TextAreaField = styled.div`
    & > textarea {
        width: 100%;
        padding: 12px 14px;
        margin: 0;
        box-sizing: border-box;
        border: 1px solid #cfd6df;
        border-radius: 8px;
        color: #202938;
        font-size: 16px;
        line-height: 1.6;
        outline: none;
        resize: vertical;
        transition: border-color 0.2s, box-shadow 0.2s;
    }

    & > textarea:focus {
        border-color: #4f46e5;
        box-shadow: 0 0 0 3px rgba(79, 70, 229, 0.14);
    }
`;

const TitleInputArea = styled(TextAreaField)`
    & > textarea {
        height: 48px;
        min-height: 48px;
        resize: none;
    }
`;

const ContentInputArea = styled(TextAreaField)`
    & > textarea {
        min-height: 280px;
    }
`;

const CategoryWrapper = styled.div`
    display: flex;
    flex-direction: column;
    gap: 8px;
`;

const CategoryLabel = styled.div`
    color: #344054;
    font-size: 14px;
    font-weight: 600;
`;

const CategoryRow = styled.div`
    display: flex;
    align-items: center;
    gap: 10px;
    flex-wrap: wrap;
`;

const CategoryChip = styled.button`
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

const ActionArea = styled.div`
    display: flex;
    justify-content: flex-end;
    gap: 12px;
    margin-top: 4px;

    & > button {
        padding: 13px 20px;
        border: 0;
        border-radius: 8px;
        font-size: 16px;
        font-weight: 700;
        cursor: pointer;
        transition: background 0.2s, transform 0.2s;
    }

    & > button:first-child {
        background: #4f46e5;
        color: #ffffff;
    }

    & > button:first-child:hover {
        background: #4338ca;
    }

    & > button:last-child {
        border: 1px solid #cfd6df;
        background: #ffffff;
        color: #344054;
    }

    & > button:last-child:hover {
        background: #f6f8fb;
    }

    & > button:active {
        transform: translateY(1px);
    }

    @media (max-width: 480px) {
        & > button {
            flex: 1;
        }
    }
`;

const BlogWritePage = () => {
    const user = localStorage.getItem("user");
    const CATEGORIES = ["개발", "생활", "취미", "일상"];
    const moveURL = useNavigate();

    const previousHandler = (event) => {
        moveURL("/blog/index");
    }

    return (
        <Page>
            <WriteCard>
                <Header>
                    <Title>글 작성</Title>
                    {user && <WelcomeMessage>{user}님</WelcomeMessage>}
                </Header>

                <WriteForm>
                    <CategoryWrapper>
                        <CategoryLabel>카테고리</CategoryLabel>
                        <CategoryRow>
                            {CATEGORIES.map((cat, idx) => (
                                <CategoryChip key={idx} type="button">{cat}</CategoryChip>
                            ))}
                        </CategoryRow>
                    </CategoryWrapper>

                    <Field>
                        <FieldLabel>제목</FieldLabel>
                        <TitleInputArea>
                            <TextInput height={48} />
                        </TitleInputArea>
                    </Field>

                    <Field>
                        <FieldLabel>내용</FieldLabel>
                        <ContentInputArea>
                            <TextInput height={280} />
                        </ContentInputArea>
                    </Field>

                    <ActionArea>
                        <Button title="게시하기" />
                        <Button title="이전" onClick={(e) => previousHandler(e)} />
                    </ActionArea>
                </WriteForm>
            </WriteCard>
        </Page>
    );
};

export default BlogWritePage;
