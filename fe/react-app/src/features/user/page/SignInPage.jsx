import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import styled from "styled-components";
import api from "../../../api/axios";

const Page = styled.main`
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 40px 20px;
    box-sizing: border-box;
    background: #f6f8fb;
`;

const SignInCard = styled.section`
    width: 100%;
    max-width: 420px;
    padding: 40px;
    box-sizing: border-box;
    border: 1px solid #e4e8ee;
    border-radius: 16px;
    background: #ffffff;
    box-shadow: 0 12px 32px rgba(32, 41, 56, 0.08);

    @media (max-width: 480px) {
        padding: 32px 24px;
    }
`;

const Title = styled.h1`
    margin: 0 0 8px;
    color: #202938;
    font-size: 30px;
    text-align: center;
`;

const Form = styled.form`
    display: flex;
    flex-direction: column;
    gap: 20px;
`;

const Field = styled.div`
    display: flex;
    flex-direction: column;
    gap: 8px;
`;

const Label = styled.label`
    color: #344054;
    font-size: 14px;
    font-weight: 600;
`;

const Input = styled.input`
    width: 100%;
    padding: 12px 14px;
    box-sizing: border-box;
    border: 1px solid #cfd6df;
    border-radius: 8px;
    color: #202938;
    font-size: 16px;
    outline: none;
    transition: border-color 0.2s, box-shadow 0.2s;

    &::placeholder {
        color: #9aa4b2;
    }

    &:focus {
        border-color: #4f46e5;
        box-shadow: 0 0 0 3px rgba(79, 70, 229, 0.14);
    }
`;

const SubmitButton = styled.button`
    margin-top: 4px;
    padding: 13px 16px;
    border: 0;
    border-radius: 8px;
    background: #4f46e5;
    color: #ffffff;
    font-size: 16px;
    font-weight: 700;
    cursor: pointer;
    transition: background 0.2s, transform 0.2s;

    &:hover {
        background: #4338ca;
    }

    &:active {
        transform: translateY(1px);
    }
`;

const SignInPage = () => {
    const [formData, setFormData] = useState({
        email: "",
        password: "",
    });

    const handleChange = (event) => {
        const { name, value } = event.target;

        setFormData((previousFormData) => ({
            ...previousFormData,
            [name]: value,
        }));
    };

    const moveURL = useNavigate();

    // TODO
    // header access token에 접근
    // 인증과 인가
    // const handleSubmit = async (event) => {
    //     event.preventDefault();
    //     await api.get("/user", {
    //         params: {
    //             email: formData.email,
    //         }})
    //         .then(response => {
    //             if (response.status === 200 && response.data[0].password === formData.password) {
    //                 localStorage.setItem("user", response.data[0].email);
    //                 moveURL("/blog/index");
    //             }
    //         })
    //         .catch(err => {
    //             console.log(`err:`, err);
    //         })
    // };

    const handleSubmit = async (event) => {
        event.preventDefault();
        // await api.get("/user/signIn", {
        //     params: {
        //         email: formData.email,
        //         password: formData.password
        //     }})
        await api.get(`/user/signIn?email=${formData.email}&password=${formData.password}`)
            .then(response => {
                console.log(response);
                if (response.status === 200) {
                    localStorage.setItem("user", response.data.email);
                    localStorage.setItem("at", response.headers.get("Authorization"));
                    localStorage.setItem("rt", response.headers.get("Refresh-Token"));
                }
                // if (response.status === 200 && response.data[0].password === formData.password) {
                    // localStorage.setItem("user", response.data[0].email);
                    // moveURL("/blog/index");
                // }
            })
            .catch(err => {
                console.log(`err:`, err);
            })
    };

    return (
        <Page>
            <SignInCard>
                <Title>로그인</Title>

                <Form onSubmit={handleSubmit}>
                    <Field>
                        <Label htmlFor="email">이메일</Label>
                        <Input
                            id="email"
                            name="email"
                            type="email"
                            placeholder="example@email.com"
                            value={formData.email}
                            onChange={handleChange}
                            autoComplete="email"
                            required
                        />
                    </Field>

                    <Field>
                        <Label htmlFor="password">비밀번호</Label>
                        <Input
                            id="password"
                            name="password"
                            type="password"
                            placeholder="비밀번호를 입력하세요"
                            value={formData.password}
                            onChange={handleChange}
                            autoComplete="off"
                            required
                        />
                    </Field>

                    <SubmitButton type="submit">로그인</SubmitButton>
                </Form>
            </SignInCard>
        </Page>
    );
};

export default SignInPage;
