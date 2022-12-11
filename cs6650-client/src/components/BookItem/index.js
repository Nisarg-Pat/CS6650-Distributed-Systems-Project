import React, {useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {addToCartThunk, removeFromCartThunk} from "../../data/carts/carts-thunk";
import {useNavigate} from "react-router-dom";
import {deleteBookThunk, sellBookThunk, shelfBookThunk} from "../../data/books/books-thunk";

const BookItem = ({book, type}) => {

    const {currentUser} = useSelector((state) => state.users)
    const {cartList} = useSelector((state) => state.books)

    const [openSell, setOpenSell] = useState(false);
    const [sellPrice, setSellPrice] = useState(0);

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const onClickSellBook = () => {
        setOpenSell(false)
        const newBook = {id: book.id, name: book.name, userId: book.userId, status: book.status, sellPrice: sellPrice}
        dispatch(sellBookThunk(newBook));
    }

    const onClickShelfBook = () => {
        dispatch(shelfBookThunk(book));
    }

    const onClickDeleteBook = () => {
        dispatch(deleteBookThunk(book));
    }

    const onClickAddToCartBtn = async () => {
        if(!currentUser) {
            navigate("/login");
        } else {
            const cart = {userId: currentUser.id, bookId: book.id}
            await dispatch(addToCartThunk(cart))
            navigate("/cart");
        }
    }

    const onClickRemoveFromCartBtn = () => {
        const cart = {userId: currentUser.id, bookId: book.id}
        dispatch(removeFromCartThunk(cart))
    }

    const isInCart = (book) => {
        return cartList.find((cartBook) => cartBook.id === book.id);
    }

    const typeSpecificDiv = () => {
        if (type === 'shelf') {
            return (
                <div className={"ds-text-center"}>
                    <div className={"mb-2"}>
                        {book.name}
                    </div>
                    <div>
                        {
                            !openSell &&
                            <>
                                <button className={"btn btn-danger"} onClick={() => setOpenSell(true)}> Sell</button>
                                <button className={"btn btn-danger ms-2"} onClick={onClickDeleteBook}> Delete</button>
                            </>
                        }
                        {
                            openSell &&
                            <>
                                <div className={"mb-2"}>
                                    Price: $ <input className={"ds-sell-input"} type={"number"} value={sellPrice} min={"0"} max={"100"}
                                                    onChange={(e) => setSellPrice(parseInt(e.target.value))}/>
                                </div>
                                <div>
                                    <button className={"btn btn-danger"} onClick={onClickSellBook}> Sell</button>
                                    <button className={"btn btn-danger ms-2"} onClick={() => setOpenSell(false)}> Cancel </button>
                                </div>
                            </>
                        }
                    </div>
                </div>)

        } else if (type === 'sell') {
            return (
                <div className={"ds-text-center"}>
                    <div className={"mb-2"}>
                        {book.name}
                    </div>
                    <div className={'mb-2'}>
                        Price: ${book.sellPrice}
                    </div>
                    {
                        book.status === 'sold' &&
                        <div>
                            <button className={"btn btn-outline-danger disabled"} onClick={onClickShelfBook}> Sold</button>
                        </div>
                    }
                    {
                        book.status === 'sell' &&
                        <div>
                            <button className={"btn btn-danger"} onClick={onClickShelfBook}> Back to Shelf</button>
                        </div>
                    }

                </div>)
        } else if (type === 'search') {
            return (
                <div className={"ds-text-center"}>
                    <div className={"mb-2"}>
                        {book.name}
                    </div>
                    {
                        !isInCart(book) &&
                        <>
                            <div className={'mb-2'}>
                                ${book.sellPrice}
                            </div>
                            <button className={"btn btn-danger"} onClick={onClickAddToCartBtn}>Add to Cart</button>
                        </>
                    }
                    {
                        isInCart(book) &&
                        <>
                            <div className={'mb-2'}>
                                ${book.sellPrice}
                            </div>
                            <button className={"btn btn-outline-danger disabled"}>Already In Cart</button>
                        </>
                    }
                </div>
            )
        } else if (type === 'cart') {
            return (
                <div className={"ds-text-center"}>
                    <h2 className={"ds-left mb-0"}>
                        {book.name}
                    </h2>
                    {
                        book.status === 'sell' &&
                        <h2 className={'ds-right mb-0'}>
                            ${book.sellPrice} <button className={"btn btn-danger ds-cart-btn"} onClick={onClickRemoveFromCartBtn}>Remove</button>
                        </h2>
                    }
                    {
                        book.status !== 'sell' &&
                        <h2 className={'ds-right mb-0'}>
                            No Longer Available <button className={"btn btn-danger ds-cart-btn"} onClick={onClickRemoveFromCartBtn}>Remove</button>
                        </h2>
                    }
                    <div className={"ds-clear"}/>
                </div>
            )
        }
    }

    return (
        <div className={"ds-book p-2"}>
            {typeSpecificDiv()}
        </div>
    )
}

export default BookItem;