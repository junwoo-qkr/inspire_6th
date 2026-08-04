import Button from "@mui/material/Button";

const MaterialButton = (props) => {
    return (
        <Button onClick={props.onclick}>{props.title}</Button>
    );
}

export default MaterialButton;