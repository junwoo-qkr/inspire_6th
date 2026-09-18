import { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

import api from "../../../api/axios";

const Page = styled.main`
    min-height: 100vh;
    padding: 40px 20px;
    box-sizing: border-box;
    display: flex;
    justify-content: center;
    background: linear-gradient(135deg, #eff6ff 0%, #ecfeff 100%);
`;

const Form = styled.form`
    width: 100%;
    max-width: 560px;
    height: fit-content;
    padding: 36px;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    gap: 24px;
    border: 1px solid #dbeafe;
    border-radius: 20px;
    background: #ffffff;
    box-shadow: 0 20px 48px rgba(14, 116, 144, 0.12);
`;

const Header = styled.header`
    display: flex;
    flex-direction: column;
    gap: 8px;
`;

const Title = styled.h1`
    margin: 0;
    color: #164e63;
    font-size: 30px;
`;

const Description = styled.p`
    margin: 0;
    color: #64748b;
    line-height: 1.6;
`;

const Field = styled.label`
    display: flex;
    flex-direction: column;
    gap: 8px;
    color: #334155;
    font-size: 14px;
    font-weight: 700;

    input {
        width: 100%;
        padding: 13px 14px;
        box-sizing: border-box;
        border: 1px solid #cbd5e1;
        border-radius: 10px;
        color: #0f172a;
        font: inherit;
        font-weight: 400;
    }

    input:focus {
        border-color: #0891b2;
        outline: 3px solid rgba(8, 145, 178, 0.15);
    }
`;

const Hint = styled.span`
    color: #64748b;
    font-size: 12px;
    font-weight: 400;
`;

const ErrorMessage = styled.p`
    margin: 0;
    padding: 12px 14px;
    border-radius: 10px;
    background: #fef2f2;
    color: #b91c1c;
    font-size: 14px;
`;

const Actions = styled.div`
    display: flex;
    gap: 12px;

    button {
        flex: 1;
        padding: 13px 18px;
        border: 0;
        border-radius: 10px;
        font-size: 15px;
        font-weight: 700;
        cursor: pointer;
    }

    button[type="button"] {
        background: #e2e8f0;
        color: #334155;
    }

    button[type="submit"] {
        background: #0891b2;
        color: #ffffff;
    }

    button:disabled {
        cursor: wait;
        opacity: 0.65;
    }
`;

const ForcastPage = () => {
    const navigate = useNavigate();
    const [form, setForm] = useState({
        base_date: "",
        base_time: "",
        beach_num: "",
    });
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const changeHandler = ({ target }) => {
        setForm((current) => ({ ...current, [target.name]: target.value }));
    };

    const submitHandler = async (event) => {
        event.preventDefault();
        setLoading(true);
        setError("");

        const request = {
            beach_num: form.beach_num,
            base_date: form.base_date.replaceAll("-", ""),
            base_time: form.base_time.replace(":", ""),
        };

        try {
            const response = await api.post("/openapi/fcst", request);
            navigate("/openapi/result", {
                state: {
                    forecasts: Array.isArray(response.data) ? response.data : [],
                    request,
                },
            });
        } catch (err) {
            console.error(err);
            setError("예보 정보를 불러오지 못했습니다. 입력값을 확인해 주세요.");
        } finally {
            setLoading(false);
        }
    };

    return (
        <Page>
            <Form onSubmit={submitHandler}>
                <Header>
                    <Title>해수욕장 기상예보</Title>
                    <Description>조회할 해변과 예보 기준 날짜·시간을 입력해 주세요.</Description>
                </Header>

                <Field>
                    해변 코드
                    <input
                        type="number"
                        name="beach_num"
                        min="1"
                        value={form.beach_num}
                        onChange={changeHandler}
                        placeholder="예: 1"
                        required
                    />
                    <Hint>공공데이터포털의 해변코드 값을 입력합니다.</Hint>
                </Field>

                <Field>
                    예보 날짜
                    <input
                        type="date"
                        name="base_date"
                        value={form.base_date}
                        onChange={changeHandler}
                        required
                    />
                </Field>

                <Field>
                    예보 시간
                    <input
                        type="time"
                        name="base_time"
                        value={form.base_time}
                        onChange={changeHandler}
                        required
                    />
                    <Hint>API 발표 시각에 맞는 시간을 선택합니다.</Hint>
                </Field>

                {error && <ErrorMessage role="alert">{error}</ErrorMessage>}

                <Actions>
                    <button type="button" onClick={() => navigate("/blog/index")}>
                        돌아가기
                    </button>
                    <button type="submit" disabled={loading}>
                        {loading ? "조회 중..." : "예보 조회"}
                    </button>
                </Actions>
            </Form>
        </Page>
    );
};

export default ForcastPage;
