import NavBar from "../NavBar";
import React, {useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {buyCartThunk, getCartThunk} from "../../data/carts/carts-thunk";
import BookItem from "../BookItem";
import {useNavigate} from "react-router-dom";

const CartScreen = () => {
    const {currentUser} = useSelector((state) => state.users);
    const {cartList} = useSelector((state) => state.books);

    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        dispatch(getCartThunk(currentUser))
    }, [currentUser, dispatch])

    const buyBtnClickHandler = async () => {
        const cart = {bookList:cartList, newUser:currentUser}
        await dispatch(buyCartThunk(cart));
        navigate("/home");
    }

    console.log(cartList)

    return (
        <>
            <NavBar/>
            <h2>
                Cart
            </h2>
            {
                cartList.map((book, key) => <div className={"mb-4"}>
                    <BookItem book={book} key={key} type={'cart'}/>
                </div>)
            }
            <div>
                <h2 className={"ds-left"}>
                    Total:
                </h2>
                <h2 className={"ds-right"}>
                    ${cartList.filter((book) => book.status === 'sell').reduce((acc, book) => acc + book.sellPrice, 0)} {
                        cartList.length !== 0 &&
                        <button className={"btn ds-btn-green ds-cart-btn me-2"} onClick={buyBtnClickHandler}>Buy</button>}
                </h2>
                <div className={"ds-clear"}/>
            </div>

        </>
    );
}

export default CartScreen;