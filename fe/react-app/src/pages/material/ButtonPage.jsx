import MaterialButton from "../../components/material/MaterialButton";

const ButtonPage = () => {
    const saveHandler = () => {
        window.alert("save button clicked");
    }

    const listHandler = () => {
        window.alert("list button clicked");
    }


    return (
        <div>
            <MaterialButton title="글 작성하기" onclick={(e) => saveHandler()} />
            <MaterialButton title="글 목록보기" onclick={(e) => listHandler()} />
        </div>
    );
}

export default ButtonPage;