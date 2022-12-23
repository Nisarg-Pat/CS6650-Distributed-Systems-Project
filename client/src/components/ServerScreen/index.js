import NavBar from "../NavBar";
import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import React, {useState} from "react";
import {setServer} from "../../data/server/server-reducer";


const NewBookScreen = () => {
    const {server} = useSelector((state)=> state.server);

    const [host, setHost] = useState(server.host);
    const [port, setPort] = useState(server.port);


    const dispatch = useDispatch();
    const navigate = useNavigate();

    const onAddBtnClick = async () => {
        //const newBook = {name, userId: currentUser.id, status: "shelf", sellPrice: 0.00};
        await dispatch(setServer({host, port, url: `http://${host}:${port}`}));
        navigate("/home");
    }

    return (
        <div className={"row"}>
            <NavBar/>
            <div className={"ds-text-center"}>
                <label>
                    Host:
                    <input value={host} onChange={(e) => setHost(e.target.value)}/>
                </label>
                <br/>
                <br/>
                <label>
                    Port:
                    <input value={port} onChange={(e) => setPort(e.target.value)}/>
                </label>
                <br/>
                <br/>
                <button className={"btn ds-btn-green"} onClick={onAddBtnClick}>Change</button>
            </div>
        </div>
    )
}

export default NewBookScreen;