import Button from "../../components/styled/Button";
import { useState, useEffect } from "react";

const CapacityPage = () => {
    // let cnt = 0;  -> state 관리가 안되는 변수
    let [cnt, setCnt] = useState(0);

    const increaseHandler = (e) => {
        // cnt++;
        setCnt(cnt => cnt + 1);
        // console.log(`increased, cnt = ${cnt}`);
    }

    const decreaseHandler = (e) => {
        // cnt--;
        setCnt(cnt => cnt - 1);
        // console.log(`decreased, cnt = ${cnt}`);
    }

    // side effect로 렌더링 이후 작업을 명시
    useEffect(() => {
        console.log("updated");
        console.log(`입장 / 퇴장, cnt = ${cnt}`);
    }, [cnt]);

    return (
        <div>
            <p>입장인원: {cnt}</p>
            <Button title="입장" onclick={(e) => increaseHandler()} />
            <Button title="퇴장" onclick={(e) => decreaseHandler()} />
        </div>
    );
}

export default CapacityPage;