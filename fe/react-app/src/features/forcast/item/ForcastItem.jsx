import styled from "styled-components";

const Card = styled.article`
    min-height: 140px;
    padding: 24px;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    gap: 20px;
    border: 1px solid #dbeafe;
    border-radius: 16px;
    background: #ffffff;
    box-shadow: 0 12px 32px rgba(14, 116, 144, 0.08);
    transition: transform 0.2s, box-shadow 0.2s;

    &:hover {
        box-shadow: 0 16px 36px rgba(14, 116, 144, 0.14);
        transform: translateY(-2px);
    }
`;

const Category = styled.span`
    color: #0891b2;
    font-size: 14px;
    font-weight: 700;
`;

const Value = styled.strong`
    color: #0f172a;
    font-size: 28px;
    line-height: 1.2;
    word-break: break-word;
`;

const Code = styled.span`
    color: #94a3b8;
    font-size: 12px;
`;

const ForcastItem = ({ forecast }) => {
    return (
        <Card>
            <Category>{forecast.categoryName || forecast.category}</Category>
            <Value>{forecast.fcstValue}</Value>
            {forecast.categoryName && <Code>{forecast.category}</Code>}
        </Card>
    );
};

export default ForcastItem;
