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

const SignUpCard = styled.section`
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

const SignInLink = styled(Link)`
    display: block;
    width: fit-content;
    margin: 20px auto 0;
    border-radius: 4px;
    color: #4f46e5;
    font-size: 14px;
    font-weight: 600;
    text-decoration: none;
    transition: color 0.2s;

    &:hover {
        color: #4338ca;
        text-decoration: underline;
    }

    &:focus-visible {
        outline: 3px solid rgba(79, 70, 229, 0.25);
        outline-offset: 3px;
    }
`;

const SignUpPage = () => {
    const [formData, setFormData] = useState({
        name: "",
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

    // const handleSubmit = async (event) => {
    //     event.preventDefault();
    //     await api.post("/users", formData)
    //         .then(response => {
    //             if (response.status === 201) {
    //                 // console.log(response);
    //                 moveURL("/users/signIn");
    //             }
    //         })
    //         .catch(err => {
    //             console.log(`err:`, err);
    //         })
    // };

    const handleSubmit = async (event) => {
        event.preventDefault();
        await api.post("/user/signUp", formData)
            .then(response => {
                if (response.status === 201) {
                    // console.log(response);
                    moveURL("/user/signIn");
                }
            })
            .catch(err => {
                console.log(`err:`, err);
            })
    };

    return (
        <Page>
            <SignUpCard>
                <Title>회원가입</Title>

                <Form onSubmit={handleSubmit}>
                    <Field>
                        <Label htmlFor="name">이름</Label>
                        <Input
                            id="name"
                            name="name"
                            type="text"
                            placeholder="이름을 입력하세요"
                            value={formData.name}
                            onChange={handleChange}
                            autoComplete="name"
                            required
                        />
                    </Field>

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
                            autoComplete="new-password"
                            minLength={8}
                            required
                        />
                    </Field>

                    <SubmitButton type="submit">가입하기</SubmitButton>
                </Form>
                <SignInLink to="/user/signIn">로그인 하러가기</SignInLink>
            </SignUpCard>
        </Page>
    );
};

export default SignUpPage;
