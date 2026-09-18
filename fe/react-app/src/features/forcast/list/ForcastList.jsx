import { useLocation, useNavigate } from "react-router-dom";
import styled from "styled-components";

import ForcastItem from "../item/ForcastItem";

const Page = styled.main`
    min-height: 100vh;
    padding: 40px 20px;
    box-sizing: border-box;
    background: linear-gradient(135deg, #eff6ff 0%, #ecfeff 100%);
`;

const Container = styled.section`
    width: 100%;
    max-width: 1080px;
    margin: 0 auto;
`;

const Header = styled.header`
    margin-bottom: 24px;
    display: flex;
    align-items: flex-end;
    justify-content: space-between;
    gap: 16px;

    h1 {
        margin: 0 0 8px;
        color: #164e63;
        font-size: 30px;
    }

    p {
        margin: 0;
        color: #64748b;
    }

    button {
        padding: 11px 16px;
        border: 0;
        border-radius: 10px;
        background: #0891b2;
        color: #ffffff;
        font-size: 14px;
        font-weight: 700;
        cursor: pointer;
    }

    @media (max-width: 600px) {
        align-items: stretch;
        flex-direction: column;
    }
`;

const Grid = styled.div`
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
    gap: 16px;
`;

const EmptyMessage = styled.div`
    padding: 48px 24px;
    border: 1px solid #dbeafe;
    border-radius: 16px;
    background: #ffffff;
    color: #64748b;
    text-align: center;
    box-shadow: 0 12px 32px rgba(14, 116, 144, 0.08);
`;

const ForcastList = () => {
    const navigate = useNavigate();
    const { state } = useLocation();
    const forecasts = state?.forecasts ?? [];
    const request = state?.request;

    return (
        <Page>
            <Container>
                <Header>
                    <div>
                        <h1>예보 조회 결과</h1>
                        <p>
                            {request
                                ? `해변 ${request.beach_num} · ${request.base_date} ${request.base_time}`
                                : "조회 조건이 없습니다."}
                        </p>
                    </div>
                    <button type="button" onClick={() => navigate("/openapi/index")}>
                        다시 조회
                    </button>
                </Header>

                {forecasts.length > 0 ? (
                    <Grid>
                        {forecasts.map((forecast, index) => (
                            <ForcastItem
                                key={`${forecast.category}-${index}`}
                                forecast={forecast}
                            />
                        ))}
                    </Grid>
                ) : (
                    <EmptyMessage>표시할 예보 데이터가 없습니다. 다시 조회해 주세요.</EmptyMessage>
                )}
            </Container>
        </Page>
    );
};

export default ForcastList;
