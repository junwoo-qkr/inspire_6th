import api from "../../api/axios.js";
import Comment from "../../components/sample/Comment.jsx";

export const CommentPage = () => {
    // Script
    let comments = [{
            "writer": "임정섭",
            "comment": "강사님과 함께하는 즐거운 React..."
        },
        {
            "writer": "차현준",
            "comment": "강사님과 함께하는 재미없는 React..."
        },
        {
            "writer": "박선아",
            "comment": "강사님과 함께하는 시시한 React..."
        }];

    // const loadData = async () => {
    //     await api.get("/comment")
    //         .then(response => {
    //             console.log(`response = ${response}`);
    //             comments = response;
    //         })
    //         .catch(err => {
    //             console.log(`error = ${err}`);
    //         });
    // }
    // loadData();

    // UI
    return (
        <div>
            {
                comments.map((comment, idx) => {
                    return <Comment key={idx} data={comment} />
                })
            }
        </div>
    )

}