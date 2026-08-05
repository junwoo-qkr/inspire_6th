import api from "../../api/axios.js";
import Comment from "../../components/sample/Comment.jsx";
import { useEffect, useState } from "react";

export const CommentPage = () => {
    // Script
    let [comments, setComments] = useState([]);

    const loadData = async () => {
        await api.get("/comment")
            .then(response => {
                console.log(`response = ${response}`);
                setComments(response.data);
            })
            .catch(err => {
                console.log(`error = ${err}`);
            });
    }

    useEffect(() => {
        loadData();
    }, []);

    // UI
    return (
        <div>
            {
                comments?.map((comment, idx) => {
                    return <Comment key={idx} data={comment} />
                }) ?? []
            }
        </div>
    )

}