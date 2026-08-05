import { useParams } from "react-router-dom";

const ViewPage = () => {
    const params = useParams();
    console.log(`id = ${params.id}`);
}

export default ViewPage;