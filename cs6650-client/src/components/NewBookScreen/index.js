import NavBar from "../NavBar";
import {useDispatch, useSelector} from "react-redux";
import {Navigate, useNavigate} from "react-router-dom";
import React, {useState} from "react";
import {addBookThunk} from "../../data/books/books-thunk";


const NewBookScreen = () => {

    const {currentUser} = useSelector((state) => state.users)

    const [name, setName] = useState("");

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const onAddBtnClick = async () => {
        const newBook = {name, userId: currentUser.id, status: "shelf", sellPrice: 0.00};
        await dispatch(addBookThunk(newBook));
        navigate("/home");
    }

    return (
        <div className={"row"}>
            {
                !currentUser && <Navigate to={'/'} replace={true}/>
            }
            <NavBar/>
            <div className={"ds-text-center"}>
                <label>
                    Name:
                    <input value={name} onChange={(e) => setName(e.target.value)}/>
                </label>
                <br/>
                <br/>
                <button className={"btn btn-secondary"} onClick={onAddBtnClick}>Add</button>
            </div>
        </div>
    )
}

export default NewBookScreen;