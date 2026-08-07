import styled from "styled-components";

const Page = styled.main`
    min-height: 100vh;
    padding: 40px 20px;
    box-sizing: border-box;
    display: flex;
    align-items: flex-start;
    justify-content: center;
    background: #f6f8fb;
`;

const ReadCard = styled.article`
    width: 100%;
    max-width: 800px;
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

const Title = styled.h1`
    margin: 0 0 24px;
    padding-bottom: 24px;
    border-bottom: 1px solid #e4e8ee;
    color: #202938;
    font-size: 30px;
`;

const ContentPlaceholder = styled.p`
    margin: 0;
    color: #697386;
    font-size: 16px;
    line-height: 1.8;
`;

const BlogReadPage = () => {
    return (
        <Page>
            <ReadCard>
                <Title>게시글 상세</Title>
                <ContentPlaceholder>게시글 내용이 표시되는 영역입니다.</ContentPlaceholder>
            </ReadCard>
        </Page>
    );
};

export default BlogReadPage;
