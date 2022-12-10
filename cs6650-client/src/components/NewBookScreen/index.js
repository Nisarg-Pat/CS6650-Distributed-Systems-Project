import NavBar from "../NavBar";
import {useDispatch, useSelector} from "react-redux";
import {Navigate, useNavigate} from "react-router-dom";
import React, {useState} from "react";
import {addBookThunk} from "../../data/books/books-thunk";


const NewBookScreen = () => {

    const {currentUser} = useSelector((state) => state.users)

    const [name, setName] = useState("");
    const [quantity, setQuantity] = useState("");

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const onAddBtnClick = async () => {
        const newBook = {name, quantity, userId: currentUser.id};
        await dispatch(addBookThunk(newBook));
        navigate("/");
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
                <label>
                    Quantity:
                    <input value={quantity} onChange={(e) => setQuantity(e.target.value)} type={"number"}/>
                </label>
                <br/>
                <button className={"btn btn-secondary"} onClick={onAddBtnClick}>Add</button>
            </div>
        </div>
    )
}

export default NewBookScreen;