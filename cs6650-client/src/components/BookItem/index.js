import React, {useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {addToCartThunk} from "../../data/carts/carts-thunk";
import {useNavigate} from "react-router-dom";
import {deleteBookThunk, sellBookThunk, shelfBookThunk} from "../../data/books/books-thunk";

const BookItem = ({book, type}) => {

    const {currentUser} = useSelector((state) => state.users)

    const [openSell, setOpenSell] = useState(false);
    const [sellPrice, setSellPrice] = useState(0);

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const onClickSellBook = () => {
        setOpenSell(false)
        const newBook = {id:book.id, name:book.name, userId: book.userId, status: book.status, sellPrice: sellPrice}
        dispatch(sellBookThunk(newBook));
    }

    const onClickShelfBook = () => {
        dispatch(shelfBookThunk(book));
    }

    const onClickDeleteBook = () => {
        dispatch(deleteBookThunk(book));
    }

    const onClickAddToCartBtn = async () => {
        const cart = {userId: currentUser.id, bookId: book.id}
        await dispatch(addToCartThunk(cart))
        navigate("/cart");
    }

    const typeSpecificDiv = () => {
        if (type === 'shelf') {
            return (
                <>
                    {
                        !openSell &&
                        <>
                            <button className={"btn btn-danger ms-2"} onClick={() => setOpenSell(true)}> Sell</button>
                            <button className={"btn btn-danger ms-2"} onClick={onClickDeleteBook}> Delete</button>
                        </>
                    }
                    {
                        openSell &&
                        <>
                            <input type={"number"} value={sellPrice} onChange={(e) => setSellPrice(parseInt(e.target.value))}/>
                            <button className={"btn btn-danger ms-2"} onClick={onClickSellBook}> Sell</button>
                            <button className={"btn btn-danger ms-2"} onClick={() => setOpenSell(false)}> Cancel</button>
                        </>
                    }
                </>)

        } else if (type === 'sell') {
            return (
                <>
                    <span className={'ms-2'}>
                        ${book.sellPrice}
                        <button className={"btn btn-danger ms-2"} onClick={onClickShelfBook}> Back to Shelf</button>
                    </span>

                </>)
        } else if (type === 'search') {
            return (
                <>
                    <button className={"btn btn-danger"} onClick={onClickAddToCartBtn}> Add To Cart </button>
                </>
            )
        } else if (type === 'cart') {
            return (
                <>
                </>
            )
        }
    }

    return (
        <div>
            {book.name}
            {typeSpecificDiv()}
        </div>
    )
}

export default BookItem;